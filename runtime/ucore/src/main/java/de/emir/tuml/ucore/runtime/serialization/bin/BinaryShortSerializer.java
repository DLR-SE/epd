package de.emir.tuml.ucore.runtime.serialization.bin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.serialization.AbstractSerializer;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class BinaryShortSerializer extends AbstractSerializer {

    private static final Short END_OF_OBJECT_FLAG = (short) 0xFFFC;
    private static final Short LIST_FLAG = (short) 0xFFFE;
    private static final Short REFERENCE_FLAG = (short) 0xFFED;

    static short hash(String string, short salt) // PaulLarsonHash
    {
        short h = salt;
        char[] c = string.toCharArray();
        for (char s : c) {
            h = (short) (h * 101 + ((short) s));
        }
        return h;
    }

    static class FeatureHash {
        public FeatureHash(UStructuralFeature f, short h) {
            feature = f;
            hash = h;
        }

        UStructuralFeature feature;
        short hash;
    }

    static class ClassifierHash {
        public ClassifierHash(UClassifier cl, short h) {
            classifier = cl;
            hash = h;
        }

        UClassifier classifier;
        short hash;
        BiMap<Short, FeatureHash> hashFHMap = HashBiMap.create();
        BiMap<Short, UStructuralFeature> hashFeatureMap = HashBiMap.create();

        public Short getHash(UStructuralFeature f) {
            return hashFeatureMap.inverse().get(f);
        }

        public void add(short hash, UStructuralFeature feature, FeatureHash featureHash) {
            if (hasHash(hash))
                ULog.error("Dublicated hash: " + hash + " for feature: " + feature + "found");
            hashFHMap.put(hash, featureHash);
            hashFeatureMap.put(hash, feature);
        }

        public boolean hasHash(short hash) {
            return hashFeatureMap.containsKey(hash);
        }

        public UStructuralFeature getFeature(short hash) {
            return hashFeatureMap.get(hash);
        }
    }

    static class HashStorage {
        private BiMap<Short, ClassifierHash> hashHashMap;
        private BiMap<Short, UClassifier> hashClassifierMap;

        public HashStorage() {
            hashClassifierMap = HashBiMap.create();
            hashHashMap = HashBiMap.create();
        }

        public void add(short hash, UClassifier cl, ClassifierHash buildHash) {
            if (hasHash(hash)) {
                ULog.error("Detected duplicated hash " + hash + " for classifier: " + cl + " and classifier "
                        + getClassifier(hash));
            }
            hashHashMap.put(hash, buildHash);
            hashClassifierMap.put(hash, cl);
        }

        public UClassifier getClassifier(short hash) {
            return hashClassifierMap.get(hash);
        }

        public boolean hasHash(short hash) {
            return hashHashMap.containsKey(hash);
        }

        public Short getHash(UClass cl) {
            return hashClassifierMap.inverse().get(cl); // note this may also return an alias
        }

        public ClassifierHash getClassifierHash(UObject instance) {
            if (instance == null)
                return null;
            UClass cl = instance.getUClassifier();
            Short hash = getHash(cl);
            if (hash == null)
                throw new NullPointerException("Could not find hash for classifier: " + cl);
            return hashHashMap.get(hash);
        }

        public UClass getClass(short hash) {
            return (UClass) getClassifier(hash);
        }

        public ClassifierHash getClassifierHash(short hash) {
            return hashHashMap.get(hash);
        }
    }

    private HashStorage mHashes = null;

    private static HashStorage buildHashes() {
        HashStorage hashStore = new HashStorage();

        HashMap<String, UClassifier> clNameMap = new HashMap<>();
        ArrayList<String> nameList = new ArrayList<>();
        for (UClassifier cl : UCoreMetaRepository.getAllClassifier()) {
            if (nameList.contains(cl.getName()))
                ULog.error("Dublicated Name123 detected: " + cl.getName());
            clNameMap.put(cl.getName(), cl);
            nameList.add(cl.getName());

            // classifier may have an alias, that could also be used as identifier
            // such an alias is defined using the following annotation @GMF(alias := "Alias1,Alias2,Alias3,...")
            UAnnotationDetail aliases = cl.getAnnotationDetail("GMF", "alias");
            if (aliases != null && aliases.getValue() != null && aliases.getValue().isEmpty()) {
                String[] as = aliases.getValue().split(",");
                for (String alias : as) {
                    clNameMap.put(alias, cl);
                    if (nameList.contains(alias)) {
                        ULog.error("Dublicated Nameasd detected: " + alias);
                    }
                    nameList.add(alias);
                }
            }
        }

        // need to do the following on a sorted list, otherwise the salt may be added in another order
        Collections.sort(nameList);

        for (String nameObj : nameList) {
            short salt = 1; // start salt
            UClassifier cl = clNameMap.get(nameObj);
            short number = hash(nameObj, salt); // calculate hash with salt
            while (hashStore.hasHash(number)) { // beware of deadlock
                // !!!!!!!!!! //Check if
                // has is still
                // available
                ULog.warn("Warning Hash Collision detected: Classifier " + nameObj);
                salt++; // if not increase salt
                number = hash(nameObj, salt); // calculate has with increased salt
            }
            hashStore.add(number, cl, buildHash(number, cl));
        }

        return hashStore;
    }

    private static ClassifierHash buildHash(short hash, UClassifier cl) {
        ClassifierHash clHash = new ClassifierHash(cl, hash);

        // doing the same for the list of features - they can also contain aliases as the classifier
        // and need to be added in a specific order to change the salt
        HashMap<String, UStructuralFeature> featureNameMap = new HashMap<>();
        ArrayList<String> nameList = new ArrayList<>();
        for (UStructuralFeature f : cl.getAllStructuralFeatures()) {
            if (nameList.contains(f.getName()))
                ULog.error("Dublicated Name detected: " + f.getName());
            featureNameMap.put(f.getName(), f);
            nameList.add(f.getName());

            // classifier may have an alias, that could also be used as identifier
            // such an alias is defined using the following annotation @GMF(alias := "Alias1,Alias2,Alias3,...")
            UAnnotationDetail aliases = cl.getAnnotationDetail("GMF", "alias");
            if (aliases != null && aliases.getValue() != null && aliases.getValue().isEmpty()) {
                String[] as = aliases.getValue().split(",");
                for (String alias : as) {
                    featureNameMap.put(alias, f);
                    if (nameList.contains(alias)) {
                        ULog.error("Dublicated Name detected: " + alias);
                    }
                    nameList.add(alias);
                }
            }
        }
        // need to do the following on a sorted list, otherwise the salt may be added in another order
        Collections.sort(nameList);

        for (String nameObj : nameList) {
            short saltFeat = 16;

            short numberFeat = hash(nameObj, saltFeat);
            UStructuralFeature feature = featureNameMap.get(nameObj);
            // beware of deadlock !!!!!!!!!! //Check if hash is still
            // available // reserved hashs, FFFF no Feauture, FFFD List of
            // Features FFFE Reference
            while (clHash.hasHash(numberFeat) || (short) 0xFFFC == numberFeat || (short) 0xFFFF == numberFeat
                    || (short) 0xFFFE == numberFeat || (short) 0xFFFD == numberFeat) {

                System.out.println("Warning Hash Collision detected: Feature ");
                saltFeat++;
                numberFeat = hash(nameObj, saltFeat); // calculate hash with
                // increased salt
            }
            clHash.add(numberFeat, feature, new FeatureHash(feature, numberFeat));
        }
        return clHash;
    }

    static class ReferenceStorage {
        BiMap<Short, UObject> mapOfObjectPositions = HashBiMap.create();

        public void remember(UObject instance, int i) {
            mapOfObjectPositions.put((short) i, instance);
        }

        public boolean contains(UObject value) {
            return mapOfObjectPositions.inverse().containsKey(value);
        }

        public Short getLocationInStream(UObject value) {
            return mapOfObjectPositions.inverse().get(value);
        }

        public UObject getObjectAtLocation(short loc) {
            return mapOfObjectPositions.get(loc);
        }

    }

    @Override
    public void serialize(UObject instance, OutputStream stream) throws IOException {
        if (mHashes == null)
            mHashes = buildHashes();

        ReferenceStorage refStore = new ReferenceStorage();
        ArrayList<Short> serializedData = new ArrayList<Short>();
        serializeObject(instance, refStore, serializedData);

        for (Short x : serializedData) {
            byte[] b = { (byte) (x & 0xff), (byte) ((x >> 8) & 0xff) };
            stream.write(b);
        }

    }

    private void serializeObject(UObject instance, ReferenceStorage refStore, ArrayList<Short> stream) {
        ClassifierHash ch = mHashes.getClassifierHash(instance);
        stream.add(ch.hash);

        System.out.println(intend + "Classifier: " + ch.classifier.getName());
        intend += "\t";

        refStore.remember(instance, stream.size() - 1); // at this point we always remember the instance, the check if
                                                        // it has been written has been done before the call to this
                                                        // method

        for (UStructuralFeature f : ch.classifier.getAllReferences()) {
            if (f.isMany()) { // do this check before isSet, to save isSet queries; lists allways returns a value != 0
                List l = (List) f.get(instance);
                if (l.isEmpty() == false) {
                    stream.add(LIST_FLAG); // list indicator
                    stream.add((short) l.size());
                    stream.add(ch.getHash(f)); // TODO: this could be done before adding the list indicator, this way we
                                               // would save this 2bytes
                    for (Object obj : l)
                        serializeProperty(obj, refStore, stream, ch, f);
                }
            } else {
                if (isSet(f, instance)) {
                    stream.add(ch.getHash(f));
                    serializeProperty(f.get(instance), refStore, stream, ch, f);
                }
            }
        }
        stream.add(END_OF_OBJECT_FLAG);// end of object indicator
        intend = intend.substring(0, intend.length() - 1);
    }

    private void serializeProperty(Object value, ReferenceStorage refStore, ArrayList<Short> stream, ClassifierHash ch,
            UStructuralFeature f) {
        System.out.println(intend + "-Feature: " + f.getName());
        if (f.isReference()) {
            // check if it has already been serialized. in this case just write the position of the stream
            UObject uValue = (UObject) value;
            if (refStore.contains(uValue)) {
                short loc = refStore.getLocationInStream(uValue);
                short save = stream.set(stream.size() - 1, REFERENCE_FLAG); // reference indicator
                stream.add(save);
                stream.add(loc);
            } else {
                serializeObject(uValue, refStore, stream);
            }
        } else {
            if (f.getType() instanceof UEnum) {
                UEnumerator lit = ((UEnum) f.getType()).getEnumerator(value.toString());
                stream.add((short) lit.getValue()); // FIXME: this will crash if the literal uses an value > short
            } else {
                Class<?> clazz = f.getType().getRepresentingClass();
                if (clazz == double.class || clazz == Double.class) { // avoid instanceof and use == instead; look for
                                                                      // order for faster returning; double is more
                                                                      // often used than short or byte
                    double v = (double) value;
                } else if (clazz == double.class || clazz == Double.class) {
                    double v = (double) value;

                    short[] shorts = new short[ByteBuffer.allocate(8).putDouble(v).array().length / 2];
                    // to turn bytes to shorts as either big endian or
                    // little
                    // endian.
                    ByteBuffer.wrap(ByteBuffer.allocate(8).putDouble(v).array()).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(shorts);

                    // to turn shorts back to bytes.
                    byte[] bytes2 = new byte[shorts.length * 2];
                    ByteBuffer.wrap(bytes2).order(ByteOrder.BIG_ENDIAN).asShortBuffer().put(shorts);

                    for (int k = 3; k > -1; k--) {
                        stream.add(shorts[k]);
                    }
                } else if (clazz == int.class || clazz == Integer.class) {
                    int v = (int) value;
                    stream.add((short) (v >> 16));
                    stream.add((short) v);
                } else if (clazz == long.class || clazz == Long.class) {
                    long v = (long) value;
                    stream.add((short) (v >> 16)); // FIXME: Same encoding as integer => loss of information
                    stream.add((short) v);
                } else if (clazz == boolean.class || clazz == Boolean.class) {
                    boolean v = (boolean) value;
                    stream.add((short) (v ? 1 : 0));
                } else if (clazz == float.class || clazz == Float.class) {
                    float v = (float) value;

                    short[] shorts = new short[ByteBuffer.allocate(4).putFloat(v).array().length / 2];
                    // to turn bytes to shorts as either big endian or
                    // little
                    // endian.
                    ByteBuffer.wrap(ByteBuffer.allocate(4).putFloat(v).array()).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(shorts);

                    // to turn shorts back to bytes.
                    byte[] bytes2 = new byte[shorts.length * 2];
                    ByteBuffer.wrap(bytes2).order(ByteOrder.BIG_ENDIAN).asShortBuffer().put(shorts);

                    for (int k = 1; k > -1; k++) {
                        stream.add(shorts[k]);
                    }
                } else if (clazz == String.class) {
                    String v = (String) value;

                    char[] cStr = v.toCharArray();
                    stream.add((short) cStr.length);
                    for (int j = 0; j < cStr.length; j += 2) {
                        short mixer = (short) cStr[j];
                        mixer = (short) (mixer << 8);
                        if (j + 1 != cStr.length) {
                            mixer += cStr[j + 1];
                        }
                        stream.add(mixer);
                    }
                } else if (clazz == byte.class || clazz == Byte.class) {
                    byte v = (byte) value;
                    stream.add((short) v);
                } else if (clazz == short.class || clazz == Short.class) {
                    short v = (short) value;
                    stream.add(v);
                }
            }
        }
    }

    private boolean isSet(UStructuralFeature f, UObject instance) {
        if (f.isAttribute() == false)
            return f.get(instance) != null;
        else {
            Object v = f.get(instance);
            if (v instanceof Number && ((Number) v).doubleValue() == 0)
                return false;
            if (v instanceof Boolean)
                return ((Boolean) v); // false is default value
        }
        return true;
    }

    private int mPos = 0;

    @Override
    public UObject deserialize(InputStream stream) throws IOException {
        if (mHashes == null)
            mHashes = buildHashes();

        int s = stream.available();
        byte[] bBuffer = new byte[s];
        stream.read(bBuffer);
        short[] sBuffer = new short[s / 2];
        ByteBuffer.wrap(bBuffer).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sBuffer);

        return deserialize(sBuffer);

    }

    public UObject deserialize(short[] sBuffer) throws IOException {
        if (mHashes == null)
            mHashes = buildHashes();

        ReferenceStorage refStore = new ReferenceStorage();
        HashStorage hashStore = new HashStorage();
        mPos = 0;

        return deserializeObject(sBuffer, refStore, hashStore);
    }

    String intend = "";

    private UObject deserializeObject(short[] stream, ReferenceStorage refStore, HashStorage hashStore)
            throws IOException {
        short hash = stream[mPos++];
        ClassifierHash ch = mHashes.getClassifierHash(hash);
        if (ch == null)
            throw new NullPointerException(
                    "Could not find a valid Classifier for hash: " + hash + " at position: " + (mPos - 1));

        System.out.println(intend + "Classifier: " + ch.classifier.getName());
        intend += "\t";

        UObject newInstance = ((UClass) ch.classifier).createNewInstance();
        refStore.remember(newInstance, mPos - 1);

        // the next element will either be a feature hash, a LIST_FLAG or an END_OF_OBJECT
        short s = stream[mPos++];
        int listSize = 0;
        while (s != END_OF_OBJECT_FLAG) {
            if (s == LIST_FLAG) {
                // don't realy need this information but thats the order.... TODO: FIXME
                listSize = stream[mPos++];
                s = stream[mPos++]; // know we got the feature hash
            }
            // we know that s is the feature hash
            UStructuralFeature f = ch.getFeature(s);
            if (f.isMany()) { // we allready now the list size
                if (listSize > 0) { // otherwise there is no need to create the list
                    List l = (List) f.get(newInstance);
                    for (int i = 0; i < listSize; i++) {
                        Object le = deserializeProperty(stream, refStore, hashStore, f);
                        if (le != null)
                            l.add(le);
                        else
                            ULog.error("read property returned an invalid instance");
                    }
                }
            } else {
                Object v = deserializeProperty(stream, refStore, hashStore, f);
                if (v != null)
                    try {
                        f.set(newInstance, v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                else
                    ULog.error("read property returned an invalid instance");
            }
            s = stream[mPos++];
        }

        // TODO: just for debugging
        if (stream[mPos - 1] != END_OF_OBJECT_FLAG)
            throw new IOException(
                    "Missing end of object flag at position: " + mPos + " previously read object: " + newInstance);

        intend = intend.substring(0, intend.length() - 1);
        return newInstance;
    }

    private Object deserializeProperty(short[] stream, ReferenceStorage refStore, HashStorage hashStore,
            UStructuralFeature f) throws IOException {
        System.out.println(intend + "-Property: " + f.getName());
        if (f.isReference()) {
            if (stream[mPos + 1] == REFERENCE_FLAG) { // Note: do not change the pos
                mPos += 3;
                short loc = stream[mPos++]; // position of the referenced object, and also the key within the
                                            // referencestore
                return refStore.getObjectAtLocation(loc);
            } else
                return deserializeObject(stream, refStore, hashStore);
        } else if (f.getType() instanceof UEnum) {
            short val = stream[mPos++];
            UEnumerator lit = ((UEnum) f.getType()).getEnumerator(val);
            return ((UEnum) f.getType()).createNewInstance(val);
        } else {
            Class<?> clazz = f.getType().getRepresentingClass();
            if (clazz == double.class || clazz == Double.class) { // avoid instanceof and use == instead; look for order
                                                                  // for faster returning; double is more often used
                                                                  // than short or byte
                byte[] memorydouble = new byte[8];
                double xDouble;
                ByteBuffer buffer = ByteBuffer.allocate(8);
                for (int j = 3; j > -1; j--) {
                    buffer.putShort(stream[mPos + j]);
                }
                for (int k = 0; k < 8; k++) {
                    memorydouble[k] = buffer.get(k);
                }
                xDouble = ByteBuffer.wrap(memorydouble).getDouble();
                mPos += 4;
                return xDouble;
            } else if (clazz == int.class || clazz == Integer.class) {
                int int1 = stream[mPos++];
                int int2 = stream[mPos++];
                if (int2 < 0) {
                    int2 += 65535 + 1;
                }
                int2 += (int1 << 16);
                return int2;
            } else if (clazz == long.class || clazz == Long.class) {
                int int1 = stream[mPos++];
                int int2 = stream[mPos++];
                if (int2 < 0) {
                    int2 += 65535 + 1;
                }
                int2 += (int1 << 16);
                return int2; // FIXME: same as int
            } else if (clazz == boolean.class || clazz == Boolean.class) {
                short v = stream[mPos++];
                if (v == 1)
                    return true;
                return false;
            } else if (clazz == float.class || clazz == Float.class) {
                byte[] memoryfloat = new byte[4];
                float xFloat;
                ByteBuffer buffer = ByteBuffer.allocate(4);
                for (int j = 1; j > -1; j--) {
                    buffer.putShort(stream[mPos + j]);
                }
                for (int k = 0; k < 4; k++) {
                    memoryfloat[k] = buffer.get(k);
                }
                xFloat = ByteBuffer.wrap(memoryfloat).getFloat();
                mPos += 2;
                return xFloat;
            } else if (clazz == String.class) {
                int sizeOfString = stream[mPos++];
                String xString = "";
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < sizeOfString / 2; j++) {
                    builder.append((char) ((char) stream[mPos + j] >> 8));
                    builder.append((char) ((byte) stream[mPos + j]));
                }
                mPos += sizeOfString / 2;
                if ((sizeOfString / 2) * 2 != sizeOfString) {
                    builder.append((char) (stream[mPos++] >> 8));
                }
                xString = builder.toString();
                return xString;
            } else if (clazz == byte.class || clazz == Byte.class) {
                return (byte) stream[mPos++];
            } else if (clazz == short.class || clazz == Short.class) {
                return stream[mPos++];
            }
        }
        return null;
    }

}

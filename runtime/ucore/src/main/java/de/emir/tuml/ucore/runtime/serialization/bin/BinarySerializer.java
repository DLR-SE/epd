package de.emir.tuml.ucore.runtime.serialization.bin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.HashBiMap;

import de.emir.tuml.ucore.runtime.BinaryBlob;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.serialization.AbstractSerializer;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class BinarySerializer extends AbstractSerializer {

    public static final int MAGIC = 892832323;

    private HashBiMap<Short, UType> mClassifierMap = HashBiMap.create();
    private HashBiMap<Short, UStructuralFeature> mFeatureMap = HashBiMap.create();

    public HashMap<Integer, Object> mInstanceMap = new HashMap<>();

    @Override
    public void serialize(UObject instance, OutputStream outputStream) throws IOException {
        DataOutputStream stream = new DataOutputStream(outputStream);
        // we will start with a magic to easily check if a stream "could" be serialized with this serializer
        stream.writeInt(MAGIC);

        serializeUObject(instance, stream);

    }

    private void serializeUObject(UObject instance, DataOutputStream stream) throws IOException {
        // remember the instance to be referenced by other instances
        int instanceId = instance.hashCode();
        stream.writeInt(instanceId);
        // check if we allready have written the content
        if (mInstanceMap.containsKey(instanceId) == false) { // we did not
            // now remember the instance, need to be done at the beginning, otherwise we may run into an endless loop
            mInstanceMap.put(instanceId, instance);
            _serializeUObject(instance, stream);
        } // else: nothing to do, do the next object
    }

    private void _serializeUObject(UObject instance, DataOutputStream stream) throws IOException {
        UClass cl = instance.getUClassifier();
        writeClassifierToStream(cl, stream);
        // we now write how many features we are going to write for this object
        // therefore we first need to collect them
        ArrayList<Object[]> featuresToWrite = new ArrayList<>();
        for (UStructuralFeature f : cl.getAllStructuralFeatures()) {
            Object[] tmp = isSet(instance, f);
            if (tmp != null)
                featuresToWrite.add(tmp);
        }
        stream.writeShort(featuresToWrite.size());// write the number of features, use a short, since we do not allow
                                                  // more features in one classifier?!
        // now do the actual writing
        for (Object[] fv : featuresToWrite) {
            writeFeatureValueToStream((UStructuralFeature) fv[0], fv[1], stream);
        }
    }

    private void writeFeatureValueToStream(UStructuralFeature feature, Object value, DataOutputStream stream)
            throws IOException {
        writeFeatureToStream(feature, stream);
        if (feature.isMany()) {
            writeMultipleFeatureValuesToStream(feature, value, stream);
        } else
            writeSingleValue(feature, value, stream);
    }

    private void writeMultipleFeatureValuesToStream(UStructuralFeature feature, Object value, DataOutputStream stream)
            throws IOException {
        // if it's a list, we write the number of elements and later each element
        List l = (List) value;
        stream.writeInt(l.size());
        for (Object le : l) {
            writeSingleValue(feature, le, stream);
        }
    }

    private Object readSingleValue(DataInputStream stream, UStructuralFeature feature, int i) throws IOException {
        UType type = feature.getType();
        if (type instanceof UEnum) {
            short v = stream.readShort();
            return ((UEnum) type).createNewInstance(v);
        } else if (type instanceof UPrimitiveType) {
            Class<?> rt = type.getRepresentingClass();
            if (rt == double.class || rt == Double.class) {
                return stream.readDouble();
            } else if (rt == int.class || rt == Integer.class) {
                return stream.readInt();
            } else if (rt == String.class) {
                return readString(stream);
            } else if (rt == boolean.class || rt == Boolean.class) {
                return stream.readBoolean();
            } else if (rt == float.class || rt == Float.class) {
                return stream.readFloat();
            } else if (rt == byte.class || rt == Byte.class) {
                return stream.readByte();
            } else if (rt == long.class || rt == Long.class) {
                return stream.readLong();
            } else if (rt == short.class || rt == Short.class) {
                return stream.readShort();
            }else if (rt == BinaryBlob.class) {
            	String str = readString(stream);
            	return new BinaryBlob(str.getBytes());
            }
        } else {
            return readObject(stream);
        }
        return null;
    }

    private void writeSingleValue(UStructuralFeature feature, Object value, DataOutputStream stream)
            throws IOException {
        UType type = feature.getType();
        if (type instanceof UEnum) {
            UEnumerator lit = ((UEnum) type).getEnumerator(value.toString());
            stream.writeShort(lit.getValue()); // FIXME: this will crash if the literal uses an value > short
        } else if (type instanceof UPrimitiveType) {
            // avoid instanceof and use == instead; look for order for faster returning; double is more often used than
            // short or byte
            Class<?> rt = type.getRepresentingClass();
            if (rt == double.class || rt == Double.class) {
                stream.writeDouble((double) value);
            } else if (rt == int.class || rt == Integer.class) {
                stream.writeInt((int) value);
            } else if (rt == String.class) {
                writeString(value.toString(), stream);
            } else if (rt == boolean.class || rt == Boolean.class) {
                stream.writeBoolean((boolean) value);
            } else if (rt == float.class || rt == Float.class) {
                stream.writeFloat((float) value);
            } else if (rt == byte.class || rt == Byte.class) {
                stream.writeByte((byte) value);
            } else if (rt == long.class || rt == Long.class) {
                stream.writeLong((long) value);
            } else if (rt == short.class || rt == Short.class) {
                stream.writeShort((short) value);
            }else if (rt == BinaryBlob.class) {
            	byte[] data = ((BinaryBlob)value).get();
            	writeString(new String(data), stream);
            }
        } else {
            /// seems to be an object
            if (value instanceof UObject) {
                serializeUObject((UObject) value, stream);
            } else
                ULog.error("Failed to write value of: " + feature + " Type is not known");
        }
    }

    /// if the value is set, the method returns an array [f, valueToWrite], otherwise null
    /// this way we do not need to ask the feature value again, we do the actual writing
    private static Object[] isSet(UObject instance, UStructuralFeature f) {
        Object v = f.get(instance);
        if (v != null) {
            if (f.isMany()) {
                if (((List) v).isEmpty())
                    return null; // no need to serialize empty collections
            }
            return new Object[] { f, v };
        }
        return null;
    }

    private void writeClassifierToStream(UType cl, DataOutputStream stream) throws IOException {
        Short id = mClassifierMap.inverse().get(cl);
        if (id == null) { // not yet written
            id = (short) (mClassifierMap.size() + 1);
            // System.out.println(id + "=" + cl.getName());
            stream.writeShort(-id);
            writeString(cl.getName(), stream);
            mClassifierMap.put(id, cl);
        } else
            stream.writeShort(id);
    }

    private void writeFeatureToStream(UStructuralFeature feature, DataOutputStream stream) throws IOException {
        Short hash = mFeatureMap.inverse().get(feature);
        if (hash == null) {
            hash = (short) (mFeatureMap.size() + 1);
            stream.writeShort(-hash); // write negative hash to indicate that we need to read the information next
            writeString(feature.getName(), stream); // name of the feature
            stream.writeBoolean(feature.isMany()); // is many
            writeClassifierToStream(feature.getType(), stream); // type of the feature - may also describe the type
            mFeatureMap.put(hash, feature);
        } else
            stream.writeShort(hash);
    }

    private UStructuralFeature readFeatureFromStream(DataInputStream stream, UClass cl, boolean throwExceptions)
            throws IOException {
        short id = stream.readShort();
        if (id < 0) { // need to read the information
            String fn = readString(stream);
            boolean isList = stream.readBoolean();
            UType type = readTypeFromStream(stream);
            // now we try to find the feature in the classifier
            UStructuralFeature f = cl.getFeature(fn);
            if (throwExceptions && f != null) {
                if (type == null)
                    throw new IOException("Could not find the type for: " + fn);
                if (isList != f.isMany())
                    throw new IOException("Detected change in DataModel: " + fn + " isMany - differs");
                if (type.inherits(f.getType()) == false)
                    throw new IOException("Detect change in DataModel: " + fn + " type changed");
            }
            mFeatureMap.put((short) -id, f);
            return f;
        }
        UStructuralFeature f = mFeatureMap.get(id);
        if (f != null)
            return f;
        return null;
    }

//    private static final Charset charSet = Charset.forName("UTF-8");

    private void writeString(String str, DataOutputStream ds) throws IOException {
        ds.writeUTF(str);
        // ds.writeInt(str.length());
        // ds.write(str.getBytes(charSet));
    }

    private String readString(DataInputStream stream) throws IOException {
        // int l = stream.readInt();
        // byte[] b = new byte[l];
        // stream.read(b);
        // stream.readu
        // String str = new String(b, charSet);
        // if (str.equals("former lighthouse Großer Vogelsan"))
        // System.out.println();
        // if (str.isEmpty() == false)
        // System.out.println(str);
        return stream.readUTF();
    }

    @Override
    public UObject deserialize(InputStream stream) throws IOException {
        DataInputStream dis = new DataInputStream(stream);
        int magic = dis.readInt();
        if (magic != MAGIC)
            throw new IOException(
                    "Could not find MAGIC number at the beginning of the stream, this may not be the correct serializer");

        return (UObject) readObject(dis);
    }

    private Object readObject(DataInputStream stream) throws IOException {
        // we always first read the instanceid and check if we already did read this instance
        int instanceID = stream.readInt();
        if (mInstanceMap.containsKey(instanceID))
            return mInstanceMap.get(instanceID);

        // read / discover the classifier
        UClass cl = readClass(stream);
        if (cl == null)
            throw new IOException("Could not find type of classifier: POS: END -" + stream.available());
        UObject instance = cl.createNewInstance();
        // remember the instance (need to be done here, when not fully read, to avoid endless loops in circles)
        mInstanceMap.put(instanceID, instance);

        // now we expect the number of features, and the features itself
        short numFeatures = stream.readShort();
        for (int i = 0; i < numFeatures; i++) {
            readFeatureValueFromStream(instance, cl, stream, i);
        }

        return instance;
    }

    int sounding = 0;

    private void readFeatureValueFromStream(UObject instance, UClass cl, DataInputStream stream, int idx)
            throws IOException {
        // if (cl.getName().equals("SoundingFeature")) {
        // sounding++;
        // System.out.println(sounding + " = " + idx);
        // }
        // if (sounding == 6021 && idx == 2)
        // System.out.println();
        // if (sounding == 6024 && idx == 2)
        // System.out.println();

        UStructuralFeature feature = readFeatureFromStream(stream, cl, true);
        if (feature == null)
            throw new IOException("Could not find the expected feature");
        if (feature.isMany()) {
            int numEntries = stream.readInt();
            List l = (List) feature.get(instance);
            // clear for the case there are some default values
            l.clear();
            for (int i = 0; i < numEntries; i++) {
                Object value = readSingleValue(stream, feature, i);
                l.add(value); // note: we also add null values, but in this case there may be some issue
            }
        } else {
            Object value = readSingleValue(stream, feature, -1);
            feature.set(instance, value);
        }
    }

    private UType readTypeFromStream(DataInputStream stream) throws IOException {
        short id = stream.readShort();
        UType type = null;
        if (id < 0) { // in this case we need to identify the classifier (and read additional information)
            String clName = readString(stream);
            type = discoverType(id, clName);
            if (type == null)
                throw new IOException("Could not discover Type with name: " + clName);
            // remember for next time
            mClassifierMap.put((short) -id, type);
            // System.out.println("R: " + -id + "=" + clName);
        } else
            type = mClassifierMap.get(id);
        if (type == null)
            return null;
        return type;
    }

    private UClass readClass(DataInputStream stream) throws IOException {
        UType type = readTypeFromStream(stream);
        if (type == null || type instanceof UClass == false)
            throw new IOException("Expecting UClass but found: " + type);
        return (UClass) type;
    }

    /**
     * This method is used to identify / discover a type, based on the name for now we just look up in the
     * UCoreMetaRepository, but this would not handle changes in the datamodel. For this there need to be an option to
     * create dynamic classifiers(and objects)
     * 
     * @param id
     * @param clName
     * @return the type, if it could be discovered or null.
     */
    protected UType discoverType(short id, String clName) {
        UType cl = UCoreMetaRepository.findClassifierBySimpleName(clName);
        if (cl != null)
            return cl;
        // this may also be a primitive type, in this case we do need to look in the TypeUtils
        return TypeUtils.getPrimitiveType(clName); // may also returns null
    }

}

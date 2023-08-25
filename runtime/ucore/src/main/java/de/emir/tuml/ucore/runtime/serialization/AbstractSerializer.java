package de.emir.tuml.ucore.runtime.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.serialization.bin.BinarySerializer;
import de.emir.tuml.ucore.runtime.serialization.bin.BinaryShortSerializer;
import de.emir.tuml.ucore.runtime.serialization.json.JsonSerializer;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;

public abstract class AbstractSerializer implements ISerializer {

    /**
     * Creates a new instance of an AbstractSerializer
     * 
     * @note the File f is only used to estimate the serializer (see below) the created serializer is NOT initialized
     *       with this file creates a new Serializer with the following name pattern: - b(emod | app) = new
     *       BinarySerializer() - s(emod | sapp) = new BinaryShortSerializer() - as used in Labskaus implementation -
     *       else = XMLSerializer
     * @param f
     *            file to get find the serializer to be used
     * @return
     */
    public static AbstractSerializer createSerializerForFile(File f) {
        String absName = f.getAbsolutePath();
        int lidx = absName.lastIndexOf('.');
        String ext = absName.substring(lidx + 1);
        AbstractSerializer ser = new XMLSerializer();
        if (ext.startsWith("b"))
            ser = new BinarySerializer();
        else if (ext.startsWith("s"))
            ser = new BinaryShortSerializer();
        else if (ext.startsWith("j"))
        	ser = new JsonSerializer();
        else
            ser = new XMLSerializer();
        return ser;
    }

    public void writeFile(UObject instance, File target) throws IOException {
        if (target != null && target.getParentFile() != null) {
            if (target.getParentFile().exists() == false)
                target.getParentFile().mkdirs();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Using a ByteArrayOutputStream increases the performance for some MAGNITUDES, obviously the FileOutputStream
        // performs a write operation (on HDD) for each operation
        // by first writing it into the baos, we can batch the HDD operations to only one operation at all.
        this.serialize(instance, baos);
        FileOutputStream fos = new FileOutputStream(target);
        fos.write(baos.toByteArray(), 0, baos.size());
    }

    public UObject readFile(File input) throws IOException {
        FileInputStream fis = new FileInputStream(input);
        byte[] data = new byte[fis.available()];
        fis.read(data); // read at once
        fis.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        return deserialize(bais);
    }

}

package de.emir.tuml.ucore.runtime.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.emir.tuml.ucore.runtime.UObject;

public interface ISerializer {

    void serialize(UObject instance, OutputStream stream) throws IOException;

    UObject deserialize(InputStream stream) throws IOException;
}

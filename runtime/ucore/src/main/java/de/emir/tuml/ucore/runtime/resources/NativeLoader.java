package de.emir.tuml.ucore.runtime.resources;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Loading utility for handling java native libraries. This tool reads shared libraries contained in each
 * plugin resources and adds them to a lib folder next to the application directory.
 */
public class NativeLoader {

    private static final Logger log = LogManager.getLogger(NativeLoader.class);

    /**
     * Copies resources from one directory to another.
     * @param reference Reference class to load resources from.
     * @param parser Initial library information (containing platform information etc.) which is used to retrieve loaded libraries.
     * @param resourcePath Path of the directory in the plugins resource folder which contains the native libraries. These
     *                     are copied to the EPD home directory in order to be loaded dynamically.
     * @return List of detected libraries.
     * @throws IOException If copying of file was not possible.
     * @throws URISyntaxException If the supplied path was not in the right syntax.
     */
    private static List<LibraryInformation> copyResourceDirectory(Class<?> reference, LibraryInformation parser, String resourcePath) throws IOException, URISyntaxException {
        ResourceManager manager = ResourceManager.get(reference);
        Path dest = manager.getHomePath().resolve(resourcePath);
        File parent = dest.toFile();
        if (!Files.exists(dest)) {
            if (!parent.exists()) {
                try {
                    boolean result = parent.mkdirs();
                    if(!result) log.error("Could not create directory to copy to.");
                } catch (Exception e) {
                    log.error("Error while creating directory to copy to: {}.", e.getMessage());
                }
            }
        }
        URL url = reference.getClassLoader().getResource(resourcePath);
        // Load resources to a temporary directory.
        File file = new File(url.toURI());
        return copyDirectory(parser, new ArrayList<>(), file, parent);
    }

    /**
     * Copies contents of a directory to another.
     * @param parser Initial library information (containing platform information etc.) which is used to retrieve loaded libraries.
     * @param result List of results which should be filled.
     * @param sourceDir Source directory from where to load files.
     * @param targetDir Target directory where to copy files to.
     * @return List of detected libraries.
     * @throws IOException If creating files was not possible.
     */
    private static List<LibraryInformation> copyDirectory(LibraryInformation parser, List<LibraryInformation> result, File sourceDir, File targetDir) throws IOException {
        if (!sourceDir.isDirectory()) log.error("Cannot load {}. Needs to be a directory.", sourceDir);

        if (!targetDir.exists()) {
            if(!targetDir.mkdirs()) log.error("Unable to create target directory {} for native libraries.", targetDir.getAbsolutePath()); // create target directory if it doesn't exist
        }

        for (File file : sourceDir.listFiles()) {
            File targetFile = new File(targetDir, file.getName());
            if (file.isDirectory()) {
                copyDirectory(parser, result, file, targetFile);
            } else {
                if(parser.getAllowedExtensions().contains("*") || parser.getAllowedExtensions().contains(FilenameUtils.getExtension(targetFile.getName()))) {
                    result.add(copyFile(parser, file, targetFile));
                }
            }
        }
        return result;
    }

    /**
     * Copies a file to a new target.
     * @param parser Initial library information (containing platform information etc.) which is used to retrieve loaded libraries.
     * @param sourceFile Source directory from where to load files.
     * @param targetFile Target directory where to copy files to.
     * @return Copied library.
     * @throws IOException If creating file was not possible.
     */
    private static LibraryInformation copyFile(LibraryInformation parser, File sourceFile, File targetFile) throws IOException {
        LibraryInformation info = parser.copy();
        info.fromFileName(sourceFile.getName());
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(targetFile)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
        return info;
    }

    /**
     * Loads a library via the System.load call. Warning: If libraries are loaded using loadLibrary, they will not find libraries registered with System.load. Always use
     * the path of the library file (By default ResourceManager.get(CallerClass).getHomePath()+/lib) to load native libraries with the NativeLoader.
     * @param libPath Path of the library file to load.
     */
    private static void loadLibrary(Path libPath) {
        if(Files.exists(libPath)) {
            System.load(libPath.toAbsolutePath().toString());
            log.debug("Loaded library {}.", libPath);
        } else {
            log.error("Could not load library {}: File does not exist.", libPath.getFileName());
        }
    }

    /**
     * Registers a native resource to the NativeLoader. This will extract all native libraries from the specified folder inside the plugin resources to the general native library folder at
     * ResourceManager.get(CallerClass).getHomePath()+/lib.
     * @param reference Class which is part of the plugin from where the libraries should be loaded from.
     * @param parser LibraryInformation parser which is used for injecting registered library information.
     * @param libFolder Folder inside resources from where to register all libraries.
     * @return List of registered libraries which were found and are applicable to the current platform.
     */
    public static List<LibraryInformation> registerNativeResources(Class<?> reference, LibraryInformation parser, String libFolder) {
        try {
            return copyResourceDirectory(reference, parser, libFolder);
        } catch (IOException | URISyntaxException e) {
            log.error("Error while registering native resources: {}.", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Loads a library from the LibraryInformation with System.load if the filename is contained in the allowedExtensions of LibraryInformation.
     * @param nativeInformation Information to use for identifying library.
     */
    public static void loadLibrary(LibraryInformation nativeInformation) {
        Path homePath = getLibPath();
        if(nativeInformation.getAllowedExtensions().contains("*") || nativeInformation.getAllowedExtensions().contains(FilenameUtils.getExtension(nativeInformation.getLibraryName()))) {
            loadLibrary(homePath.resolve(nativeInformation.getLibraryName()));
        } else {
            log.error("Could not load library. The shared library file type is not supported on this platform.");
        }
    }

    /**
     * Gets the library path. The libraries are contained in the lib folder at the EPD configuration folder (home path).
     * @return Path of the common library folder.
     */
    private static Path getLibPath() {
        return ResourceManager.get(NativeLoader.class).getHomePath().resolve("lib");
    }
}



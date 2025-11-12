package de.emir.tuml.ucore.runtime.resources;

import java.util.Set;

/**
 * This interface contains library definitions which are used for loading native libraries using the NativeLoader class. As
 * each shared library could use different naming schemes, this class needs to be implemented when using the NativeLoader.
 */
public interface LibraryInformation {

    /**
     * Gets the name of the shared library file including file extension.
     * @return Name of the library file.
     */
    String getLibraryName();

    /**
     * Gets the platform classifier. This indicates the type of operating system and architecture used. This
     * can be used to identify the shared library name required for the platform.
     * @return Platform classifier of the current JVM platform.
     */
    default PlatformClassifier getPlatformClassifier() {
        String osName = System.getProperty("os.name").toLowerCase();

        String osArch = System.getProperty("os.arch").toLowerCase();

        switch (osArch) {
            case "x86" -> osArch = "x86_32";
            case "x86_64", "x86-64", "amd64" ->
                osArch = "x86_64";
            case "aarch32", "arm" ->
                osArch = "aarch32";
            case "aarch64", "arm64"->
                osArch = "aarch64";
        }

        if (osName.contains("win")) {
            switch (osArch) {
                case "x86_32" -> {
                    return PlatformClassifier.WINDOWS_X86_32;
                }
                case "x86_64" -> {
                    return PlatformClassifier.WINDOWS_X86_64;
                }
                case "aarch32" -> {
                    return PlatformClassifier.WINDOWS_AARCH32;
                }
                case "aarch64" -> {
                    return PlatformClassifier.WINDOWS_AARCH64;
                }
                case null, default -> throw new UnsupportedOperationException("Unsupported OS: " + osName);
            }
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            switch (osArch) {
                case "x86_32" -> {
                    return PlatformClassifier.WINDOWS_X86_32;
                }
                case "x86_64" -> {
                    return PlatformClassifier.WINDOWS_X86_64;
                }
                case "aarch32" -> {
                    return PlatformClassifier.WINDOWS_AARCH32;
                }
                case "aarch64" -> {
                    return PlatformClassifier.WINDOWS_AARCH64;
                }
                case null, default -> throw new UnsupportedOperationException("Unsupported OS: " + osName);
            }
        } else if (osName.contains("mac")) {
            switch (osArch) {
                case "x86_32" -> {
                    return PlatformClassifier.WINDOWS_X86_32;
                }
                case "x86_64" -> {
                    return PlatformClassifier.WINDOWS_X86_64;
                }
                case "aarch32" -> {
                    return PlatformClassifier.WINDOWS_AARCH32;
                }
                case "aarch64" -> {
                    return PlatformClassifier.WINDOWS_AARCH64;
                }
                case null, default -> throw new UnsupportedOperationException("Unsupported OS: " + osName);
            }
        } else {
            throw new UnsupportedOperationException("Unsupported OS: " + osName);
        }
    }

    /**
     * Gets the list of allowed file extensions which are loaded from the lib directory. As
     * each operating system needs a different kind of shared library file (.so for linux, .dll for windows etc.),
     * only libraries compatible with the current platform should be included in the allowed extensions.
     * @return Set of allowed extensions, for example dll, so etc. (do not include the dot before the file extension).
     */
    Set<String> getAllowedExtensions();

    /**
     * Parses and populates the libraryName from a file name.
     * @param filename File name to parse.
     */
    void fromFileName(String filename);

    /**
     * Copies the object.
     * @return Copy of the LibraryInformation instance.
     */
    LibraryInformation copy();

    /**
     * Represents the platform and arch used for this JVM.
     */
    enum PlatformClassifier {
        WINDOWS_X86_32,
        WINDOWS_X86_64,
        WINDOWS_AARCH32,
        WINDOWS_AARCH64,
        LINUX_X86_32,
        LINUX_X86_64,
        LINUX_AARCH32,
        LINUX_AARCH64,
        MACOS_X86_32,
        MACOS_X86_64,
        MAXOS_AARCH32,
        MACOS_AARCH64,
    }
}

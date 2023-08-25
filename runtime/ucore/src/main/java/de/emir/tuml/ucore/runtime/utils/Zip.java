package de.emir.tuml.ucore.runtime.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static ArrayList<File> decompress(final File file, final String target, final boolean overwrite)
            throws ZipFileException, IOException {
        return decompress(file, target, overwrite, false);
    }

    public static ArrayList<File> decompress(final File file, final String target, final boolean overwrite,
            final boolean force) throws ZipFileException, IOException {
        if (file.getAbsolutePath().endsWith(".zip") || force)
            return decompressZip(file, target, overwrite);

        return null;
    }

    public static void compressZip(final String rootDir, final String target, final boolean overwrite)
            throws ZipFileException {
        final ArrayList<File> fileList = FileOperations.readAllFilesOfDirectory(rootDir, -1);
        compressZip(rootDir, fileList, target, overwrite);
    }

    public static void compressZip(String root, final ArrayList<File> fileList, final String target,
            final boolean overwrite) throws ZipFileException {
        final File f = new File(target);
        if (f.exists() && !overwrite)
            throw new ZipFileException("File " + target + " already exists.");
        int read = 0;
        FileInputStream in;
        final byte[] data = new byte[1024];
        try {
            if (!root.endsWith(File.separator))
                root += File.separator;
            final ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target));
            out.setMethod(ZipOutputStream.DEFLATED);

            for (int i = 0; i < fileList.size(); i++) {
                final String name = fileList.get(i).getPath().replace(root, "");
                final ZipEntry entry = new ZipEntry(name);
                in = new FileInputStream(fileList.get(i).getPath());
                out.putNextEntry(entry);
                while ((read = in.read(data, 0, 1024)) != -1)
                    out.write(data, 0, read);
                out.closeEntry();
                in.close();
//                System.out.println(name + "...added");
            }
            out.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<File> decompressZip(final File f, final String target, final boolean overwrite)
            throws ZipFileException, IOException {
        final java.io.File targetFile = new java.io.File(target);
        if (targetFile.exists() && !overwrite)
            throw new ZipFileException("Folder or file : " + target + " already exists");
        targetFile.mkdirs();
        final ZipFile zf = new ZipFile(f.getAbsolutePath());
        final Enumeration<? extends ZipEntry> entries = zf.entries();
        final ArrayList<File> out = new ArrayList<File>();
        while (entries.hasMoreElements()) {
            final ZipEntry entry = entries.nextElement();
            out.add(saveEntry(entry, zf, target));
//            System.out.println(target + File.separator + entry.getName() + "....unziped");
        }
        zf.close();
        return out;
    }

    private static File saveEntry(final ZipEntry entry, final ZipFile zf, final String target) throws IOException {
        final File f = new File(target + "/" + entry.getName());
        if (entry.isDirectory())
            f.mkdirs();
        else {
            final InputStream is = zf.getInputStream(entry);
            final BufferedInputStream bis = new BufferedInputStream(is);
            new File(f.getParent()).mkdirs();
            final FileOutputStream fos = new FileOutputStream(f);
            final BufferedOutputStream bos = new BufferedOutputStream(fos);
            final int EOF = -1;
            for (int c; (c = bis.read()) != EOF;)
                bos.write((byte) c);
            is.close();
            bos.close();
            fos.close();
        }
        return f;
    }

    public void compressZip(final File root, final File target, final boolean overwrite) throws ZipFileException {
        compressZip(root.getAbsolutePath(), target.getAbsolutePath(), overwrite);
    }
}

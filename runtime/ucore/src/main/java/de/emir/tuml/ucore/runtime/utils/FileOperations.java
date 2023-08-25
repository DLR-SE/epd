package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.logging.ULog;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {


	public static String getRelativePathToDirectory(final String file, final String relativeTo) throws IOException{
		return getRelativePathToDirectory(new File(file), new File(relativeTo));
	}
	public static String getRelativePathToDirectory(final File file, final File file2) throws IOException {
		if (file2.isDirectory()==false)
			return getRelativePath(file, file2.getParentFile());
		else
			return getRelativePath(file, file2);
	}
	public static String getRelativePath(final String file, final String relativeTo) throws IOException
	{
		return getRelativePath(new File(file), new File(relativeTo));
	}
	public static String getRelativePath(File file, File relativeTo)
			throws IOException {
		//Übernommen von: http://forums.sun.com/thread.jspa?threadID=584546
		//Autor:  rioriorio945  06.01.2005 07:19
		file = new File(file + File.separator + "89243jmsjigs45u9w43545lkhj7").getParentFile();
		relativeTo = new File(relativeTo + File.separator+ "984mvcxbsfgqoykj30487df556").getParentFile();
		final File origFile = file;
		final File origRelativeTo = relativeTo;
		final ArrayList filePathStack = new ArrayList();
		final ArrayList relativeToPathStack = new ArrayList();
		// build the path stack info to compare it afterwards
		file = file.getCanonicalFile();
		while (file != null) {
			filePathStack.add(0, file);
			file = file.getParentFile();
		}
		relativeTo = relativeTo.getCanonicalFile();
		while (relativeTo != null) {
			relativeToPathStack.add(0, relativeTo);
			relativeTo = relativeTo.getParentFile();
		}
		// compare as long it goes
		int count = 0;
		file = (File) filePathStack.get(count);
		relativeTo = (File) relativeToPathStack.get(count);
		while (count < filePathStack.size() - 1
				&& count < relativeToPathStack.size() - 1
				&& file.equals(relativeTo)) {
			count++;
			file = (File) filePathStack.get(count);
			relativeTo = (File) relativeToPathStack.get(count);
		}
		if (file.equals(relativeTo))
			count++;
		// up as far as necessary
		final StringBuffer relString = new StringBuffer();
		for (int i = count; i < relativeToPathStack.size(); i++) {
			relString.append(".." + File.separator);
		}
		// now back down to the file
		for (int i = count; i < filePathStack.size() - 1; i++) {
			relString.append(((File) filePathStack.get(i)).getName()
					+ File.separator);
		}
		relString.append(((File) filePathStack.get(filePathStack.size() - 1))
				.getName());
		// just to test
		final File relFile = new File(origRelativeTo.getAbsolutePath()
				+ File.separator + relString.toString());
		if (!relFile.getCanonicalFile().equals(origFile.getCanonicalFile())) {
			throw new IOException("Failed to find relative path.");
		}
		return relString.toString();
	}
	public static void createDir(final String dir, final boolean overwrite)
			throws IOException {

		File f = new File(dir);
		if (f.exists() && overwrite) {
			deleteDir(dir);
		}
		if (!f.mkdirs())
			throw new RuntimeException("failed to create directory: " + dir);
		f = null;

	}

	public static void deleteDir(final String dir) throws IOException {
		File f = new File(dir);
		if (f.isDirectory()) {
			final File[] fl = f.listFiles();
			for (int i = 0; i < fl.length; i++) {
				deleteDir(fl[i].getAbsolutePath());
			}
		}
		if (!f.delete())
			throw new IOException("Could not delete file or directory: " + f);
		f = null;
	}

	public static boolean copyDir(final String src, final String dest, final boolean overwrite, final boolean verbose) throws IOException {
		return copyDir(src, dest, overwrite, verbose, false, null);
	}
	public static boolean copyDir(final String src, final String dest, final boolean overwrite) throws IOException {
		return copyDir(src, dest, overwrite, false, false, null);
	}
	public static boolean copyDir(final String src, final String dest, final boolean overwrite, final FileFilter fileFilter) throws IOException {
		return copyDir(src, dest, overwrite, false, false, fileFilter);
	}
	public static boolean copyDirNew(final String src, final String dest, final boolean overwrite, final boolean verbose) throws IOException {
		return copyDir(src, dest, overwrite, verbose, true, null);
	}
	public static boolean copyDirNew(final File src, final File dest, final boolean overwrite) throws IOException {
		return copyDir(src.getAbsolutePath(), dest.getAbsolutePath(), overwrite, false, true, null);
	}
	public static boolean copyDirNew(final String src, final String dest, final boolean overwrite) throws IOException {
		return copyDir(src, dest, overwrite, false, true, null);
	}
	public static boolean copyDirNew(final String src, final String dest, final boolean overwrite, final FileFilter fileFilter) throws IOException {
		return copyDir(src, dest, overwrite, false, true, fileFilter);
	}
	public static boolean copyDir(final File src, final File dst, final boolean overwrite) throws IOException {
		return copyDir(src.getAbsolutePath(), dst.getAbsolutePath(), overwrite);
	}
	public static boolean copyDir(final File src, final File dst, final boolean overwrite, final boolean verbose) throws IOException {
		return copyDir(src.getAbsolutePath(), dst.getAbsolutePath(), overwrite, verbose);
	}
	public static boolean copyDir(final File src, final File dst, final boolean overwrite, final FileFilter fileFilter) throws IOException {
		return copyDir(src.getAbsolutePath(), dst.getAbsolutePath(), overwrite, fileFilter);
	}

	public static boolean copyDir(final String src, final String dest, final boolean overwrite, final boolean verbose, final boolean onlynew, final FileFilter fileFilter) throws IOException {
		final File source = new File(src);
		if (!source.exists()){
			if (!verbose){
				System.out.println("Source " + src + " does not exist");
			}
			return false;
		}
		final File target = new File(dest);
		if (!target.isDirectory())
			createDir(target.getAbsolutePath(), overwrite);
		File[] fl = null;
		if (fileFilter != null)
			fl = source.listFiles(fileFilter);
		else
			fl = source.listFiles();
		if (fl == null){
			if (!verbose)
				System.out.println("Empty Directory");
			return false;
		}
		boolean res = true;
		for (int i = 0; i < fl.length; i++)
			if (fl[i].isDirectory()){
				final String tmp = dest+"/" + fl[i].getName();
				res = res & copyDir(fl[i].getAbsolutePath(), tmp, overwrite, verbose, onlynew, fileFilter);
				if (!verbose && res)
					System.out.println("CopyDir: "+fl[i].getAbsolutePath()+" to: "+tmp);
			}else {
				final String[] tmp = fl[i].getPath().replace("\\", "/").split("/");
				final String filename = tmp[tmp.length - 1];
				res = res & copy(fl[i].getAbsolutePath(), dest + "/" + filename, overwrite, onlynew);
				if (!verbose && res)
					System.out.println("CopyFile: "+fl[i].getAbsolutePath()+" to: "+dest + "/" + filename);

			}
		return res;
	}

	public static boolean copy(final String src, final String dest, final boolean overwrite, final boolean onlynew) throws IOException {
		final File t = new File(dest);
		if (t.exists() && !overwrite)
			return false;
		final File s =new File(src);
		if (onlynew){
			if (t.lastModified() >= s.lastModified())
				return false;
		}

		final String tmp = t.getAbsolutePath().replace(t.getName(), "");
		final File t2 = new File(tmp);
		if (t2.exists() && !t2.isDirectory() && !overwrite){
			return false;
		}
		if (!t2.exists())
			createDir(tmp, overwrite);

		FileInputStream f_in = null;
		FileOutputStream f_out = null;

		try {
			// Streams ï¿½ffnen
			f_in = new FileInputStream(src);
			f_out = new FileOutputStream(dest);

			// eigentliches Kopieren blockweise
			final byte[] buffer = new byte[1000];
			int n_bytes;
			for (;;) {
				n_bytes = f_in.read(buffer);
				if (n_bytes == -1)
					break;
				f_out.write(buffer, 0, n_bytes);
			}
		} catch (final IOException e) {
			ULog.error(e);
		} finally {
			// auf jeden Fall aufrï¿½umen
			if (f_in != null)
				try {
					f_in.close();
				} catch (final IOException e) {
				}
			if (f_out != null)
				try {
					f_out.close();
				} catch (final IOException e) {
				}
		}
		return true;
	}



	public static File searchFile(final String name, final String root, final int depth) {
		final ArrayList<File> fl = readAllFilesOfDirectory(root, depth);
		if (fl == null)
			return null;
		for (int i = 0; i < fl.size(); i++)
			if (fl.get(i).getName().equals(name))
				return fl.get(i);
		return null;
	}

	public static File searchDirectory(final String name, final File root, int deep){
		if (deep == 0)
			return null;
		File[] fa = root.listFiles();
		if (fa == null)
			return null;
		for (int i = 0; i < fa.length; i++){
			if (fa[i].isDirectory()){
				if (fa[i].getName().equals(name))
					return fa[i];
				File res = searchDirectory(name, fa[i], deep-1);
				if (res != null)
					return res;
			}
		}
		return null;
	}

	public static File searchFile(final String name, final String root) {
		return searchFile(name, root, -1);
	}
	public static ArrayList<File> readAllFiles(final String path, final int depth, final FileFilter filter){
		return readAllFiles(path, depth, filter, false);
	}
	public static ArrayList<File> readAllFiles(final String path, final int depth, final FileFilter filter, final boolean Selectdirectory){
		if (depth == 0)
			return null;
		final File f = new File(path);
		final ArrayList<File> fileList = new ArrayList<File>();
		File[] fa = null;
		if (filter != null)
			fa= f.listFiles(filter);
		else
			fa = f.listFiles();
		if (fa == null)
			return null;
		for (int i = 0; i < fa.length; i++){
			if (fa[i].isDirectory()){
				if (Selectdirectory)
					fileList.add(fa[i]);
				final ArrayList<File> tmp = readAllFiles(fa[i].getAbsolutePath(), depth-1, filter, Selectdirectory);
				if (tmp != null)
					fileList.addAll(tmp);
			}else
				fileList.add(fa[i]);
		}
		return fileList;
	}

	public static ArrayList<File> readAllFilesOfDirectory(final String path, final int depth) {
		return readAllFiles(path, depth, null);
	}

	public static ArrayList<File> readAllFiles(final File f, final int depth, final FileFilter filter) {
		return readAllFiles(f.getAbsolutePath(), depth, filter);
	}


	public static void delete(final String target, final boolean recursiv) throws IOException {
		final File f = new File(target);
		if (!f.exists())
			return ;
		if (f.isDirectory() && recursiv)
			deleteDir(target);
		else
			if (!f.delete())
				throw new IOException("Failed to delete target: "+target);

	}

	public static void cut(final String source, final String target,final boolean overwrite, final boolean recursiv) throws IOException {
		cut(source, target, overwrite, recursiv, false);
	}
	public static void cut(final String source, final String target,final boolean overwrite, final boolean recursiv, final boolean verbose) throws IOException {
		final File s = new File(source);
		if (!s.exists())
			return;
		final File t = new File(target);
		if (t.exists() && !overwrite)
			return ;
		if (s.isDirectory()){
			copyDir(source, target, overwrite, verbose);
			delete(source, true);
		}else{
			copy(source, target, overwrite, true);
			delete(source, false);
		}
	}

	public static void rename(final String source, final String target, final boolean overwrite) {
		final File f = new File(source);
		if (!f.exists())
			return ;
		final File t = new File(target);
		if (t.exists() && !overwrite){
			return;
		}
		f.renameTo(t);
	}

	public static void decompress(final String source, final String target,
			final boolean overwrite) throws ZipFileException, IOException {
		Zip.decompressZip(new File(source), target, overwrite);

	}
	public static void compress(final String root, final String[] source, final String target, final boolean recursiv, final boolean overwrite) throws ZipFileException, IOException {
		if (new File(target).exists() && overwrite)
			delete(target, false);
		final ArrayList<File> fileList = new ArrayList<File>();
		for (int i = 0; i < source.length; i++){
			final File f = new File(source[i]);
			if (!f.exists())
				continue;
			if (f.isDirectory() && recursiv){
				final ArrayList<File> tmp = readAllFilesOfDirectory(f.getAbsolutePath(), -1);
				fileList.addAll(tmp);
			}else
				fileList.add(f);
		}
		Zip.compressZip(root, fileList, target, false);
	}
	public static void compress(final String root, final String[] source, final String target, final boolean recursiv) throws ZipFileException, IOException {
		compress(root, source, target, recursiv, false);
	}

	public static void createDir(final File dir, final boolean overwrite) throws IOException {
		createDir(dir.getAbsolutePath(), overwrite);
	}

	public static void copy(final File src, final File dest, final boolean overwrite) throws IOException {
		copy(src.getAbsolutePath(), dest.getAbsolutePath(), overwrite, false);
	}
	public static void copyNew(final File src, final File dest, final boolean overwrite) throws IOException {
		copy(src.getAbsolutePath(), dest.getAbsolutePath(), overwrite, true);
	}

	public static void deleteDir(final File dir) throws IOException {
		deleteDir(dir.getAbsolutePath());
	}

	public static void delete(final File file, final boolean recursive) throws IOException {
		delete(file.getAbsolutePath(), recursive);
	}


	/**
     * Non-valid Characters for naming files, folders under Windows: <code>":", "*", "?", "\"", "<", ">", "|"</code>
     *
     * @see <a href="http://support.microsoft.com/?scid=kb%3Ben-us%3B177506&x=12&y=13">
     *      http://support.microsoft.com/?scid=kb%3Ben-us%3B177506&x=12&y=13</a>
     */
    private static final String[] INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME = { ":", "*", "?", "\"", "<", ">", "|" };
	
	/**
     * For Windows OS, check if the file name contains any of the following characters:
     * <code>":", "*", "?", "\"", "<", ">", "|"</code>
     *
     * @param f not null file
     * @return <code>false</code> if the file path contains any of forbidden Windows characters,
     *         <code>true</code> if the Os is not Windows or if the file path respect the Windows constraints.
     * @see #INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME
     * @since 1.5.2
     */
    public static boolean isValidWindowsFileName( File f )
    {
    
    	String n = f.getName();
    	for (int i = 0; i < INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME.length; i++) {
    		int idx = n.indexOf(INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME[i]);
    		if (idx != -1)
    			return false; 
    	}
        File parentFile = f.getParentFile();
        if ( parentFile != null )
        {
            return isValidWindowsFileName( parentFile );
        }
        

        return true;
    }
}


package de.emir.tuml.ucore.runtime.resources;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

import de.emir.tuml.ucore.runtime.logging.ULog;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility class to load icons from resources.
 */
public class IconManager {

	private static final Map<URI, BufferedImage>						mImageMap = new HashMap<>();
	private static final Map<URI, HashMap<Integer, BufferedImage>> mScaledImageMap = new HashMap<>();
	protected static float scaleFactor = 1.0f;
	
	/**
	 * Returns a scaled image icon for the given location.
	 * Uses the ResourceManager, to resolve a valid URL from the input string.
	 * @param resourceName Path of the resource to resolve.
	 * @param size Size of the icon.
	 * @return ImageIcon at path or null if not existing.
	 */
	public static ImageIcon getIcon(Object caller, String resourceName, int size){
		Class<?> callerClazz;
		
		if (caller == null) callerClazz = IconManager.class;
		else if (caller instanceof Class) callerClazz = (Class<? extends Object>)caller;
		else callerClazz = caller.getClass();
		
		URL _url = ResourceManager.get(callerClazz).resolveResource(resourceName);
		return getIcon(_url, size);
	}

	/**
	 * Returns the image for the given location. Uses the ResourceManager to resolve a valid URL for the resource name.
	 * @param caller Caller class to resolve resource from. The class specifies the location where resources are
	 *               retrieved from.
	 * @param resourceName Path of the resource to resolve
	 * @return Image at path if found, else null.
	 */
	public static BufferedImage getImage(Object caller, String resourceName) {
		if (caller != null)
			return getImage(caller.getClass(), resourceName);
		else
			return getImage(ResourceManager.class, resourceName); 
	}

	/**
	 * Returns a scaled image for the given location. Uses the ResourceManager to resolve a valid URL for the resource name.
	 * @param caller Caller class to resolve resource from. The class specifies the location where resources are
	 *               retrieved from.
	 * @param resourceName Path of the resource to resolve.
	 * @param size Size of the image to return.
	 * @return Image at path if found, else null.
	 */
	public static BufferedImage getImage(Object caller, String resourceName, int size) {
		if (caller != null)
			return getImage(caller.getClass(), resourceName, size);
		else
			return getImage(ResourceManager.class, resourceName, size); 
	}

	/**
	 * Returns the image for the given location. Uses the ResourceManager to resolve a valid URL for the resource name.
	 * @param callerClazz Caller class to resolve resource from. The class specifies the location where resources are
	 *               retrieved from.
	 * @param resourceName Path of the resource to resolve
	 * @return Image at path if found, else null.
	 */
	public static BufferedImage getImage(Class<?> callerClazz, String resourceName) {
		URL _url = ResourceManager.get(callerClazz).resolveResource(resourceName);
		return getImage(_url);
	}

	/**
	 * Returns the scaled image for the given location. Uses the ResourceManager to resolve a valid URL for the resource name.
	 * @param callerClazz Caller class to resolve resource from. The class specifies the location where resources are
	 *               retrieved from.
	 * @param resourceName Path of the resource to resolve
	 * @param size Size of the image to return.
	 * @return Image at path if found, else null.
	 */
	public static BufferedImage getImage(Class<?> callerClazz, String resourceName, int size) {
		URL _url = ResourceManager.get(callerClazz).resolveResource(resourceName);
		return getScaledImage(_url, size);
	}

	/**
	 * Returns an image icon for the given location.
	 * Uses the ResourceManager, to resolve a valid URL from the input string.
	 * @param caller Caller class to resolve resource from. The class specifies the location where resources are
	 *               retrieved from.
	 * @param resourceName Path of the resource to resolve.
	 * @return ImageIcon at path or null if not existing.
	 */
	public static ImageIcon getIcon(Object caller, String resourceName) {
		return getIcon(caller,resourceName, -1);
	}
	/**
	 * Scales the image to the requested size and creates an image icon
	 * @param url url to the image to load
	 * @param size size in pixel
	 * @return image icon with requested size or null if not found.
	 */
	public static ImageIcon getIcon(URL url, int size){
		if (url == null)
			return null;
		BufferedImage img = size > 0 ? getScaledImage(url, size) : getImage(url);
		if (img != null)
			return new ImageIcon(img);
		return null;
	}
	
	/**
	 * returns the scaled image of the given resource name, if the name could be resolved, using the ResourceManager
	 * @param resourceName Path of the resource to resolve.
	 * @param size Size of the image to return.
	 * @return image with the given resource name, if it could be resolved, null otherwise.
	 */
	public static Image getScaledImage(Object caller, String resourceName, int size) {
		Class<?> callerClazz;
		
		if (caller == null) callerClazz = IconManager.class;
		else if (caller instanceof Class) callerClazz = (Class<? extends Object>)caller;
		else callerClazz = caller.getClass();
		
		URL _url = ResourceManager.get(callerClazz).resolveResource(resourceName);
		if (_url == null)
			return null;
		return getScaledImage(_url, size);
	}

	/**
	 * Returns a scaled image of the image found at the given version.
	 * @param url URL of the scaled image to load.
	 * @param size Size of the image.
	 * @return null if the url is invalid or does not point to an image.
	 */
	public static BufferedImage getScaledImage(URL url, int size) {
		try {
            if (url == null) {
                return null;
            }
			URI uri = url.toURI().normalize();
			if (!mScaledImageMap.containsKey(uri)){
				mScaledImageMap.put(uri, new HashMap<>());
			}
			HashMap<Integer, BufferedImage> tmp = mScaledImageMap.get(uri);
			if (tmp.containsKey(size))
				return tmp.get(size);

            BufferedImage before = getImage(url);
			if (before == null)
				return null;

			int w = before.getWidth();
			int h = before.getHeight();
			BufferedImage after = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
			AffineTransform at = new AffineTransform();
			float fW = (float)size / (float)w;
			float fH = (float)size / (float)h;
			at.scale(fW, fH);
			AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			after = scaleOp.filter(before, after);

			tmp.put(size, after);
			return after;
		} catch (URISyntaxException e) {
			return null;
		}
	}

	/**
	 * Returns the Image at the given url as BufferedImage
	 * if the image has been loaded before, the same instance is returned. 
	 * @param url URL of the image to load.
	 * @return BufferedImage for given URL if found, else null.
	 */
	public static BufferedImage getImage(URL url) {
		try {
			URI uri = url.toURI().normalize();
			if (mImageMap.containsKey(uri))
				return mImageMap.get(uri); //may be null
			BufferedImage bufferedImage = ImageIO.read(url);
			mImageMap.put(uri, bufferedImage); //remember, even if null
			return bufferedImage;
		} catch (IOException | URISyntaxException e) {
			return null;
		}
	}

	/**
	 * Returns the preferred small icon size.
	 * @return Small icon size depending on current scale factor.
	 */
	public static int preferedSmallIconSize() {
		return (int) (16 * scaleFactor);
	}

	/**
	 * Returns the preferred middle icon size.
	 * @return Middle icon size depending on current scale factor.
	 */
	public static int preferedMidIconSize() {
		return (int) (24 * scaleFactor);
	}

	/**
	 * Returns the preferred big icon size.
	 * @return Big icon size depending on current scale factor.
	 */
	public static int preferedBigIconSize() {
		return (int) (32 * scaleFactor);
	}

	/**
	 * Returns an icon with a scaled image.
	 * @apiNote  the image is not buffered
	 * @param before BufferedImage to convert to icon.
	 * @param size Size of the icon.
	 * @return Icon if found, else null.
	 */
	public static Icon getIcon(BufferedImage before, int size) {
		if (before == null)
			return null;
		
		int w = before.getWidth();
		int h = before.getHeight();
		BufferedImage after = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		float fW = (float)size / (float)w;
		float fH = (float)size / (float)h;
		at.scale(fW, fH);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(before, after);
		return new ImageIcon(after);
	}

	/**
	 * Sets the scale factor for the images. This is used for calculating small, mid and big sizes
	 * for the icons.
	 * @param newScaleFactor New scale factor to set for calculating sizes.
	 */
	public static void setScaleFactor(float newScaleFactor) {
		scaleFactor = newScaleFactor;
	}

	/**
	 * Retrieves all files within a given resource (i.e. directory) and tries to load them as images.
	 * This method returns all Icons available within a diretory.
	 * @param callerClazz Caller class to resolve resource from. The class specifies the location where resources are
	 *               	  retrieved from.
	 * @param resourceName Resource path where to look for icons.
	 * @return Map of URI and BufferedImage where URI is the URI of the icon and BufferedImage is the loaded icon.
	 */
	public static Map<URI, BufferedImage> getIconSet(Class<?> callerClazz, String resourceName) {
		URL folder = ResourceManager.get(callerClazz).resolveResource(resourceName);
		Map<URI, BufferedImage> icons = new HashMap<>();
		if(folder != null) {
			try {
				switch (folder.getProtocol()) {
					case "file" -> {
						Path dir = Paths.get(folder.toURI());
						try (Stream<Path> paths = Files.list(dir)) {
							paths
									.filter(Files::isRegularFile)
									.map(p -> resourceName + "/" + p.getFileName())
									.forEach(p -> {
										Map.Entry<URI, BufferedImage> image = loadBufferedImage(callerClazz, p);
										if(image != null) {
											icons.put(image.getKey(), image.getValue());
										}
									});
						}
					}
					case "jar" -> {
						JarURLConnection conn = (JarURLConnection) folder.openConnection();
						JarFile jar = conn.getJarFile();

						Enumeration<JarEntry> entries = jar.entries();
						while (entries.hasMoreElements()) {
							JarEntry entry = entries.nextElement();
							String name = entry.getName();

							if (name.startsWith(resourceName + "/") && !entry.isDirectory()) {
								Map.Entry<URI, BufferedImage> image = loadBufferedImage(callerClazz, name);
								if(image != null) {
									icons.put(image.getKey(), image.getValue());
								}
							}
						}
					}
				}
			} catch (IOException | URISyntaxException e) {
				ULog.error("Error while loading iconset: {}.", e.getMessage());
			}

		}
		return icons;
	}

	/**
	 * Loads a BufferedImage from a given path.
	 * @param callerClazz Caller class to resolve resource from. The class specifies the location where resources are
	 *               	  retrieved from.
	 * @param path Resource path of the image to load.
	 * @return Map Entry for the loaded BufferedImage which consists of the image URI and BufferedImage loaded. If it is not found, null is returned.
	 */
	private static Map.Entry<URI, BufferedImage> loadBufferedImage(Class<?> callerClazz, String path) {
		URL url = ResourceManager.get(callerClazz).resolveResource(path);
		if(url != null) {
			try {
				BufferedImage img = ImageIO.read(url);
				if(img != null) return new AbstractMap.SimpleEntry<>(url.toURI(), img);
			} catch (IOException | URISyntaxException e) {
			}
		}
		return null;
	}

}

package de.emir.tuml.ucore.runtime.resources;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility class to load icons from resources
 * @author sschweigert
 *
 */
public class IconManager {

	private static HashMap<URL, BufferedImage>						mImageMap = new HashMap<>();
	private static HashMap<URL, HashMap<Integer, BufferedImage>>	mScaledImageMap = new HashMap<URL, HashMap<Integer, BufferedImage>>();
	protected static float scaleFactor = 1.0f;
	
	/**
	 * Returns a scaled image icon for the given location,
	 * uses the ResourceManager, to resolve a valid URL from the input string
	 * @param resourceName
	 * @param size
	 * @return
	 */
	public static ImageIcon getIcon(Object caller, String resourceName, int size){
		Class<?> callerClazz = null;
		
		if (caller == null) callerClazz = IconManager.class;
		else if (caller instanceof Class) callerClazz = (Class<? extends Object>)caller;
		else callerClazz = caller.getClass();
		
		URL _url = ResourceManager.get(callerClazz).resolveResource(resourceName);
		return getIcon(_url, size);
	}
	
	public static BufferedImage getImage(Object caller, String resourceName) {
		if (caller != null)
			return getImage(caller.getClass(), resourceName);
		else
			return getImage(ResourceManager.class, resourceName); 
	}
	public static BufferedImage getImage(Object caller, String resourceName, int size) {
		if (caller != null)
			return getImage(caller.getClass(), resourceName, size);
		else
			return getImage(ResourceManager.class, resourceName, size); 
	}
	public static BufferedImage getImage(Class<?> callerClazz, String resourceName) {
		URL _url = ResourceManager.get(callerClazz).resolveResource(resourceName);
		return getImage(_url);
	}
	public static BufferedImage getImage(Class<?> callerClazz, String resourceName, int size) {
		URL _url = ResourceManager.get(callerClazz).resolveResource(resourceName);
		return getScaledImage(_url, size);
	}
	public static ImageIcon getIcon(Object caller, String resourceName) {
		return getIcon(caller,resourceName, -1);
	}
	/**
	 * Scales the image to the requested size and creates an image icon
	 * @param url url to the image to load
	 * @param size size in pixel
	 * @return image icon with requested size
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
	 * returns the scaled image of the given resourcename, if the name could be resolved, using the ResourceManager
	 * @param resourceName
	 * @param size
	 * @return image with the given resource name, if it could be resolved, null otherwise
	 */
	public static Image getScaledImage(Object caller, String resourceName, int size) {
		Class<?> callerClazz = null;
		
		if (caller == null) callerClazz = IconManager.class;
		else if (caller instanceof Class) callerClazz = (Class<? extends Object>)caller;
		else callerClazz = caller.getClass();
		
		URL _url = ResourceManager.get(callerClazz).resolveResource(resourceName);
		if (_url == null)
			return null;
		return getScaledImage(_url, size);
	}

	/**
	 * Returns a scaled image of the image found at the given version
	 * @param url
	 * @param size
	 * @return null if the url is invalid or does not point to an image
	 */
	public static BufferedImage getScaledImage(URL url, int size) {
		if (mScaledImageMap.containsKey(url) == false){
			mScaledImageMap.put(url, new HashMap<Integer, BufferedImage>());
		}
		HashMap<Integer, BufferedImage> tmp = mScaledImageMap.get(url);
		if (tmp.containsKey(size))
			return tmp.get(size);
		
		if (url == null)
			return null;
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
	}


	/**
	 * Returns the Image at the given url as BufferedImage
	 * if the image has been loaded before, the same instance is returned. 
	 * @param url
	 * @return
	 */
	public static BufferedImage getImage(URL url) {
		try {
			if (mImageMap.containsKey(url))
				return mImageMap.get(url); //may be null
			BufferedImage bimg = ImageIO.read(url);
			mImageMap.put(url, bimg); //remember, even if null
			return bimg;
		} catch (IOException e) {
			return null;
		}
	}
	
	
	public static int preferedSmallIconSize() {
		return (int) (16 * scaleFactor);
	}
	public static int preferedMidIconSize() {
		return (int) (24 * scaleFactor);
	}
	public static int preferedBigIconSize() {
		return (int) (32 * scaleFactor);
	}
	
	
	/**
	 * returns an icon with a scalled image
	 * @note the image is not buffered 
	 * @param icon
	 * @param size
	 * @return
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

	
	public static void setScaleFactor(float newScaleFactor) {
		scaleFactor = newScaleFactor;
	}

	
}

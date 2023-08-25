package de.emir.rcp.parts.vesseleditor.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class PaintUtil {

    public static BufferedImage rotateImag(BufferedImage imag, int n) { //n rotation in gradians
        double rotationRequired = Math.toRadians(n);
        double locationX = imag.getWidth() / 2;
        double locationY = imag.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage newImage = new BufferedImage(imag.getWidth(), imag.getHeight(), imag.getType()); //20, 20 is a height and width of imag ofc
        op.filter(imag, newImage);

        //this.img = newImage;
        return (newImage);
    }
}

package com.jaisocx.imaging.helpers;



import java.awt.*;
import java.awt.image.BufferedImage;
import com.jaisocx.imaging.helpers.helper_interface.PixelHelperInterface;



public class PixelHelper extends ColorBase implements PixelHelperInterface {

    public PixelHelperInterface setRgba (
      BufferedImage bufferedImage,
      int rgba,
      int x, 
      int y
    ) {

      bufferedImage.setRGB (
        x, 
        y, 
        rgba
      );

      return this;
    }

    public PixelHelperInterface setPixel (
      BufferedImage bufferedImage,
      int r,
      int g,
      int b,
      int a,
      int x, 
      int y
    ) {

      Color colorWithAlpha = new Color ( r, g, b, a );
      int rgbOfColorWithAlpha = colorWithAlpha.getRGB();

      bufferedImage.setRGB (
        x, 
        y, 
        rgbOfColorWithAlpha
      );

      return this;
    }

    public PixelHelperInterface setColorPixel (
      BufferedImage bufferedImage,
      int Alpha, 
      String inColor, 
      int x, 
      int y
    ) {

      int rgbOfColorWithAlpha = this.getColorRgba (
        Alpha,
        inColor
      );

      bufferedImage.setRGB (
        x, 
        y, 
        rgbOfColorWithAlpha
      );



      return this;
    }

}





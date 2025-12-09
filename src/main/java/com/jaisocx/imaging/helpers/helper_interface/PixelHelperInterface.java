package com.jaisocx.imaging.helpers.helper_interface;

import java.awt.image.BufferedImage;



public interface PixelHelperInterface {

    // The Fastest
    public PixelHelperInterface setRgba (
      BufferedImage bufferedImage,
      int rgba,
      int x, 
      int y
    );

    // Faster
    public PixelHelperInterface setPixel (
      BufferedImage bufferedImage,
      int r,
      int g,
      int b,
      int a,
      int x, 
      int y
    );

    // not fast, however easy to pass color text value
    public PixelHelperInterface setColorPixel (
      BufferedImage bufferedImage,
      int Alpha, 
      String inColor, 
      int x, 
      int y
    );

}





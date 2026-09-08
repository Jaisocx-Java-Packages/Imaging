package com.jaisocx.imaging;



import java.awt.*;
import java.awt.image.BufferedImage;

import com.jaisocx.imaging.imaging_types.Dimension;



public interface BaseImagingInterface {

    // BufferedImage.TYPE_INT_ARGB
    public BufferedImage createImage ( int width, int height, int imageType );

    public BaseImagingInterface copyArea_FromImageToAnother ( 
      BufferedImage src_Img, 
      Dimension src_Dimension,
      Graphics2D dest_graphics2D, 
      int dest_X, 
      int dest_Y,
      boolean toCleanup
    );

    public BaseImagingInterface copyAreaFiltered_FromImageToAnother (
      BufferedImage src_Img,
      Dimension src_Dimension,
      BufferedImage dest_Img,
      int dest_X, 
      int dest_Y,
      int rgbaFilterBitsNumber,
      int rgbaFilterBitsOffset,
      int rgbaFilterValue,
      boolean toMatchFilter
    );

    public BaseImagingInterface cleanup( Graphics2D g );
}






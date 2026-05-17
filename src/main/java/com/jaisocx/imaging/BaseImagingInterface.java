package com.jaisocx.imaging;



import java.awt.*;
import java.awt.image.BufferedImage;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.types.Rect;



public interface BaseImagingInterface {

    // BufferedImage.TYPE_INT_ARGB
    public BufferedImage createImage ( int width, int height, int imageType );

    public BaseImagingInterface copyArea_FromImageToAnother ( 
      BufferedImage src_Img, 
      Rect src_Rect, 
      Graphics2D dest_graphics2D, 
      int dest_X, 
      int dest_Y,
      boolean toCleanup
    );

    public BaseImagingInterface copyAreaFiltered_FromImageToAnother (
      BufferedImage src_Img,
      Rect src_Rect, 
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






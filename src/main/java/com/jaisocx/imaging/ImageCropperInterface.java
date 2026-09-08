package com.jaisocx.imaging;


import java.awt.image.BufferedImage;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.imaging_types.Dimension;


public interface ImageCropperInterface extends BaseImagingInterface {

  public ImageCropperInterface crop (
      String pathOf_srcImage_from,
      String pathOf_ProducedImageParent_to,
      String nameOf_ProducedImage_to,
      Dimension srcImage_Dimension,
      ImageFormatEnum imageFormatTo,
      String versionCounter,
      boolean printsToConsole
  );

  public BufferedImage cropBufferedImage (
      BufferedImage src_Img,
      Dimension srcImage_Dimension
  );

}



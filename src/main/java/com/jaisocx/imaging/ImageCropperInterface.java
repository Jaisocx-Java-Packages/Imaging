package com.jaisocx.imaging;


import java.awt.image.BufferedImage;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;
import com.jaisocx.imaging.types.Rect;
import com.jaisocx.imaging.BaseImagingInterface;



public interface ImageCropperInterface extends BaseImagingInterface {

  public ImageCropperInterface crop (
      String pathOf_srcImage_from,
      Rect srcImage_Rect,
      ImageFormatEnum imageFormatTo,
      String versionCounter
  );

  public BufferedImage cropBufferedImage (
      BufferedImage src_Img,
      Rect srcImage_Rect
  );

}



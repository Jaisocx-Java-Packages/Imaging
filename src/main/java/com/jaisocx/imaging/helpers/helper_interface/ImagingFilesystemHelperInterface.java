package com.jaisocx.imaging.helpers.helper_interface;

import java.awt.image.BufferedImage;

import com.jaisocx.imaging.Constants.ImageFormatEnum;



public interface ImagingFilesystemHelperInterface {

  public BufferedImage read (
      String pathImageRead_from
  );

  // Save
  public long write (
      BufferedImage bufferedImageToWrite,
      ImageFormatEnum format,
      String pathImageWrite_to
  );

}



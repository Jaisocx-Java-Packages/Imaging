package com.jaisocx.imaging.helpers.helper_interface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.jaisocx.imaging.Constants.ImageFormatEnum;

import javax.imageio.ImageIO;


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



package com.jaisocx.imaging.helpers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.jaisocx.imaging.BaseImagingInterface;
import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;

import javax.imageio.ImageIO;



public class ImagingFilesystemHelper implements ImagingFilesystemHelperInterface {

  public BufferedImage read (
      String pathImageRead_from
  ) {
    File imageFile = new File( pathImageRead_from );
    BufferedImage readBufferedImage = null;

    try {
      readBufferedImage = ImageIO.read ( imageFile );
    } catch (IOException e) {
      e.printStackTrace();
    }

    imageFile = null;



    BufferedImage convertedBufferedImage = new BufferedImage (
        readBufferedImage.getWidth(),
        readBufferedImage.getHeight(),
        BufferedImage.TYPE_INT_ARGB
    );

    Graphics2D g = convertedBufferedImage.createGraphics();
    g.drawImage ( readBufferedImage, 0, 0, null );
    g.dispose();
    g = null;


    return convertedBufferedImage;
  }



  // Save
  public long write (
      BufferedImage bufferedImageToWrite,
      ImageFormatEnum format,
      String pathImageWrite_to
  ) {

      bufferedImageToWrite.flush();



      File savedFile = new File( pathImageWrite_to );

      try {
        ImageIO.write (
            bufferedImageToWrite,
            format.getFilenameExtension(),
            savedFile
        );
      } catch (IOException e) {
        e.printStackTrace();
      }

      savedFile = null;



      return 1L;
    }

}



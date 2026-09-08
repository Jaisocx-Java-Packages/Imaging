package com.jaisocx.imaging;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.imaging_types.Dimension;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;


public class ImageCropper extends BaseImaging implements ImageCropperInterface {

  protected ImagingFilesystemHelper imagingFilesystemHelper = null;



  public ImageCropper (
      ImagingFilesystemHelperInterface fsHelper
  ) {
    super();

    this.imagingFilesystemHelper = (ImagingFilesystemHelper)fsHelper;
  }



  public ImageCropperInterface crop (
      String pathOf_srcImage_from,
      String pathOf_ProducedImageParent_to,
      String nameOf_ProducedImage_to,
      Dimension srcImage_Dimension,
      ImageFormatEnum imageFormatTo,
      String versionCounter,
      boolean printsToConsole
  ) {

    if ( this.imagingFilesystemHelper == null ) {
      this.imagingFilesystemHelper = new ImagingFilesystemHelper();
    }

    BufferedImage bufImageRead = this.imagingFilesystemHelper.read( pathOf_srcImage_from );

    BufferedImage producedImage = this.cropBufferedImage (
        bufImageRead,
        srcImage_Dimension
    );

    String pathOf_producedImage_to = "";
    String filenameExtensionOf_srcImage = "";
    int dotPos = pathOf_srcImage_from.lastIndexOf( "." );

    if ( dotPos != (-1) ) {
      filenameExtensionOf_srcImage = pathOf_srcImage_from.substring( dotPos );
      pathOf_srcImage_from = pathOf_srcImage_from.substring( 0, dotPos );
    }

    if ( imageFormatTo == null ) {
      ImageFormatEnum imageFormatFrom = ImageFormatEnum.fromString( filenameExtensionOf_srcImage );
      imageFormatTo = imageFormatFrom;
    }

    File f_ProducedImageParent_to = new File( pathOf_ProducedImageParent_to );
    if ( f_ProducedImageParent_to.exists() == false ) {
      f_ProducedImageParent_to.mkdir();
    }
    f_ProducedImageParent_to = null;

    pathOf_producedImage_to = ( pathOf_ProducedImageParent_to + "/" + nameOf_ProducedImage_to + versionCounter + "." + imageFormatTo.getFilenameExtension() );

    this.imagingFilesystemHelper.write (
        producedImage,
        imageFormatTo,
        pathOf_producedImage_to
    );

    if ( printsToConsole == true) {
      System.out.println( pathOf_producedImage_to );
    }

    return this;
  }



  public BufferedImage cropBufferedImage (
      BufferedImage src_Img,
      Dimension srcImage_Dimension
  ) {

    // BufferedImage.TYPE_INT_ARGB
    BufferedImage producedImage = super.createImage (
        srcImage_Dimension.width,
        srcImage_Dimension.height,
        BufferedImage.TYPE_INT_ARGB
    );

    Graphics2D dest_graphics2D = (Graphics2D)producedImage.createGraphics();

    super.copyArea_FromImageToAnother (
        src_Img,
        srcImage_Dimension,
        dest_graphics2D,
        0,
        0,
        true
    );

    return producedImage;

  }

}



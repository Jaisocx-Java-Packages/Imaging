package com.jaisocx.imaging;


import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.ImageConverterInterface;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;

import java.awt.image.BufferedImage;


public class ImageConverter implements ImageConverterInterface {

  protected ImagingFilesystemHelper imagingFilesystemHelper = null;



  public ImageConverter(
      ImagingFilesystemHelperInterface fsHelper
  ) {
    this.imagingFilesystemHelper = (ImagingFilesystemHelper)fsHelper;
  }



  public ImageConverterInterface convert (
      String pathOf_readImage_from,
      String pathOf_convertedImage_to,
      ImageFormatEnum imageFormat_readFrom,
      ImageFormatEnum imageFormat_convertTo
  ) {

    if ( this.imagingFilesystemHelper == null ) {
      this.imagingFilesystemHelper = new ImagingFilesystemHelper();
    }

    BufferedImage bufImageRead = this.imagingFilesystemHelper.read( pathOf_readImage_from );

    this.imagingFilesystemHelper.write (
        bufImageRead,
        imageFormat_convertTo,
        pathOf_convertedImage_to
    );

    return this;
  }

}



package com.jaisocx.imaging;


import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;



public interface ImageConverterInterface {

  public ImageConverterInterface convert (
      String pathOf_readImage_from,
      String pathOf_convertedImage_to,
      ImageFormatEnum imageFormat_readFrom,
      ImageFormatEnum imageFormat_convertTo
  );

}



package com.jaisocx.imaging;

import java.awt.image.BufferedImage;

import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;

import com.jaisocx.imaging.Constants.ImageFormatEnum;

import com.jaisocx.imaging.imaging_types.Dimension;


public interface ImagingInterface {

  public ImagingFilesystemHelperInterface getImagingFilesystemHelper();

  public BaseImagingInterface getBaseImaging();
  public ImageConverterInterface getImageConverter();
  public ImageCropperInterface getImageCropper();


  public String getPathToSaveProducedImages();
  public void setPathToSaveProducedImages( String inPath );

  public ImagingInterface convert (
      String pathOf_readImage_from,
      String pathOf_convertedImage_to,
      ImageFormatEnum imageFormat_readFrom,
      ImageFormatEnum imageFormat_convertTo
  );

  public ImagingInterface crop (
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



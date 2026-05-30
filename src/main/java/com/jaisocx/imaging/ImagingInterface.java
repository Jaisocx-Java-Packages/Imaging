package com.jaisocx.imaging;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import com.jaisocx.imaging.types.Rect;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.Constants.ImagingConstants;

import com.jaisocx.imaging.BaseImagingInterface;
import com.jaisocx.imaging.BaseImaging;

import com.jaisocx.imaging.ImageCropperInterface;
import com.jaisocx.imaging.ImageCropper;



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
      Rect srcImage_Rect,
      ImageFormatEnum imageFormatTo,
      String versionCounter,
      boolean printsToConsole
  );

  public BufferedImage cropBufferedImage (
      BufferedImage src_Img,
      Rect srcImage_Rect
  );

}



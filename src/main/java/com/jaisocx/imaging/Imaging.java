package com.jaisocx.imaging;

import java.awt.image.BufferedImage;

import com.jaisocx.imaging.imaging_types.Dimension;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.Constants.ImagingConstants;


public class Imaging implements ImagingInterface {

  protected ImagingFilesystemHelper imagingFilesystemHelper = null;

  protected BaseImaging baseImaging = null;
  protected ImageConverter imageConverter = null;
  protected ImageCropper imageCropper = null;

  public String pathToSaveProducedImages = null;



  public Imaging() {
    this.pathToSaveProducedImages = ImagingConstants.getInstance().PATH_SAVE_PRODUCED_MINI_IMAGE;
  }



  public ImagingFilesystemHelperInterface getImagingFilesystemHelper() {
    if ( this.imagingFilesystemHelper == null ) {
      this.imagingFilesystemHelper = new ImagingFilesystemHelper();
    }

    return this.imagingFilesystemHelper;
  }



  public BaseImagingInterface getBaseImaging() {
    if ( this.baseImaging == null ) {
      this.baseImaging = new BaseImaging();
    }

    return this.baseImaging;
  }



  public ImageConverterInterface getImageConverter() {
    if ( this.imageConverter == null ) {

      if ( this.imagingFilesystemHelper == null ) {
        this.imagingFilesystemHelper = new ImagingFilesystemHelper();
      }

      this.imageConverter = new ImageConverter( this.imagingFilesystemHelper );
    }

    return this.imageConverter;
  }



  public ImageCropperInterface getImageCropper() {
    if ( this.imageCropper == null ) {

      if ( this.imagingFilesystemHelper == null ) {
        this.imagingFilesystemHelper = new ImagingFilesystemHelper();
      }

      this.imageCropper = new ImageCropper( this.imagingFilesystemHelper  );
    }

    return this.imageCropper;
  }


  public String getPathToSaveProducedImages() {
    return this.pathToSaveProducedImages;
  }

  public void setPathToSaveProducedImages( String inPath ) {
    this.pathToSaveProducedImages = inPath;
  }


  public ImagingInterface convert (
      String pathOf_readImage_from,
      String pathOf_convertedImage_to,
      ImageFormatEnum imageFormat_readFrom,
      ImageFormatEnum imageFormat_convertTo
  ) {
    ImageConverter localImageConverter = (ImageConverter)this.getImageConverter();

    localImageConverter.convert (
        pathOf_readImage_from,
        pathOf_convertedImage_to,
        imageFormat_readFrom,
        imageFormat_convertTo
    );

    return this;
  }



  public ImagingInterface crop (
      String pathOf_srcImage_from,
      String pathOf_ProducedImageParent_to,
      String nameOf_ProducedImage_to,
      Dimension srcImage_Dimension,
      ImageFormatEnum imageFormatTo,
      String versionCounter,
      boolean printsToConsole
  ) {

    ImageCropper localImageCropper = (ImageCropper)this.getImageCropper();

    localImageCropper.crop (
        pathOf_srcImage_from,
        pathOf_ProducedImageParent_to,
        nameOf_ProducedImage_to,
        srcImage_Dimension,
        imageFormatTo,
        versionCounter,
        printsToConsole
    );

    return this;
  }

  public BufferedImage cropBufferedImage (
      BufferedImage src_Img,
      Dimension srcImage_Dimension
  ) {
    ImageCropper localImageCropper = (ImageCropper)this.getImageCropper();

    BufferedImage producedImage = localImageCropper.cropBufferedImage (
        src_Img,
        srcImage_Dimension
    );

    return producedImage;
  }

}



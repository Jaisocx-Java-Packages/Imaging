package com.jaisocx.app;


import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.Imaging;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;
import com.jaisocx.imaging.cropping_sizes.ImagingCroppingSizes;
import com.jaisocx.imaging.imaging_types.Dimension;

import java.awt.image.BufferedImage;


public class CropperOne_MaxSize {

  public static void main (String[] args) {

    boolean printsToConsole_true = true;

    String imageVer = args[0];

    String pathOf_ProducedImageParent_to = args[6];
    String nameOf_ProducedImage_to = args[7];

    String pathOf_srcImage_from = args[8];

    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[5] );


    Imaging imaging = new Imaging();
    ImagingFilesystemHelper imagingFilesystemHelper = ( (ImagingFilesystemHelper)imaging.getImagingFilesystemHelper() );
    BufferedImage bufferedImage  = imagingFilesystemHelper.read( pathOf_srcImage_from );

    ImagingCroppingSizes imagingSizes    = new ImagingCroppingSizes();
    Dimension bufferedImageDimension = ( new Dimension() );
      bufferedImageDimension.height     = bufferedImage.getTileHeight();
      bufferedImageDimension.width      = bufferedImage.getTileWidth();
      bufferedImageDimension.x          = 0;
      bufferedImageDimension.y          = 0;

    // ImagingSizesEnum imagingSizesEnum = ImagingSizesEnum.SIZE_1_2;
    /* Dimension srcImage_Dimension = imagingSizes.getCroppedMaxSizesByEnum (
        bufferedImageDimension,
        ImagingSizesEnum.SIZE_1_2
    ); */



    Dimension srcImage_Dimension = imagingSizes.getCroppedMaxSizesByNumbers (
        bufferedImageDimension,
        Integer.valueOf( args[4] ),
        Integer.valueOf( args[3] )
    );

    srcImage_Dimension.x = Integer.valueOf( args[2] );
    srcImage_Dimension.y = Integer.valueOf( args[1] );

    if ( ( srcImage_Dimension.y == 0 ) && ( srcImage_Dimension.height < bufferedImageDimension.height ) ) {
      int diffHeight = ( bufferedImageDimension.height - srcImage_Dimension.height );
      int halfDiffHeight = ( diffHeight >>> 1 );
      srcImage_Dimension.y = halfDiffHeight;

    } else if ( ( srcImage_Dimension.x == 0 ) && ( srcImage_Dimension.width < bufferedImageDimension.width ) ) {
      int diffWidth = ( bufferedImageDimension.width - srcImage_Dimension.width );
      int halfDiffWidth = ( diffWidth >>> 1 );
      srcImage_Dimension.x = halfDiffWidth;

    }



    imaging.crop (
        pathOf_srcImage_from,
        pathOf_ProducedImageParent_to,
        nameOf_ProducedImage_to,
        srcImage_Dimension,
        imageFormatTo,
        imageVer,
        printsToConsole_true
    );

  }

}



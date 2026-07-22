package com.jaisocx.app;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.Imaging;

import com.jaisocx.imaging.types.Rect;



public class CropperOne {

  public static void main (String[] args) {

    boolean printsToConsole_true = true;

    Rect srcImage_Rect = new Rect();
    srcImage_Rect.x = Integer.valueOf( args[2] );
    srcImage_Rect.y = Integer.valueOf( args[1] );
    srcImage_Rect.height = Integer.valueOf( args[3] );
    srcImage_Rect.width = Integer.valueOf( args[4] );

    String imageVer = args[0];

    String pathOf_ProducedImageParent_to = args[6];
    String nameOf_ProducedImage_to = args[7];

    String pathOf_srcImage_from = args[8];

    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[5] );



    Imaging imaging = new Imaging();

    imaging.crop (
      pathOf_srcImage_from,
      pathOf_ProducedImageParent_to,
      nameOf_ProducedImage_to,
      srcImage_Rect,
      imageFormatTo,
      imageVer,
      printsToConsole_true
    );

  }

}



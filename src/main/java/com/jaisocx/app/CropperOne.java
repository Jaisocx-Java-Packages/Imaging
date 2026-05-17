package com.jaisocx.app;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.types.Rect;
import com.jaisocx.imaging.Imaging;


public class CropperOne {

  public static void main (String[] args) {

    Rect srcImage_Rect = new Rect();
    srcImage_Rect.x = Integer.valueOf( args[1] );
    srcImage_Rect.y = Integer.valueOf( args[2] );
    srcImage_Rect.width = Integer.valueOf( args[3] );
    srcImage_Rect.height = Integer.valueOf( args[4] );

    String versionCounter = args[0];

    String pathOf_srcImage_from = args[6];

    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[5] );



    Imaging imaging = new Imaging();

    imaging.crop (
      pathOf_srcImage_from,
      srcImage_Rect,
      imageFormatTo,
      versionCounter
    );
  }

}



package com.jaisocx.app;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.ImageCropperMultiple;
import com.jaisocx.imaging.types.Rect;



/* @in_engineering, needs bugfixes... */
public class CropperOneMultiple {

  public static void main (String[] args) {

    boolean b_printsToConsole_true = true;

    String cli_imageVer = args[0];

    String cli_pathOf_srcImage_from = args[16];
    String cli_pathOf_ProducedImageParent_to = args[14];
    String cli_nameOf_ProducedImage_to = args[15];
    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[5] );


    Integer sInt = Integer.valueOf(1);
    
    Rect srcImage_Rect = new Rect();
    srcImage_Rect.y = sInt.parseInt( args[1] );
    srcImage_Rect.x = sInt.parseInt( args[2] );
    srcImage_Rect.height = sInt.parseInt( args[3] );
    srcImage_Rect.width = sInt.parseInt( args[4] );

    int cli_stepNextTry_pos_x = sInt.parseInt( args[6] );
    int cli_stepNextTry_pos_y = sInt.parseInt( args[7] );

    int cli_stepNextTry_size_h = sInt.parseInt( args[8] );
    int cli_stepNextTry_size_w = sInt.parseInt( args[9] );

    int cli_trialsNumber_pos_x = sInt.parseInt( args[10] );
    int cli_trialsNumber_pos_y = sInt.parseInt( args[11] );

    int cli_trialsNumber_size_h = sInt.parseInt( args[12] );
    int cli_trialsNumber_size_w = sInt.parseInt( args[13] );

    boolean b_offsetRemainsMiddle_false = false;
    boolean b_offsetRemainsMiddle_true = true;



    ImageCropperMultiple imageCropperMultiple = ( new ImageCropperMultiple() );
    int cropped = imageCropperMultiple
        .croppingMultiple (
            cli_imageVer,
            cli_pathOf_srcImage_from,
            cli_pathOf_ProducedImageParent_to,
            cli_nameOf_ProducedImage_to,
            imageFormatTo,
            srcImage_Rect,
            cli_stepNextTry_pos_x,
            cli_stepNextTry_pos_y,
            cli_stepNextTry_size_h,
            cli_stepNextTry_size_w,
            cli_trialsNumber_pos_x,
            cli_trialsNumber_pos_y,
            cli_trialsNumber_size_h,
            cli_trialsNumber_size_w,
            b_offsetRemainsMiddle_false,
            b_printsToConsole_true
        );

    return;
  }
}



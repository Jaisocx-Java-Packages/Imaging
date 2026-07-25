package com.jaisocx.app;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.ImageCropperMultiple;
import com.jaisocx.imaging.types.Rect;



public class CropperOneMultiple {

  public static void main (String[] args) {

    boolean b_printsToConsole_true = true;

    String cli_imageVer = args[0];

    String cli_pathOf_srcImage_from = args[16];
    String cli_pathOf_ProducedImageParent_to = args[14];
    String cli_nameOf_ProducedImage_to = args[15];
    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[5] );



    Rect srcImage_Rect = new Rect();
    srcImage_Rect.y = Integer.valueOf( args[1] );
    srcImage_Rect.x = Integer.valueOf( args[2] );
    srcImage_Rect.height = Integer.valueOf( args[3] );
    srcImage_Rect.width = Integer.valueOf( args[4] );

    Integer cli_stepNextTry_pos_x = Integer.valueOf( args[6] );
    Integer cli_stepNextTry_pos_y = Integer.valueOf( args[7] );

    Integer cli_stepNextTry_size_h = Integer.valueOf( args[8] );
    Integer cli_stepNextTry_size_w = Integer.valueOf( args[9] );

    Integer cli_trialsNumber_pos_x = Integer.valueOf( args[10] );
    Integer cli_trialsNumber_pos_y = Integer.valueOf( args[11] );

    Integer cli_trialsNumber_size_h = Integer.valueOf( args[12] );
    Integer cli_trialsNumber_size_w = Integer.valueOf( args[13] );

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



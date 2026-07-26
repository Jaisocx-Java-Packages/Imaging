package com.jaisocx.app;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.ImageCropperMultiple;
import com.jaisocx.imaging.types.Rect;



/* @in_engineering, needs bugfixes... */
public class CropperOneMultiple {

  public static void main (String[] args) {

    String cli_printsToConsole = args[0];
    boolean b_printsToConsole = cli_printsToConsole.equals( "--print" );

    String cli_imageVer = args[1];

    String cli_pathOf_srcImage_from = args[18];
    String cli_pathOf_ProducedImageParent_to = args[16];
    String cli_nameOf_ProducedImage_to = args[17];
    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[6] );

    String cli_offsetRemainsMiddle = args[7];
    boolean b_offsetRemainsMiddle = cli_offsetRemainsMiddle.equals( "--middle" );


    Integer sInt = Integer.valueOf(1);
    
    Rect srcImage_Rect = new Rect();
    srcImage_Rect.y = sInt.parseInt( args[2] );
    srcImage_Rect.x = sInt.parseInt( args[3] );
    srcImage_Rect.height = sInt.parseInt( args[4] );
    srcImage_Rect.width = sInt.parseInt( args[5] );

    int cli_stepNextTry_pos_x = sInt.parseInt( args[8] );
    int cli_stepNextTry_pos_y = sInt.parseInt( args[9] );

    int cli_stepNextTry_size_h = sInt.parseInt( args[10] );
    int cli_stepNextTry_size_w = sInt.parseInt( args[11] );

    int cli_trialsNumber_pos_x = sInt.parseInt( args[12] );
    int cli_trialsNumber_pos_y = sInt.parseInt( args[13] );

    int cli_trialsNumber_size_h = sInt.parseInt( args[14] );
    int cli_trialsNumber_size_w = sInt.parseInt( args[15] );



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
            b_offsetRemainsMiddle,
            b_printsToConsole
        );

    return;
  }
}



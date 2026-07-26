package com.jaisocx.example_dbg;


import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.ImageCropperMultiple;
import com.jaisocx.imaging.types.Rect;


/* @in_engineering, needs bugfixes... */
public class ClsDbgCropperOneMultiple {

  public static void main (String[] args) {

    boolean b_printsToConsole_true = true;

    String cli_imageVer = "_d";

    String cli_pathOf_srcImage_from = "./src/main/resources/images/Neolite__figma_com.png";
    String cli_pathOf_ProducedImageParent_to = "./produced/neolite_b_multiple";
    String cli_nameOf_ProducedImage_to = "neolite_b_multiple";
    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( "png" );



    Integer sInt = Integer.valueOf(1);

    Rect srcImage_Rect = new Rect();
    srcImage_Rect.y           = sInt.parseInt( "140" );
    srcImage_Rect.x           = sInt.parseInt( "300" );
    srcImage_Rect.height      = sInt.parseInt( "400" );
    srcImage_Rect.width       = sInt.parseInt( "420" );

    int cli_stepNextTry_pos_x = sInt.parseInt( "5" );
    int cli_stepNextTry_pos_y = sInt.parseInt( "1" );

    int cli_stepNextTry_size_h = sInt.parseInt( "1" );
    int cli_stepNextTry_size_w = sInt.parseInt( "1" );

    int cli_trialsNumber_pos_x = sInt.parseInt( "10" );
    int cli_trialsNumber_pos_y = sInt.parseInt( "1" );

    int cli_trialsNumber_size_h = sInt.parseInt( "1" );
    int cli_trialsNumber_size_w = sInt.parseInt( "1" );

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
            b_offsetRemainsMiddle_true,
            b_printsToConsole_true
        );

    return;
  }
}



package com.jaisocx.example_dbg;


import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.ImageCropperMultiple;
import com.jaisocx.imaging.types.Rect;


public class ClsDbgCropperOneMultiple {

  public static void main (String[] args) {

    boolean b_printsToConsole_true = true;

    String cli_imageVer = "_d";

    String cli_pathOf_srcImage_from = "./src/main/resources/images/Neolite__figma_com.png";
    String cli_pathOf_ProducedImageParent_to = "./produced/neolite_b_multiple";
    String cli_nameOf_ProducedImage_to = "neolite_b_multiple";
    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( "png" );



    Rect srcImage_Rect = new Rect();
    srcImage_Rect.y = Integer.valueOf( 120 );
    srcImage_Rect.x = Integer.valueOf( 420 );
    srcImage_Rect.height = Integer.valueOf( 400 );
    srcImage_Rect.width = Integer.valueOf( 420 );

    int cli_stepNextTry_pos_x = Integer.valueOf( 5 );
    int cli_stepNextTry_pos_y = Integer.valueOf( 1 );

    int cli_stepNextTry_size_h = Integer.valueOf( 1 );
    int cli_stepNextTry_size_w = Integer.valueOf( 1 );

    int cli_trialsNumber_pos_x = Integer.valueOf( 10 );
    int cli_trialsNumber_pos_y = Integer.valueOf( 1 );

    int cli_trialsNumber_size_h = Integer.valueOf( 1 );
    int cli_trialsNumber_size_w = Integer.valueOf( 1 );

    boolean b_offsetRemainsMiddle = false;



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
            b_printsToConsole_true
        );

    return;
  }
}



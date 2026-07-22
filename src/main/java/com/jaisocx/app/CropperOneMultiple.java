package com.jaisocx.app;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jaisocx.tools.combiner.Combiner;


import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;
import com.jaisocx.imaging.Imaging;

import com.jaisocx.imaging.types.Rect;
import com.jaisocx.helpers.JaisocxArrayHelper;


public class CropperOneMultiple {

  public static void main (String[] args) {

    boolean printsToConsole_true = true;

    Rect srcImage_Rect = new Rect();
    srcImage_Rect.y = Integer.valueOf( args[1] );
    srcImage_Rect.x = Integer.valueOf( args[2] );
    srcImage_Rect.height = Integer.valueOf( args[3] );
    srcImage_Rect.width = Integer.valueOf( args[4] );


    String imageVer = args[0];

    String pathOf_ProducedImageParent_to = args[14];
    String cli_nameOf_ProducedImage_to = args[15];
    String nameOf_ProducedImage_to = cli_nameOf_ProducedImage_to;
    String pathOf_ProducedImage_to = "./img.png";

    String pathOf_srcImage_from = args[16];

    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[5] );
    String filenameExtensionOf_producedImage_to = imageFormatTo.getFilenameExtension();
    long written = 1L;



    int stepNextTry_pos_x = Integer.valueOf( args[6] );
    int stepNextTry_pos_y = Integer.valueOf( args[7] );

    int stepNextTry_size_h = Integer.valueOf( args[8] );
    int stepNextTry_size_w = Integer.valueOf( args[9] );

    int trialsNumber_pos_x = Integer.valueOf( args[10] );
    int trialsNumber_pos_y = Integer.valueOf( args[11] );

    int trialsNumber_size_h = Integer.valueOf( args[12] );
    int trialsNumber_size_w = Integer.valueOf( args[13] );



    Combiner combiner = new Combiner();
    Integer[] x = new Integer[] {
        ( srcImage_Rect.x ),
        ( srcImage_Rect.x + stepNextTry_pos_x ),
        ( srcImage_Rect.x + ( stepNextTry_pos_x * 2 ) ),
        ( srcImage_Rect.x + ( stepNextTry_pos_x * 3 ) ),
        ( srcImage_Rect.x + ( stepNextTry_pos_x * 4 ) )
    };

    Integer[] y = new Integer[] {
        ( srcImage_Rect.y ),
        // ( srcImage_Rect.y + stepNextTry_pos_y ),
    };

    Integer[] sizes_h = new Integer[] {
        ( srcImage_Rect.height ),
        // ( srcImage_Rect.height + stepNextTry_size_h ),

    };

    Integer[] sizes_w = new Integer[] {
        ( srcImage_Rect.width ),
        // ( srcImage_Rect.width + stepNextTry_size_w ),
    };

    List<Integer> list_x = Arrays.asList( x );
    List<Integer> list_y = Arrays.asList( y );
    List<Integer> listSizes_h = Arrays.asList( sizes_h );
    List<Integer> listSizes_w = Arrays.asList( sizes_w );

    List<List<Object>> combinations = Combiner.combine (
        list_x,
        list_y,
        listSizes_h,
        listSizes_w
    );


    Imaging imaging = new Imaging();
    ImagingFilesystemHelper fsHelper = (ImagingFilesystemHelper) imaging.getImagingFilesystemHelper();
    BufferedImage bImg = fsHelper.read (
        pathOf_srcImage_from
    );

    String templateHtmlImagesBlock = new String();
    File f_templateHtmlImagesBlock = new File( "./src/main/resources/templates/produced_images.html" );
    long templateFileSize = f_templateHtmlImagesBlock.length();
    int i_templateFileSize = (int)templateFileSize;
    FileReader fileReader = null;
    char[] cbuf = new char[i_templateFileSize];
    try {
      fileReader = new FileReader( f_templateHtmlImagesBlock );
      int hasRead = fileReader.read( cbuf, 0, i_templateFileSize );
    } catch( Exception e ) {
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
    }

    try {
      fileReader.close();
    } catch( Exception e ){
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
    }

    f_templateHtmlImagesBlock = null;
    fileReader = null;

    templateHtmlImagesBlock = String.copyValueOf( cbuf );



    String[] str_template_a = templateHtmlImagesBlock.split( "\\{\\{ htmlImagesBlock \\}\\}" );
    String[] str_template_b = new String[]{
        str_template_a[0],
        templateHtmlImagesBlock,
        str_template_a[1]
    };
    List<String> htmlTemplate = Arrays.asList( str_template_b );
    ArrayList<String> a_htmlTemplate = ( new ArrayList<String>( htmlTemplate ) );

    String[] str_htmlImagesBlock = new String[]{ "" };
    List<String> htmlImagesBlock = Arrays.asList( str_htmlImagesBlock );
    ArrayList<String> io_htmlImagesBlock = ( new ArrayList<String>( htmlImagesBlock ) );


    combinations.forEach(combinationItem -> CropperOneMultiple.produceCombined (
        pathOf_ProducedImageParent_to,
        cli_nameOf_ProducedImage_to,
        bImg,
        (Integer) combinationItem.get(0),
        (Integer) combinationItem.get(1),
        (Integer) combinationItem.get(2),
        (Integer) combinationItem.get(3),
        imaging,
        fsHelper,
        imageFormatTo,
        filenameExtensionOf_producedImage_to,
        srcImage_Rect,
        io_htmlImagesBlock
      )
    );



    String s_htmlImagesBlock = JaisocxArrayHelper.concat( io_htmlImagesBlock );
    a_htmlTemplate.set( 1, s_htmlImagesBlock );

    String s_html = JaisocxArrayHelper.concat( a_htmlTemplate );

    String fname = "./produced/" + cli_nameOf_ProducedImage_to + ".html";
    File f_htmlPreview = new File( fname );
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter( f_htmlPreview );
      fileWriter.write( s_html );
    } catch( Exception e ){
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
    }

    try {
      fileWriter.close();
    } catch( Exception e ){
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
    }

    f_htmlPreview = null;
    fileWriter = null;



    /*
    BufferedImage producedBufImage = null;

    int posX = 1;
    int counter = 1;
    int trialIx = 1;
    int imageCounter = 1;
    int COUNTER_CORRECTION = 2;
    int IMG_COUNTER_CORRECTION = 1;

    int trialsNumber_max = trialsNumber_pos_x;

    /* Start of block:
         trials x position by step of next try position distance
         in real raster px colour points
    *
    ci_trials_posX: while ( trialIx < trialsNumber_max ) {
      counter++;
      trialIx = ( counter - COUNTER_CORRECTION );
      if ( trialIx >= trialsNumber_max ) {
        break ci_trials_posX;
      }

      imageCounter = ( counter - IMG_COUNTER_CORRECTION );
      nameOf_ProducedImage_to = (
          cli_nameOf_ProducedImage_to
          + "_by_step_x_"
          + stepNextTry_pos_x
          + "_"
          + imageCounter
      );

      posX = srcImage_Rect.x;
      srcImage_Rect.x = ( posX + stepNextTry_pos_x );

      producedBufImage = imaging.cropBufferedImage (
          bImg,
          srcImage_Rect
      );



      pathOf_ProducedImage_to = (
          pathOf_ProducedImageParent_to
              + "/" + nameOf_ProducedImage_to
              + "." + filenameExtensionOf_producedImage_to
      );

      written = fsHelper.write (
          producedBufImage,
          imageFormatTo,
          pathOf_ProducedImage_to
      );



      /* temporarily paths aren't configured in ./.env.
           the plan is, to produce html preview page by template renderer,
           nearly same like for now the ./produced_images.html
      *
      pathOf_ProducedImage_to = (
          "./produced/preview/"
              + "image"
              + "_"
              + Integer.toString( imageCounter )
              + "."
              + filenameExtensionOf_producedImage_to
      );

      written = fsHelper.write (
          producedBufImage,
          imageFormatTo,
          pathOf_ProducedImage_to
      );



      continue ci_trials_posX;
    }
    /* End of block:
         trials x position by step of next try position distance
         in real raster px colour points
    *

    */

  }



  public static long produceCombined (
      String pathOf_ProducedImageParent_to,
      String cli_nameOf_ProducedImage_to,
      BufferedImage bImg,
      Integer x,
      Integer y,
      Integer h,
      Integer w,
      Imaging imaging,
      ImagingFilesystemHelper fsHelper,
      ImageFormatEnum imageFormatTo,
      String filenameExtensionOf_producedImage_to,
      Rect srcImage_Rect,
      ArrayList<String> io_htmlImagesBlock
  ) {

    BufferedImage producedBufImage = null;

    String nameOf_ProducedImage_to = "";
    String pathOf_ProducedImage_to = "";
    String abs_pathOf_ProducedImage_to = "";

    long written = 1L;
    String imagesBlockHtml = "";

    String imageHtml_a = "";
    String imageHtml_b = "";

    String template = "    <item_image>\n" +
        "      <img\n" +
        "          title=\"{{ nameOf_ProducedImage_to }}\"\n" +
        "          src=\"file://{{ abs_pathOf_ProducedImage_to }}\"\n" +
        "          onerror=\"javascript: ( () => { this.style.display = 'none'; this.onerror = null; } )();\"\n" +
        "      />\n" +
        "    </item_image>\n\n";

    nameOf_ProducedImage_to = (
        cli_nameOf_ProducedImage_to
            + "_x_"
            + x
            + "_y_"
            + y
            + "_"
            + w
            + "x"
            + h
    );

    srcImage_Rect.x = x;
    srcImage_Rect.y = y;
    srcImage_Rect.height = h;
    srcImage_Rect.width = w;

    producedBufImage = imaging.cropBufferedImage (
        bImg,
        srcImage_Rect
    );



    pathOf_ProducedImage_to = (
        pathOf_ProducedImageParent_to
            + "/" + nameOf_ProducedImage_to
            + "." + filenameExtensionOf_producedImage_to
    );

    File file = new File( pathOf_ProducedImage_to );
    try {
      abs_pathOf_ProducedImage_to = file.getCanonicalPath();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    file = null;



    written = fsHelper.write (
        producedBufImage,
        imageFormatTo,
        pathOf_ProducedImage_to
    );



      /* temporarily paths aren't configured in ./.env.
           the plan is, to produce html preview page by template renderer,
           nearly same like for now the ./produced_images.html
      */
    pathOf_ProducedImage_to = (
        "./produced/preview/"
            + "image"
            + "_"
            + Integer.toString( x )
            + "."
            + filenameExtensionOf_producedImage_to
    );

    written = fsHelper.write (
        producedBufImage,
        imageFormatTo,
        pathOf_ProducedImage_to
    );




    imageHtml_a = template.replaceFirst( "\\{\\{ nameOf_ProducedImage_to \\}\\}", nameOf_ProducedImage_to );
    imageHtml_b = imageHtml_a.replaceFirst( "\\{\\{ abs_pathOf_ProducedImage_to \\}\\}", abs_pathOf_ProducedImage_to );

    io_htmlImagesBlock.add( imageHtml_b );

    return 1L;
  }

}



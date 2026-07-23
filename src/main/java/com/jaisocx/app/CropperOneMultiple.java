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
import com.jaisocx.tools.combiner.CombinerFlatArrays;


public class CropperOneMultiple {

  public String tpl_HtmlPreviewSPA = new String();
  public String tpl_imageHtml = new String();

  public static void main (String[] args) {

    if ( args.length < 3 ) {
      return;
    }

    CropperOneMultiple cropperOneMultiple = ( new CropperOneMultiple() );



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

    boolean offsetRemainsMiddle = false;



    Combiner combiner = new Combiner();
    CombinerFlatArrays combinerFlatArrays = new CombinerFlatArrays();

    Imaging imaging = new Imaging();
    ImagingFilesystemHelper fsHelper = (ImagingFilesystemHelper) imaging.getImagingFilesystemHelper();
    BufferedImage bImg = fsHelper.read (
        pathOf_srcImage_from
    );


    ArrayList<Integer> list_x = combinerFlatArrays.getIntegerFlatArray (
        srcImage_Rect.x,
        stepNextTry_pos_x,
        trialsNumber_pos_x,
        0,
        bImg.getWidth(),
        offsetRemainsMiddle
    );

    ArrayList<Integer> list_y = combinerFlatArrays.getIntegerFlatArray (
        srcImage_Rect.y,
        stepNextTry_pos_y,
        trialsNumber_pos_y,
        0,
        bImg.getHeight(),
        offsetRemainsMiddle
    );

    ArrayList<Integer> listSizes_h = combinerFlatArrays.getIntegerFlatArray (
        srcImage_Rect.height,
        stepNextTry_size_h,
        trialsNumber_size_h,
        0,
        bImg.getHeight(),
        offsetRemainsMiddle
    );

    ArrayList<Integer> listSizes_w = combinerFlatArrays.getIntegerFlatArray (
        srcImage_Rect.width,
        stepNextTry_size_w,
        trialsNumber_size_w,
        0,
        bImg.getWidth(),
        offsetRemainsMiddle
    );


    List<List<Object>> combinations = Combiner.combine (
        list_x,
        list_y,
        listSizes_h,
        listSizes_w
    );


    char[] cbuf = (new char[]{});
    cbuf = cropperOneMultiple.readHtmlFile( "./src/main/resources/templates/produced_images.html" );
    cropperOneMultiple.tpl_HtmlPreviewSPA = String.copyValueOf( cbuf );

    cbuf = cropperOneMultiple.readHtmlFile( "./src/main/resources/templates/image.html" );
    cropperOneMultiple.tpl_imageHtml = String.copyValueOf( cbuf );



    String[] str_template_a = cropperOneMultiple.tpl_HtmlPreviewSPA.split( "\\{\\{ htmlImagesBlock \\}\\}" );
    String[] str_template_b = new String[] {
        str_template_a[0],
        "< tpl image >",
        str_template_a[1]
    };
    List<String> htmlTemplate = Arrays.asList( str_template_b );
    ArrayList<String> a_htmlTemplate = ( new ArrayList<String>( htmlTemplate ) );

    String[] str_htmlImagesBlock = new String[]{ "" };
    List<String> htmlImagesBlock = Arrays.asList( str_htmlImagesBlock );
    ArrayList<String> io_htmlImagesBlock = ( new ArrayList<String>( htmlImagesBlock ) );



    combinations.forEach(combinationItem -> cropperOneMultiple.produceCombined (
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

  }


  public char[] readHtmlFile (
      String tplPath
  ) {

    File f_tpl = new File( tplPath );
    long tpl_FileSize = f_tpl.length();
    int i_tpl_FileSize = (int)tpl_FileSize;
    FileReader fileReader = null;
    char[] cbuf = new char[i_tpl_FileSize];

    try {
      fileReader = new FileReader( f_tpl );
      int hasRead = fileReader.read( cbuf, 0, i_tpl_FileSize );

      if ( hasRead < 10 ) {
        throw new RuntimeException( "Template html wasn't found" );
      }

    } catch( Exception e ) {
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
      throw new RuntimeException( e );
    }


    try {
      fileReader.close();
    } catch( Exception e ){
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
    }

    f_tpl = null;
    fileReader = null;

    return cbuf;
  }



  public long produceCombined (
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


    imageHtml_a = this.tpl_imageHtml.replaceAll( "\\{\\{ nameOf_ProducedImage_to \\}\\}", nameOf_ProducedImage_to );
    imageHtml_b = imageHtml_a.replaceFirst( "\\{\\{ abs_pathOf_ProducedImage_to \\}\\}", abs_pathOf_ProducedImage_to );

    imageHtml_a = imageHtml_b.replaceFirst( "\\{\\{ x \\}\\}", Integer.toString( srcImage_Rect.x, 10 ) );
    imageHtml_b = imageHtml_a.replaceFirst( "\\{\\{ y \\}\\}", Integer.toString( srcImage_Rect.y, 10 ) );
    imageHtml_a = imageHtml_b.replaceFirst( "\\{\\{ h \\}\\}", Integer.toString( srcImage_Rect.height, 10 ) );
    imageHtml_b = imageHtml_a.replaceFirst( "\\{\\{ w \\}\\}", Integer.toString( srcImage_Rect.width, 10 ) );

    io_htmlImagesBlock.add( imageHtml_b );

    return 1L;
  }

}



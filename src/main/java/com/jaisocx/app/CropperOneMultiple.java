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

  public String path_tpl_HtmlPreviewSPA = "./src/main/resources/templates/produced_images.html";
  public String path_tpl_imageHtml = "./src/main/resources/templates/image.html";

  public String tpl_HtmlPreviewSPA = new String();
  public String tpl_imageHtml = new String();



  public static void main (String[] args) {

    if ( args.length < 2 ) {
      return;
    }


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

    int cli_stepNextTry_pos_x = Integer.valueOf( args[6] );
    int cli_stepNextTry_pos_y = Integer.valueOf( args[7] );

    int cli_stepNextTry_size_h = Integer.valueOf( args[8] );
    int cli_stepNextTry_size_w = Integer.valueOf( args[9] );

    int cli_trialsNumber_pos_x = Integer.valueOf( args[10] );
    int cli_trialsNumber_pos_y = Integer.valueOf( args[11] );

    int cli_trialsNumber_size_h = Integer.valueOf( args[12] );
    int cli_trialsNumber_size_w = Integer.valueOf( args[13] );

    boolean b_offsetRemainsMiddle = false;



    CropperOneMultiple cropperOneMultiple = ( new CropperOneMultiple() );
    int cropped = cropperOneMultiple
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



  public int croppingMultiple (
      String cli_imageVer,
      String cli_pathOf_srcImage_from,
      String cli_pathOf_ProducedImageParent_to,
      String cli_nameOf_ProducedImage_to,
      ImageFormatEnum imageFormatTo,
      Rect srcImage_Rect,
      int cli_stepNextTry_pos_x,
      int cli_stepNextTry_pos_y,
      int cli_stepNextTry_size_h,
      int cli_stepNextTry_size_w,
      int cli_trialsNumber_pos_x,
      int cli_trialsNumber_pos_y,
      int cli_trialsNumber_size_h,
      int cli_trialsNumber_size_w,
      boolean b_offsetRemainsMiddle,
      boolean b_printsToConsole
  ) {

    int retVal = 3;

    String nameOf_ProducedImage_to = cli_nameOf_ProducedImage_to;
    String pathOf_ProducedImage_to = "./img.png";

    String filenameExtensionOf_producedImage_to = imageFormatTo.getFilenameExtension();

    Imaging imaging = new Imaging();
    ImagingFilesystemHelper fsHelper = (ImagingFilesystemHelper) imaging.getImagingFilesystemHelper();
    BufferedImage bImg = fsHelper.read (
        cli_pathOf_srcImage_from
    );

    Combiner combiner = new Combiner();
    CombinerFlatArrays combinerFlatArrays = new CombinerFlatArrays();

    ArrayList<Integer> list_x = combinerFlatArrays.getIntegerFlatArray (
        srcImage_Rect.x,
        cli_stepNextTry_pos_x,
        cli_trialsNumber_pos_x,
        0,
        bImg.getWidth(),
        b_offsetRemainsMiddle
    );

    ArrayList<Integer> list_y = combinerFlatArrays.getIntegerFlatArray (
        srcImage_Rect.y,
        cli_stepNextTry_pos_y,
        cli_trialsNumber_pos_y,
        0,
        bImg.getHeight(),
        b_offsetRemainsMiddle
    );

    ArrayList<Integer> listSizes_h = combinerFlatArrays.getIntegerFlatArray (
        srcImage_Rect.height,
        cli_stepNextTry_size_h,
        cli_trialsNumber_size_h,
        0,
        bImg.getHeight(),
        b_offsetRemainsMiddle
    );

    ArrayList<Integer> listSizes_w = combinerFlatArrays.getIntegerFlatArray (
        srcImage_Rect.width,
        cli_stepNextTry_size_w,
        cli_trialsNumber_size_w,
        0,
        bImg.getWidth(),
        b_offsetRemainsMiddle
    );


    List<List<Object>> combinations = Combiner.combine (
        list_x,
        list_y,
        listSizes_h,
        listSizes_w
    );



    char[] cbuf_tpl_HtmlPreviewSPA = (new char[]{});
    cbuf_tpl_HtmlPreviewSPA = this.readHtmlFile( this.path_tpl_HtmlPreviewSPA );
    this.tpl_HtmlPreviewSPA = String.copyValueOf( cbuf_tpl_HtmlPreviewSPA );

    char[] cbuf_tpl_imageHtml = (new char[]{});
    cbuf_tpl_imageHtml = this.readHtmlFile( this.path_tpl_imageHtml );
    this.tpl_imageHtml = String.copyValueOf( cbuf_tpl_imageHtml );



    String[] str_template_a = this.tpl_HtmlPreviewSPA.split( "\\{\\{ htmlImagesBlock \\}\\}" );
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



    combinations.forEach(combinationItem -> this.produceCombined (
        cli_pathOf_ProducedImageParent_to,
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
    } catch ( Exception e ){
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
      throw new RuntimeException( e );
    }

    try {
      fileWriter.close();
    } catch ( Exception e ){
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
      throw new RuntimeException( e );
    }

    f_htmlPreview = null;
    fileWriter = null;


    return retVal;
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
    } catch ( IOException e) {
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
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

    } catch ( Exception e ) {
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
      throw new RuntimeException( e );
    }


    try {
      fileReader.close();
    } catch ( Exception e ){
      Logger.getLogger("logger_jaisocx_imaging").log (Level.ALL, e.getMessage() );
      throw new RuntimeException( e );
    }

    f_tpl = null;
    fileReader = null;

    return cbuf;
  }

}



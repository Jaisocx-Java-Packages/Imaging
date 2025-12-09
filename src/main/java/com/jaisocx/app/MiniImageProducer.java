package com.jaisocx.app;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import com.jaisocx.imaging.BaseImaging;
import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.Constants.ImagingConstants;
import com.jaisocx.imaging.helpers.ColorBase;
import com.jaisocx.imaging.helpers.ColorHelper;
import com.jaisocx.imaging.helpers.PixelHelper;
import com.jaisocx.imaging.helpers.ShapeHelper;
import com.jaisocx.imaging.helpers.TextHelper;
import com.jaisocx.imaging.types.Rect;



public class MiniImageProducer {

    public static void main (String[] args) {

        System.out.println ( "-------------------------------\n" );
        System.out.println ( "Hello" );

        MiniImageProducer producer = new MiniImageProducer();

        String producedImageFileBasename = "mimeWithSticker_223";
        ImageFormatEnum format = ImageFormatEnum.PNG;

        int imgWidth  = 32;
        int imgHeight = 32;
        int imgType = BufferedImage.TYPE_INT_ARGB;
        String logoText = "</>";
        String logoFontfilePath = "/Users/illiapolianskyi/Projects/Projects2025/SitesTechno/MotivationsSchreiben/www/letter_first/fonts/Orbitron/static/Orbitron-SemiBold.ttf";
        float logoFontSize = (float)14.2;
        String logoTextColor = "#ffffff";
        int borderWidthPx = 1;
        int bgColorAlpha = 0;
        String bgColor = "#112233";

        boolean toCenter_Sticker_true = true;
        boolean toMatchFilter_true = true;



        // long retVal = producer.imageRasterUse (
        //   imgHeight,
        //   imgWidth,
        //   BufferedImage.TYPE_INT_ARGB_PRE,
        //   producedImageFileBasename,
        //   format
        // );



        // long retVal = producer.writeMiniImage (
        //   imgHeight,
        //   imgWidth,
        //   BufferedImage.TYPE_INT_ARGB,
        //   logoText,
        //   logoFontfilePath,
        //   logoFontSize,
        //   logoTextColor,
        //   borderWidthPx,
        //   bgColorAlpha,
        //   bgColor,
        //   producedImageFileBasename,
        //   format
        // );



        // long retVal = producer.roundedArea (
        //   imgHeight,
        //   imgWidth,
        //   borderPxNumber,
        //   producedImageFileBasename,
        //   "#e5e5e5"
        // );



        // String src_ImagePath = "produced/mini_images/mimeBase_109.png";
        // String dest_ImagePath = "src/main/resources/BgOrange_copy.png";

        // long retVal = producer.glueSticker (
        //   imgHeight,
        //   imgWidth,
        //   producedImageFileBasename,
        //   src_ImagePath,
        //   dest_ImagePath,
        //   0,
        //   0,
        //   false,
        //   true
        // );



        // String src_ImagePath = "produced/mini_images/mimeSticker_221.png";
        // String dest_ImagePath = "produced/mini_images/mimeBase_208.png";
        // int dest_X = 0;
        // int dest_Y = 0;
        // int rgbaFilterBitsNumber = 32;
        // int rgbaFilterBitsOffset = 0;
        // int rgbaFilterValue = 0;

        // long retVal = producer.glueSticker (
        //   imgHeight,
        //   imgWidth,
        //   producedImageFileBasename,
        //   format,
        //   src_ImagePath,
        //   dest_ImagePath,
        //   dest_X,
        //   dest_Y,
        //   rgbaFilterBitsNumber,
        //   rgbaFilterBitsOffset,
        //   rgbaFilterValue,
        //   false,
        //   true
        // );



        System.out.println ( "-------------------------------" );
        System.out.println ( "Done, producer.glueSticker()" );
        System.out.println ( "-------------------------------\n\n" );

    }



    public long roundedArea (
      int imgHeight,
      int imgWidth,
      int inBorderPxNumber,
      String inProducedImageFileBasename,
      String colorText
    ) {

      ImageFormatEnum imageFormat = ImageFormatEnum.PNG;

      String miniImagesPathToSave = ImagingConstants.getInstance().PATH_SAVE_PRODUCED_MINI_IMAGE;
      String locProducedImageFilename = ( inProducedImageFileBasename + "." + imageFormat.getFilenameExtension() );
      String locPathToSave = Path.of( miniImagesPathToSave, locProducedImageFilename ).toAbsolutePath().toString();


      BaseImaging baseImaging = new BaseImaging();

      BufferedImage bufferedImage_toWrite = null;

      bufferedImage_toWrite = baseImaging.createImage ( 
        imgWidth, 
        imgHeight, 
        BufferedImage.TYPE_INT_ARGB
      );



      Graphics2D graphics2D = bufferedImage_toWrite.createGraphics();
      ColorBase colorBase = new ColorBase();
      ColorHelper colorHelper = new ColorHelper();
      ShapeHelper shapeHelper = new ShapeHelper();
      PixelHelper pixelHelper = new PixelHelper();


      // Color whiteTransparentColor = colorBase.getColor ( 0, "#ffffff" );
      // graphics2D.setBackground ( whiteTransparentColor );

      Color whiteColor = colorBase.getColor ( 0, "#ffffff" );
      graphics2D.setBackground ( whiteColor );





      Rect imgRect = new Rect();

      imgRect.height = imgHeight;
      imgRect.width = imgWidth;
      imgRect.x = 0;
      imgRect.y = 0;



      double dArcWH = ( imgRect.width / 1.9 );
      int arcWH = (int)Math.round ( dArcWH );


      Color orangeColor = colorBase.getColor ( 255, "#ff8042" );
      graphics2D.setColor ( orangeColor );

      graphics2D.fillRoundRect (
        imgRect.x, imgRect.y, imgRect.width, imgRect.height,
        arcWH,
        arcWH
      );



      Rect img2Rect = new Rect();

      img2Rect.height = ( imgHeight - ( inBorderPxNumber + inBorderPxNumber ) );
      img2Rect.width = ( imgWidth - ( inBorderPxNumber + inBorderPxNumber ) );
      img2Rect.x = inBorderPxNumber;
      img2Rect.y = inBorderPxNumber;

      dArcWH = ( img2Rect.width / 1.9 );
      arcWH = (int)Math.round ( dArcWH );

      Color orange2Color = colorBase.getColor ( 255, "#ff9360" );
      graphics2D.setColor ( orange2Color );

      graphics2D.fillRoundRect (
        img2Rect.x, img2Rect.y, img2Rect.width, img2Rect.height,
        arcWH,
        arcWH
      );



      baseImaging.cleanup( graphics2D );

      baseImaging.writeImageToFile ( 
        bufferedImage_toWrite,
        imageFormat,
        locPathToSave
      );



      return 3L;
    }



    public long glueSticker (
      int imgHeight,
      int imgWidth,
      String inProducedImageFileBasename,
      ImageFormatEnum inProducedImageFormat,
      String src_ImagePath,
      String dest_ImagePath,
      int dest_X,
      int dest_Y,
      int rgbaFilterBitsNumber,
      int rgbaFilterBitsOffset,
      int rgbaFilterValue,
      boolean toCenter_Sticker,
      boolean toMatchFilter
    ) {

      String miniImagesPathToSave = ImagingConstants.getInstance().PATH_SAVE_PRODUCED_MINI_IMAGE;
      String locProducedImageFilename = ( inProducedImageFileBasename + "." + inProducedImageFormat.getFilenameExtension().toLowerCase() );
      String locPathToSave = Path.of( miniImagesPathToSave, locProducedImageFilename ).toAbsolutePath().toString();



      Rect imgRect = new Rect();
      Rect rect_1 = new Rect();
      Rect rect_2 = new Rect();

      imgRect.height = imgHeight;
      imgRect.width = imgWidth;
      imgRect.x = 0;
      imgRect.y = 0;



      BufferedImage bufferedImage_toWrite = null;

      BufferedImage readSrc_bufferedImage = null;
      BufferedImage readDest_bufferedImage = null;

      BaseImaging baseImaging = new BaseImaging();



      readSrc_bufferedImage = baseImaging.readImageFile( src_ImagePath );
      readDest_bufferedImage = baseImaging.readImageFile( dest_ImagePath );

      imgRect.width  = readDest_bufferedImage.getWidth();
      imgRect.height = readDest_bufferedImage.getHeight();
      
      bufferedImage_toWrite = baseImaging.createImage ( 
        imgRect.width, 
        imgRect.height, 
        BufferedImage.TYPE_INT_ARGB
      );



      Graphics2D graphics2D = bufferedImage_toWrite.createGraphics();
      ColorBase colorBase = new ColorBase();
      ColorHelper colorHelper = new ColorHelper();
      ShapeHelper shapeHelper = new ShapeHelper();
      PixelHelper pixelHelper = new PixelHelper();



      Color whiteTransparentColor = colorBase.getColor ( 0, "#000000" );
      graphics2D.setBackground ( whiteTransparentColor );

      // Color whiteColor = colorBase.getColor ( 255, "#ffffff" );
      // graphics2D.setBackground ( whiteColor );

      graphics2D.fillRect ( 0, 0, imgRect.width, imgRect.height );

      // shapeHelper.drawInnerBorder( Color.GRAY, 1, graphics2D, imgRect, 0 );
      


      Rect src_Rect = new Rect();
      src_Rect.width = readSrc_bufferedImage.getWidth();
      src_Rect.height = readSrc_bufferedImage.getHeight();



      Rect dest_Rect = new Rect();
      dest_Rect.width = readDest_bufferedImage.getWidth();
      dest_Rect.height = readDest_bufferedImage.getHeight();



      // the main image read copied to dest image to write, where other image above will apply with color mask
      baseImaging.copyArea_FromImageToAnother ( 
        readDest_bufferedImage, 
        dest_Rect, 
        graphics2D, 
        0, 
        0,
        false
      );



      if ( toCenter_Sticker == true ) {
        int dest_mid_x = ( imgRect.width >> 1 );
        int dest_mid_y = ( imgRect.height >> 1 );

        dest_X = ( dest_mid_x - ( src_Rect.width >> 1 ) );
        dest_Y = ( dest_mid_y - ( src_Rect.height >> 1 ) );
      }



      Color transparentColor = colorBase.getColor ( 0, "#ffffff" );
      int rgba = transparentColor.getRGB();



      // Color orange2Color = colorBase.getColor ( 255, "#ff9955" );
      // graphics2D.setColor ( orange2Color );
      // int rgba = orange2Color.getRGB();



      // Color darkGreyColor = colorBase.getColor ( 255, "#010101" );
      // graphics2D.setColor ( darkGreyColor );
      // int rgba = darkGreyColor.getRGB();



      // int rgbaFilterBitsNumber = 32;
      // int rgbaFilterBitsOffset = 0;
      // int rgbaFilterValue = rgba;



      baseImaging.copyAreaFiltered_FromImageToAnother (
        readSrc_bufferedImage,
        src_Rect,
        bufferedImage_toWrite,
        dest_X,
        dest_Y,
        rgbaFilterBitsNumber,
        rgbaFilterBitsOffset,
        rgbaFilterValue,
        toMatchFilter
      );



      baseImaging.cleanup( graphics2D );

      baseImaging.writeImageToFile ( 
        bufferedImage_toWrite,
        inProducedImageFormat,
        locPathToSave
      );



      return 5L;

    }



    public long writeMiniImage (
      int imgHeight,
      int imgWidth,
      int imgType,
      String logoText,
      String logoFontfilePath,
      float logoFontSize,
      String logoTextColor,
      int borderWidthPx,
      int bgColorAlpha,
      String bgColor,
      String inProducedImageFileBasename,
      ImageFormatEnum inImageFormat
    ) {

      String miniImagesPathToSave = ImagingConstants.getInstance().PATH_SAVE_PRODUCED_MINI_IMAGE;
      String locProducedImageFilename = ( inProducedImageFileBasename + "." + inImageFormat.getFilenameExtension() );
      String locPathToSave = Path.of( miniImagesPathToSave, locProducedImageFilename ).toAbsolutePath().toString();



      Rect imgRect = new Rect();

      imgRect.height = imgHeight;
      imgRect.width = imgWidth;
      imgRect.x = 0;
      imgRect.y = 0;



      BufferedImage bufferedImage = null;

      BaseImaging baseImaging = new BaseImaging();
      bufferedImage = baseImaging.createImage ( 
        imgRect.height, 
        imgRect.width, 
        imgType 
      );

      Graphics2D graphics2D = bufferedImage.createGraphics();
      ColorBase colorBase = new ColorBase();
      ColorHelper colorHelper = new ColorHelper();
      ShapeHelper shapeHelper = new ShapeHelper();
      TextHelper textHelper = new TextHelper();



      Color backgroundColor = colorBase.getColor ( bgColorAlpha, bgColor );
      graphics2D.setColor ( backgroundColor );
      graphics2D.fillRect ( 0, 0, imgRect.width, imgRect.height );



      Color lineColor = colorBase.getColor ( 255, logoTextColor );
      shapeHelper.drawInnerBorder ( lineColor, 1, graphics2D, imgRect, 0 );



      File fontFile = new File( logoFontfilePath );
      Font font = null;
      try {
        font = Font.createFont ( Font.TRUETYPE_FONT, fontFile );
      } catch ( Exception e ) {
        e.printStackTrace();
      }

      font = font.deriveFont ( logoFontSize );
      fontFile = null;

      boolean toCenter_true = true;
      textHelper.setText ( 
        255, 
        logoTextColor, 
        font, 
        logoText, 
        imgRect, 
        graphics2D, 
        1, 
        1, 
        toCenter_true
      );



      baseImaging.cleanup ( graphics2D );

      baseImaging.writeImageToFile ( 
        bufferedImage,
        inImageFormat,
        locPathToSave
      );



      return 4L;
    }



    public long imageRasterUse (
      int imgHeight,
      int imgWidth,
      int imgType,
      String inProducedImageFileBasename,
      ImageFormatEnum inImageFormat
    ) {
      
      String miniImagesPathToSave = ImagingConstants.getInstance().PATH_SAVE_PRODUCED_MINI_IMAGE;
      String locProducedImageFilename = ( inProducedImageFileBasename + "." + inImageFormat.getFilenameExtension() );
      String locPathToSave = "";
      locPathToSave = Path.of( miniImagesPathToSave, locProducedImageFilename ).toAbsolutePath().toString();



      Rect imgRect = new Rect();
      Rect rect_1 = new Rect();
      Rect rect_2 = new Rect();

      imgRect.height = imgHeight;
      imgRect.width = imgWidth;
      imgRect.x = 0;
      imgRect.y = 0;


      
      BufferedImage bufferedImage;

      // Imaging imaging = new Imaging();
      // imaging.setPathToSaveProducedImages ( miniImagesPathToSave );
      // imaging.save ( 
      //   bufferedImage, 
      //   format, 
      //   locProducedImageFileBasename 
      // );


      BaseImaging baseImaging = new BaseImaging();
      bufferedImage = baseImaging.createImage ( 
        imgHeight, 
        imgWidth, 
        imgType 
      );

      Graphics2D graphics2D = bufferedImage.createGraphics();


      ColorBase colorBase = new ColorBase();


      ColorHelper colorHelper = new ColorHelper();
      colorHelper.fillVerticalGradient( Color.CYAN, Color.WHITE, graphics2D, imgRect );


      ShapeHelper shapeHelper = new ShapeHelper();

      rect_1.height = imgRect.height - 20;
      rect_1.width = imgRect.width - 20;
      rect_1.x = 10;
      rect_1.y = 10;
      shapeHelper.drawInnerBorder ( Color.LIGHT_GRAY, 1, graphics2D, rect_1, 0 );


      rect_2.height = imgRect.height - 80;
      rect_2.width = imgRect.width - 80;
      rect_2.x = 40;
      rect_2.y = 40;
      shapeHelper.drawShadowRect (  Color.YELLOW, 5, graphics2D, rect_2 );


      Color lineColor = colorBase.getColor ( 255, "#ff5500" );
      int rgba = lineColor.getRGB();

      // int rgba = colorBase.getColorRgba ( 255, "#ff5500" );
      // PixelHelper pixelHelper = new PixelHelper();


      int startX = 40;
      int len = 120;
      int finishX = ( startX + len );
      int x = ( startX - 1 );

      baseImaging.cleanup( graphics2D );

      // graphics2D.setColor ( lineColor );

      // graphics2D.drawLine( x, 100, finishX, 100 );
      // graphics2D.drawLine( x, 104, finishX, 104 );


      // int pxArrayLen = ( imgRect.height * imgRect.width );
      // int[] pixels = new int[pxArrayLen];
      int pxIx = 0;

      int y = 100;
      int xoffsetForY = ( y * imgRect.width );



      WritableRaster raster = bufferedImage.getAlphaRaster();
      DataBufferInt buffer = (DataBufferInt) raster.getDataBuffer();
      int[] rasterPixels = buffer.getData();

      while ( x < finishX ) {
        x++;

        // first line on y=140, 
        pxIx = ( xoffsetForY + x );
        // pixels[pxIx] = rgba;

        rasterPixels[pxIx] = rgba;



        // second line after 120px, 120 is the width of the raster
        // pxIx = ( ( x - startX ) + 120 );
        // pixels[pxIx] = rgba;


        // bufferedImage.setRGB (
        //   x,
        //   120,
        //   rgba
        // );

        // bufferedImage.setRGB (
        //   x,
        //   123,
        //   rgba
        // );

        // bufferedImage.setRGB (
        //   x,
        //   124,
        //   rgba
        // );
        

        // raster.setSample ( x, 100, 0, rgba );
        // raster.setSample ( x, 101, 0, rgba );
        // raster.setSample ( x, 102, 0, rgba );


        // pixelHelper.setRgba (
        //   bufferedImage, 
        //   rgba,
        //   x,
        //   100
        // );

      }

      
      // System.arraycopy( pixels, ( xoffsetForY + startX ), buffer.getData(), ( xoffsetForY + startX ), len );



      baseImaging.writeImageToFile ( 
        bufferedImage,
        inImageFormat,
        locPathToSave
      );

      baseImaging.cleanup( graphics2D );



      return 5L;
    }

}



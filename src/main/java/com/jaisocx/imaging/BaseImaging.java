package com.jaisocx.imaging;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.types.Rect;



public class BaseImaging implements BaseImagingInterface {

    protected Graphics2D g2d_Graphics = null;


    // BufferedImage.TYPE_INT_ARGB
    public BufferedImage createImage ( int width, int height, int imageType ) {
      BufferedImage bufferedImage = new BufferedImage( width, height, imageType );

      return bufferedImage;
    }



    public BufferedImage readImageFile ( String path ) {

      File imageFile = new File( path );
      BufferedImage readBufferedImage = null;

      try {
        readBufferedImage = ImageIO.read ( imageFile );
      } catch (IOException e) {
        e.printStackTrace();
      }

      imageFile = null;

      

      BufferedImage convertedBufferedImage = new BufferedImage (
          readBufferedImage.getWidth(),
          readBufferedImage.getHeight(),
          BufferedImage.TYPE_INT_ARGB
      );

      Graphics2D g = convertedBufferedImage.createGraphics();
      g.drawImage ( readBufferedImage, 0, 0, null );
      g.dispose();
      g = null;


      return convertedBufferedImage;
    }



    public BaseImagingInterface copyArea_FromImageToAnother ( 
      BufferedImage src_Img, 
      Rect src_Rect, 
      Graphics2D dest_graphics2D, 
      int dest_X, 
      int dest_Y,
      boolean toCleanup
    ) {

      BufferedImage copiedArea = src_Img.getSubimage ( 
        src_Rect.x, 
        src_Rect.y, 
        src_Rect.width, 
        src_Rect.height 
      );

      dest_graphics2D.drawImage (
        copiedArea,
        dest_X, 
        dest_Y,
        null
      );

      if ( toCleanup == true ) {
        this.cleanup( dest_graphics2D );
      }


      return this;
    }



    public BaseImagingInterface copyAreaFiltered_FromImageToAnother (
      BufferedImage inOverlayImage,
      Rect inRect_OverlayImageToCopy, 
      BufferedImage dest_Img,
      int dest_X, 
      int dest_Y,
      int rgbaFilterBitsNumber,
      int rgbaFilterBitsOffset,
      int rgbaFilterValue,
      boolean toMatchFilter
    ) {

      int rgbaMask = 0;
      int rgbaMaskBitOffset = 0;

      while( rgbaMaskBitOffset < rgbaFilterBitsNumber ) {
        rgbaMask += 1;



        rgbaMaskBitOffset++;
        if ( rgbaMaskBitOffset == rgbaFilterBitsNumber ) {
          break;
        }

        rgbaMask = ( rgbaMask << 1 );
      }



      BufferedImage locOverlayImageCopy = inOverlayImage.getSubimage ( 
        inRect_OverlayImageToCopy.x, 
        inRect_OverlayImageToCopy.y, 
        inRect_OverlayImageToCopy.width, 
        inRect_OverlayImageToCopy.height 
      );

      Rect locRect_OverlayImageToCopy = new Rect();
      locRect_OverlayImageToCopy.width = ( inRect_OverlayImageToCopy.width );
      locRect_OverlayImageToCopy.height = ( inRect_OverlayImageToCopy.height );
      locRect_OverlayImageToCopy.x = 0;
      locRect_OverlayImageToCopy.y = 0;



      WritableRaster dest = dest_Img.getAlphaRaster();
      DataBufferInt dest_Buffer = (DataBufferInt) dest.getDataBuffer();
      int[] destImagePixels = dest_Buffer.getData();
      Rect dest_Rect = new Rect();
      dest_Rect.width = dest_Img.getWidth();
      dest_Rect.height = dest_Img.getHeight();
      dest_Rect.x = 0;
      dest_Rect.y = 0;



      WritableRaster overlayImageRaster = locOverlayImageCopy.getAlphaRaster();
      DataBufferInt overlayImageDataBuffer = (DataBufferInt) overlayImageRaster.getDataBuffer();
      int[] overlayImagePixels = overlayImageDataBuffer.getData();
      int overlayImagePixelsNumber = overlayImagePixels.length;
      int overlayImageLinesNumber = locRect_OverlayImageToCopy.height;
      int overlayImagePixelIx = 0;
      int oneLine_overlayImagePixelIx = 0;
      int offset_overlayImagePixelIx = 0;



      int overlayImageLineIx = 0;
      int destImageLineIx    = dest_Y;

      int offset_destImagePixelIx = ( ( destImageLineIx * dest_Rect.width ) + dest_X );
      int destImagePixelIx = ( offset_destImagePixelIx );



      int overlayImage_PixelRgba = 0;
      int rgbaValueToCompare = 0;
      boolean rgbaFilterMatches = false;



      oneLine_overlayImagePixelIx -= 1;

      while ( overlayImagePixelIx < overlayImagePixelsNumber ) {

        oneLine_overlayImagePixelIx++;

        if ( oneLine_overlayImagePixelIx == locRect_OverlayImageToCopy.width ) {
          oneLine_overlayImagePixelIx = 0;

          overlayImageLineIx++;
          destImageLineIx = ( overlayImageLineIx + dest_Y );

          offset_overlayImagePixelIx = ( overlayImageLineIx * locRect_OverlayImageToCopy.width );
          offset_destImagePixelIx    = ( ( destImageLineIx * dest_Rect.width ) + dest_X );
        }

        overlayImagePixelIx = ( oneLine_overlayImagePixelIx + offset_overlayImagePixelIx );
        destImagePixelIx    = ( oneLine_overlayImagePixelIx + offset_destImagePixelIx );



        // in addition, exit statement, if the next line of the overlay image exceeds the lines humber of the overlay image
        if ( overlayImageLineIx == overlayImageLinesNumber ) {
          break;
        }



        // the rgba color value of the overlay image
        overlayImage_PixelRgba = overlayImagePixels[ overlayImagePixelIx ];



        // CHECK WHETHER IMAGE PX COLOR MATCHES THE COLOR FILTER
        // shift and mask the px value to compare with filter value
        if ( rgbaFilterBitsOffset != 0 ) {
          rgbaValueToCompare = ( overlayImage_PixelRgba >> rgbaFilterBitsOffset );
        } else {
          rgbaValueToCompare = overlayImage_PixelRgba;
        }

        rgbaFilterMatches = ( rgbaFilterValue == ( rgbaValueToCompare & rgbaMask ) );

        // filter matches, we don't copy px to dest image, skipping ...
        if ( rgbaFilterMatches == toMatchFilter ) {
          continue;
        }



        // the rgba color value of the overlay image is assigned to the dest image pixels data buffer of datatype int[]
        destImagePixels[ destImagePixelIx ] = overlayImage_PixelRgba;

      }


      return this;
    }



    // Save 
    public BaseImagingInterface writeImageToFile ( 
      BufferedImage bufferedImageToWrite, 
      ImageFormatEnum format, 
      String path
    ) {

      bufferedImageToWrite.flush();



      File savedFile = new File( path );

      try {
        ImageIO.write ( 
          bufferedImageToWrite, 
          format.getFilenameExtension(), 
          savedFile 
        );
      } catch (IOException e) {
        e.printStackTrace();
      }

      savedFile = null;



      return this;
    }



    public BaseImagingInterface cleanup ( Graphics2D g ) {
      g.dispose();
      g = null;

      return this;
    }

}





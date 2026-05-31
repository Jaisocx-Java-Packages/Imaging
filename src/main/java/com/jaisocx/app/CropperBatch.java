package com.jaisocx.app;

import java.awt.image.BufferedImage;
import java.io.File;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.Imaging;
import com.jaisocx.imaging.helpers.helper_interface.ImagingFilesystemHelperInterface;
import com.jaisocx.imaging.types.Rect;



public class CropperBatch {

  public static void main (String[] args) {

    String imageVer = args[0];
    String versionBatchCounter = "_${lineKey}_${itemKey}_";

    Rect srcImage_Rect = new Rect();
    Rect ci_Image_Rect  = new Rect();

    srcImage_Rect.x = Integer.valueOf( args[1] );
    srcImage_Rect.y = Integer.valueOf( args[2] );
    srcImage_Rect.width = Integer.valueOf( args[3] );
    srcImage_Rect.height = Integer.valueOf( args[4] );

    ci_Image_Rect.width  = srcImage_Rect.width;
    ci_Image_Rect.height = srcImage_Rect.height;


    long offsetX = Long.valueOf( args[5] );
    long offsetY = Long.valueOf( args[6] );
    long offsetNextX = Long.valueOf( args[7] );
    long offsetNextY = Long.valueOf( args[8] );

    long ci_first_line = Long.valueOf( args[9] );
    long ci_first_item = Long.valueOf( args[10] );

    long ci_lines_number = Long.valueOf( args[11] );
    long ci_items_number = Long.valueOf( args[12] );

    String pathOf_ProducedImageParent_to = args[13];
    String nameOf_ProducedImage_to = args[14];
    String pathOf_srcImage_from = args[15];

    ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[16] );

    File f_ProducedImageParent_to = new File( ( pathOf_ProducedImageParent_to + imageVer ));
    if ( f_ProducedImageParent_to.exists() == false ) {
      f_ProducedImageParent_to.mkdir();
    }
    f_ProducedImageParent_to = null;

    File f_ProducedPreview_to = new File( "./produced/preview" );
    if ( f_ProducedPreview_to.exists() == false ) {
      f_ProducedPreview_to.mkdir();
    }
    f_ProducedPreview_to = null;



    String pathOf_producedImage_to = "";
    String filenameExtensionOf_producedImage_to = imageFormatTo.getFilenameExtension();
    long written = 1L;

    String pathOf_folder_srcImage_from = ".";
    String filenameOf_srcImage_from = pathOf_srcImage_from;
    String filenameBaseOf_srcImage_from = "";
    String filenameExtensionOf_srcImage_from = "";
    int pathJoiningSymbolPos = pathOf_srcImage_from.lastIndexOf( "/" );
    int filenameExtensionJoiningSymbolPos = pathOf_srcImage_from.lastIndexOf( "." );

    if ( pathJoiningSymbolPos != (-1) ) {
      pathOf_folder_srcImage_from = pathOf_srcImage_from.substring( 0, pathJoiningSymbolPos );
      filenameOf_srcImage_from = pathOf_srcImage_from.substring( pathJoiningSymbolPos, ( pathOf_srcImage_from.length() ) );
    }

    if ( filenameExtensionJoiningSymbolPos != (-1) ) {
      filenameBaseOf_srcImage_from = pathOf_srcImage_from.substring( pathJoiningSymbolPos, filenameExtensionJoiningSymbolPos );
      filenameExtensionOf_srcImage_from = pathOf_srcImage_from.substring( filenameExtensionJoiningSymbolPos, ( pathOf_srcImage_from.length() ) );
    }

    if ( imageFormatTo == null ) {
      ImageFormatEnum imageFormatFrom = ImageFormatEnum.fromString( filenameExtensionOf_srcImage_from );
      imageFormatTo = imageFormatFrom;
    }



    Imaging imaging = new Imaging();
    ImagingFilesystemHelperInterface fsHelper = imaging.getImagingFilesystemHelper();
    BufferedImage bImg = fsHelper.read (
        pathOf_srcImage_from
    );

    BufferedImage producedBufImage = null;

    long lineKey = 1L;
    long counterLine = 1L;
    long counterLineMax = 1L;

    long itemKey = 1L;
    long counterItem = 1L;
    long counterItemMax = 1L;

    counterLine = ( ci_first_line + 1 );
    counterItem = ( ci_first_item + 1 );

    counterLineMax = ( ci_first_line + ci_lines_number + 2 );
    counterItemMax = ( ci_first_item + ci_items_number + 2 );

    lineKey = ( counterLine - 1 );
    itemKey = ( counterItem - 1 );

    ci_Image_Rect.width  = srcImage_Rect.width;
    ci_Image_Rect.height = srcImage_Rect.height;

    ci_Image_Rect.y = (int)( offsetY + srcImage_Rect.y + ( ( offsetNextY + srcImage_Rect.y + srcImage_Rect.height ) * lineKey ) );
    ci_Image_Rect.x = (int)( offsetX + srcImage_Rect.x + ( ( offsetNextX + srcImage_Rect.x + srcImage_Rect.width )  * itemKey ) );

    ci_a_line: while( counterLine < counterLineMax ) {
      counterLine++;
      if ( counterLine >= counterLineMax ) {
        break ci_a_line;
      }

      lineKey = ( counterLine - 2 );
      ci_Image_Rect.y = (int)( offsetY + srcImage_Rect.y + ( ( offsetNextY + srcImage_Rect.y + srcImage_Rect.height ) * lineKey ) );


      counterItem = ( ci_first_item + 1 );
      counterItemMax = ( ci_first_item + ci_items_number + 2 );
      ci_b_item: while( counterItem < counterItemMax ) {
        counterItem++;
        if ( counterItem >= counterItemMax ) {
          continue ci_a_line;
        }

        itemKey = ( counterItem - 2 );
        ci_Image_Rect.x = (int)( offsetX + srcImage_Rect.x + ( ( offsetNextX + srcImage_Rect.x + srcImage_Rect.width )  * itemKey ) );
        versionBatchCounter = ( lineKey + "_" + itemKey );

        /* pathOf_producedImage_to = (
            pathOf_folder_srcImage_from
                + "/" + "produced_batch"
                + "_" + imageVer
                + "/" + filenameBaseOf_srcImage_from
                + "_" + imageVer + "_"
                + versionBatchCounter
                + "." + filenameExtensionOf_producedImage_to
        ); */


        pathOf_producedImage_to = (
            ( pathOf_ProducedImageParent_to + imageVer )
                + "/" + (
                    nameOf_ProducedImage_to
                    + imageVer
                    + "_" + versionBatchCounter
                    + "." + filenameExtensionOf_producedImage_to
                  )
        );

        producedBufImage = imaging.cropBufferedImage (
            bImg,
            ci_Image_Rect
        );

        written = fsHelper.write (
            producedBufImage,
            imageFormatTo,
            pathOf_producedImage_to
        );

        pathOf_producedImage_to = (
            "./produced/preview"
            + "/" + "image_"
            + "_" + versionBatchCounter
            + "." + filenameExtensionOf_producedImage_to
        );

        written = fsHelper.write (
            producedBufImage,
            imageFormatTo,
            pathOf_producedImage_to
        );

        continue ci_b_item;
      }

      continue ci_a_line;
    }

  }

}



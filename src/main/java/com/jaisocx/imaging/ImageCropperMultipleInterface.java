package com.jaisocx.imaging;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.helpers.ImagingFilesystemHelper;
import com.jaisocx.imaging.types.Rect;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/* @in_engineering: base methods move to another Java library */
public interface ImageCropperMultipleInterface {

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
  );

  public void printCombinations (List<List<Object>> combinations);

  public void printCombinationsItem (List<Object> combinationItem);

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
  );

  public char[] readHtmlFile (
      String tplPath
  );
}

package com.jaisocx.imaging.downgrader.downgrader_interface;

import java.awt.image.BufferedImage;

public interface ImagingDowngraderInterface {

  public BufferedImage widthDowngrade (
      BufferedImage in_raster,
      long in_width
  );

  public BufferedImage heightDowngrade (
      BufferedImage in_raster,
      long in_height
  );

  public BufferedImage isWidthDowngrades (
      BufferedImage in_raster,
      long in_width
  );

  public BufferedImage isHeightDowngrades (
      BufferedImage in_raster,
      long in_height
  );

}



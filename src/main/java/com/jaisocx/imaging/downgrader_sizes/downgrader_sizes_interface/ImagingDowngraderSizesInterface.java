package com.jaisocx.imaging.downgrader_sizes.downgrader_sizes_interface;


import com.jaisocx.imaging.imaging_types.Dimension;


public interface ImagingDowngraderSizesInterface {

  public Dimension getUpscalePxSizesByShift (
      Dimension in_originalDimension,
      byte multiplicatorByShift
  );

  public Dimension getUpscaleRAMByteRasterSizeByShift (
      Dimension in_originalDimension,
      byte multiplicatorByShift
  );

  public Dimension getRAMByteRasterSize (
      Dimension in_originalDimension
  );



  public Dimension getWidthUpscaleSizes (
      Dimension in_originalDimension,
      long in_upscaleDestPxWidth
  );

  public Dimension getHeightUpscaleSizes (
      Dimension in_originalDimension,
      long in_upscaleDestPxHeight
  );



  public Dimension getPercentageDowngradeSizes (
      Dimension in_originalDimension,
      float in_downgradeDestPct
  );

  public Dimension getWidthDowngradeSizes (
      Dimension in_originalDimension,
      long in_downgradeDestPxWidth
  );

  public Dimension getHeightDowngradeSizes (
      Dimension in_originalDimension,
      long in_downgradeDestPxHeight
  );

  public long[] getDimensionedSizes (
      long[] in_originalSizes,
      long in_destFirstSize
  );

}



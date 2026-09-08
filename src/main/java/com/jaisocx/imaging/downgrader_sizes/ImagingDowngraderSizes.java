package com.jaisocx.imaging.downgrader_sizes;


import com.jaisocx.imaging.downgrader_sizes.downgrader_sizes_interface.ImagingDowngraderSizesInterface;
import com.jaisocx.imaging.imaging_types.Dimension;


public class ImagingDowngraderSizes implements ImagingDowngraderSizesInterface {

  public Dimension getUpscalePxSizesByShift (
      Dimension in_originalDimension,
      byte multiplicatorByShift
  ) {
    return null;
  }

  public Dimension getUpscaleRAMByteRasterSizeByShift (
      Dimension in_originalDimension,
      byte multiplicatorByShift
  ) {
    return null;
  }

  public Dimension getRAMByteRasterSize ( Dimension in_originalDimension ) {
    return null;
  }



  public Dimension getWidthUpscaleSizes (
      Dimension in_originalDimension,
      long in_upscaleDestPxWidth
  ) {
    return null;
  }

  public Dimension getHeightUpscaleSizes (
      Dimension in_originalDimension,
      long in_upscaleDestPxHeight
  ) {
    return null;
  }



  public Dimension getPercentageDowngradeSizes (
      Dimension in_originalDimension,
      float in_downgradeDestPct
  ) {
    return null;
  }



  public Dimension getWidthDowngradeSizes (
      Dimension in_originalDimension,
      long in_downgradeDestPxWidth
  ) {
    Dimension loc_originalDimension    = in_originalDimension;
    long  loc_downgradeDestPxWidth     = in_downgradeDestPxWidth;


    long[] r_dimensionedSizes          = ( new long[2] );

    long[] arg_originalSizes           = ( new long[2] );
    long   arg_dimensionedFirstSize    = loc_downgradeDestPxWidth;
    arg_originalSizes[0] = loc_originalDimension.width;
    arg_originalSizes[1] = loc_originalDimension.height;

    r_dimensionedSizes = this.getDimensionedSizes (
        arg_originalSizes,
        arg_dimensionedFirstSize
    );


    Dimension r_dimensionDowngraded = ( new Dimension() );
    r_dimensionDowngraded.width  = (int)( r_dimensionedSizes[0] );
    r_dimensionDowngraded.height = (int)( r_dimensionedSizes[1] );


    return r_dimensionDowngraded;
  }



  public Dimension getHeightDowngradeSizes (
      Dimension in_originalDimension,
      long in_downgradeDestPxHeight
  ) {
    Dimension loc_originalDimension    = in_originalDimension;
    long  loc_downgradeDestPxHeight    = in_downgradeDestPxHeight;


    long[] r_dimensionedSizes          = ( new long[2] );

    long[] arg_originalSizes           = ( new long[2] );
    long   arg_dimensionedFirstSize    = loc_downgradeDestPxHeight;
    arg_originalSizes[0] = loc_originalDimension.height;
    arg_originalSizes[1] = loc_originalDimension.width;

    r_dimensionedSizes = this.getDimensionedSizes (
        arg_originalSizes,
        arg_dimensionedFirstSize
    );


    Dimension r_dimensionDowngraded = ( new Dimension() );
    r_dimensionDowngraded.height = (int)( r_dimensionedSizes[0] );
    r_dimensionDowngraded.width  = (int)( r_dimensionedSizes[1] );


    return r_dimensionDowngraded;
  }



  public long[] getDimensionedSizes (
      long[] in_originalSizes,
      long in_destFirstSize
  ) {
    long[] loc_originalSizes    = in_originalSizes;
    long   loc_destFirstSize    = in_destFirstSize;

    long[] r_dimensionedSizes   = ( new long[2] );



    long loc_originalWidth    = loc_originalSizes[0];
    long loc_originalHeight   = loc_originalSizes[1];

    long loc_destWidth        = loc_destFirstSize;
    long loc_destHeight       = 1;

    byte precisionBitsShift = 20;

    long width_preceise_px_shifted                = ( loc_originalWidth << precisionBitsShift );
    long preceise_px_multiplicator                = Math.floorDiv( width_preceise_px_shifted, loc_destWidth );

    long calc_height_preceise_px_sizes            = ( loc_originalHeight * preceise_px_multiplicator );
    long calc_height_rounded                      = ( calc_height_preceise_px_sizes >>> precisionBitsShift );
    loc_destHeight                                = calc_height_rounded;


    r_dimensionedSizes[0] = loc_destWidth;
    r_dimensionedSizes[1] = loc_destHeight;

    return r_dimensionedSizes;
  }

}



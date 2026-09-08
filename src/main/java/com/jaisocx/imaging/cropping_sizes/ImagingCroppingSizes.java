package com.jaisocx.imaging.cropping_sizes;


import com.jaisocx.imaging.imaging_types.Dimension;
import com.jaisocx.imaging.sizes.Constants.ImagingSizeKeyEnum;
import com.jaisocx.imaging.sizes.Constants.ImagingSizesEnum;
import com.jaisocx.imaging.cropping_sizes.cropping_sizes_interface.ImagingCroppingSizesInterface;


public class ImagingCroppingSizes implements ImagingCroppingSizesInterface {

  /**
  * The secure counter for the recursive method
  * this.getCroppedMaxSizes( ... ) => 1)this.calcSizes( ... ) => 2)this.calcSizes( ... )
  */
  private byte poc_recursionCounter = 1;



  /**
  * recall of this.getCroppedMaxSizesByNumbers( ... ) =>  getCroppedMaxSizesByRect( ... ) => this.calcSizes( ... );
  * I have to think... the Dimension object brings transparency.
  *
  * The first cropping action might be the largest cropped image inside the original image,
  * due to one size, width or height, equal to the width or height of the original image.
  *
  * @param Rect in_originalSizes
  * @param ImagingSizesEnum in_sizes
  * @return Dimension
  */
  public Dimension getCroppedMaxSizesByEnum (
      Dimension in_originalSizes,
      ImagingSizesEnum in_sizes
  ) {
    Dimension r_dimension = this.getCroppedMaxSizesByNumbers (
        in_originalSizes,
        in_sizes.getWidth(),
        in_sizes.getHeight()
    );

    return r_dimension;
  }



  /**
  * recall of getCroppedMaxSizesByRect( ... ) => this.calcSizes( ... );
  * @param in_originalSizes
  * @param in_width
  * @param in_height
  * @return Dimension
  */
  public Dimension getCroppedMaxSizesByNumbers (
      Dimension in_originalSizes,
      long in_width,
      long in_height
  ) {
    Dimension loc_originalSizes = in_originalSizes;
    long loc_width  = in_width;
    long loc_height = in_height;


    Dimension arg_resultDimension = ( new Dimension() );
      arg_resultDimension.width  = (int)loc_width;
      arg_resultDimension.height = (int)loc_height;
      arg_resultDimension.x = 0;
      arg_resultDimension.y = 0;

    Dimension r_dimension = this.getCroppedMaxSizesByRect (
        loc_originalSizes,
        arg_resultDimension
    );


    return r_dimension;
  }



  /**
  * Real subcall of this.calcCroppedMaxSizes( ... );
  * @param in_originalSizes
  * @param in_resultDimension
  * @return Dimension
  */
  public Dimension getCroppedMaxSizesByRect (
      Dimension in_originalSizes,
      Dimension in_resultDimension
  ) {
    this.poc_recursionCounter = 1;

    Dimension loc_originalSizes = in_originalSizes;
    Dimension loc_resultDimension = in_resultDimension;
    ImagingSizeKeyEnum loc_keysize = this.getMaxSizeKeyByRect(loc_resultDimension);

    Dimension r_dimension = this.calcCroppedMaxSizes (
        loc_originalSizes,
        loc_resultDimension,
        loc_keysize
    );

    return r_dimension;
  }



  /**
  * Subcall of this.calcMaxSizesByFirst100pctDimension( .. )
  * Sometimes twice recalling recursive method.
  * increments recursive counter, throws runtime exception on exceeding of hardcoded number 3.
  * @param Rect in_originalSizes
  * @param Rect in_resultDimension
  * @param ImagingSizeKeyEnum in_sizekey
  * @return Dimension
  */
  public Dimension calcCroppedMaxSizes (
      Dimension in_originalSizes,
      Dimension in_resultDimension,
      ImagingSizeKeyEnum in_sizekey
  ) {
    byte loc_recursionCounter = this.poc_recursionCounter;
    loc_recursionCounter += 1;
    this.poc_recursionCounter = loc_recursionCounter;

    if ( loc_recursionCounter > 3 ) {
      throw new RuntimeException( "Infinite Loop encountered! Broke..." );
    }



    Dimension loc_originalSizes = in_originalSizes;
    Dimension loc_resultDimension = in_resultDimension;
    ImagingSizeKeyEnum loc_sizekey = in_sizekey;

    Dimension r_dimension = ( new Dimension() );
    r_dimension.x = loc_originalSizes.x;
    r_dimension.y = loc_originalSizes.y;

    long[] arg_originalSizes   = ( new long[2] );
    long[] arg_aimedDimension  = ( new long[2] );
    long[] loc_maxSizes        = ( new long[2] );

    if ( loc_sizekey == ImagingSizeKeyEnum.WIDTH ) {
      arg_originalSizes[0] = loc_originalSizes.width;
      arg_originalSizes[1] = loc_originalSizes.height;

      arg_aimedDimension[0] = loc_resultDimension.width;
      arg_aimedDimension[1] = loc_resultDimension.height;

    } else if ( loc_sizekey == ImagingSizeKeyEnum.HEIGHT ) {
      arg_originalSizes[0] = loc_originalSizes.height;
      arg_originalSizes[1] = loc_originalSizes.width;

      arg_aimedDimension[0] = loc_resultDimension.height;
      arg_aimedDimension[1] = loc_resultDimension.width;

    }

    loc_maxSizes = this.calcCroppedMaxSizesByFirst100pctDimension (
        arg_originalSizes,
        arg_aimedDimension
    );

    if ( loc_sizekey == ImagingSizeKeyEnum.WIDTH ) {
      r_dimension.width   = ( (int) loc_maxSizes[0] );
      r_dimension.height  = ( (int) loc_maxSizes[1] );

    } else if ( loc_sizekey == ImagingSizeKeyEnum.HEIGHT ) {
      r_dimension.height  = ( (int) loc_maxSizes[0] );
      r_dimension.width   = ( (int) loc_maxSizes[1] );

    }



    boolean loc_isOneDimOutOfBounds = (
        ( r_dimension.width > loc_originalSizes.width )
            || ( r_dimension.height > loc_originalSizes.height )
    );

    boolean loc_isDimInBounds = ( loc_isOneDimOutOfBounds == false );

    if ( loc_isDimInBounds == true ) {
      this.poc_recursionCounter = 1;

      return r_dimension;
    }



    ImagingSizeKeyEnum arg_sizekey = ImagingSizeKeyEnum.HEIGHT;
    if ( loc_sizekey == ImagingSizeKeyEnum.WIDTH ) {
      arg_sizekey = ImagingSizeKeyEnum.HEIGHT;

    } else if ( loc_sizekey == ImagingSizeKeyEnum.HEIGHT ) {
      arg_sizekey = ImagingSizeKeyEnum.WIDTH;

    }


    r_dimension = this.calcCroppedMaxSizes (
        loc_originalSizes,
        loc_resultDimension,
        arg_sizekey
    );

    return r_dimension;
  }



  public long[] calcCroppedMaxSizesByFirst100pctDimension (
      long[] in_originalSizes,
      long[] in_aimedDimension
  ) {

    long[] loc_originalSizes    = in_originalSizes;
    long[] loc_aimedDimension   = in_aimedDimension;
    long[] r_sizes = ( new long[2] );

    long loc_width  = loc_originalSizes[0];
    long loc_height = loc_originalSizes[1];

    long sizes_width  = loc_aimedDimension[0];
    long sizes_height = loc_aimedDimension[1];

    byte precisionBitsShift = 10;

    long calc_height_preceise_px_shifted          = ( loc_height << precisionBitsShift );
    long preceise_px_multiplicator                = Math.floorDiv( calc_height_preceise_px_shifted, sizes_height );


    long calc_height_preceise_px_sizes            = ( sizes_height * preceise_px_multiplicator );
    long calc_width_preceise_px_sizes             = ( sizes_width  * preceise_px_multiplicator );

    long calc_height_rounded                      = ( calc_height_preceise_px_sizes >>> precisionBitsShift );
    long calc_width_rounded                       = ( calc_width_preceise_px_sizes  >>> precisionBitsShift );



    r_sizes[0] = calc_width_rounded;
    r_sizes[1] = calc_height_rounded;

    return r_sizes;
  }



  public ImagingSizeKeyEnum getMaxSizeKey ( Dimension in_dimension) {
    Dimension loc_dimension = in_dimension;
    ImagingSizeKeyEnum r_sizeKey = ImagingSizeKeyEnum.HEIGHT;

    if ( loc_dimension.width > loc_dimension.height ) {
      r_sizeKey = ImagingSizeKeyEnum.WIDTH;
    }

    return r_sizeKey;
  }

  public long getMaxSizeValue ( Dimension in_dimension) {
    Dimension loc_dimension = in_dimension;
    long r_maxSize = loc_dimension.height;

    if ( loc_dimension.width > loc_dimension.height ) {
      r_maxSize = loc_dimension.width;
    }

    return r_maxSize;
  }



  public ImagingSizeKeyEnum getMaxSizeKeyByEnum ( ImagingSizesEnum in_sizes ) {
    ImagingSizeKeyEnum r_keysize = in_sizes.getMaxSizeKeyEnum();

    return r_keysize;
  }

  public ImagingSizeKeyEnum getMaxSizeKeyByRect ( Dimension in_dimension) {
    ImagingSizeKeyEnum r_keysize = this.getMaxSizeKeyByNumbers (
        in_dimension.width,
        in_dimension.height
    );

    return r_keysize;
  }

  public ImagingSizeKeyEnum getMaxSizeKeyByNumbers (
      long in_width,
      long in_height
  ) {
    ImagingSizeKeyEnum r_keysize = ImagingSizeKeyEnum.HEIGHT;
    if ( in_width > in_height ) {
      r_keysize = ImagingSizeKeyEnum.WIDTH;
    }

    return r_keysize;
  }

}



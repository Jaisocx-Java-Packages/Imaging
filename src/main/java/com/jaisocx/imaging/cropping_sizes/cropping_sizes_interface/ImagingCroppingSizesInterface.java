package com.jaisocx.imaging.cropping_sizes.cropping_sizes_interface;


import com.jaisocx.imaging.imaging_types.Dimension;
import com.jaisocx.imaging.sizes.Constants.ImagingSizeKeyEnum;
import com.jaisocx.imaging.sizes.Constants.ImagingSizesEnum;


public interface ImagingCroppingSizesInterface {

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
  );



  /**
  * recall of getCroppedMaxSizesByRect( ... ) => this.calcSizes( ... );
  * @param Rect in_originalSizes
  * @param long in_width
  * @param long in_height
  * @return Dimension
  */
  public Dimension getCroppedMaxSizesByNumbers (
      Dimension in_originalSizes,
      long in_width,
      long in_height
  );



  /**
  * Real subcall of this.calcSizes( ... );
  * @param in_originalSizes
  * @param in_resultDimension
  * @return Dimension
  */
  public Dimension getCroppedMaxSizesByRect (
      Dimension in_originalSizes,
      Dimension in_resultDimension
  );



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
  );



  /**
  *
  * @param in_originalSizes
  * @param in_aimedDimension
  * @return
  */
  public long[] calcCroppedMaxSizesByFirst100pctDimension (
      long[] in_originalSizes,
      long[] in_aimedDimension
  );



  public ImagingSizeKeyEnum getMaxSizeKey ( Dimension in_dimension);

  public long getMaxSizeValue ( Dimension in_dimension);



  public ImagingSizeKeyEnum getMaxSizeKeyByEnum ( ImagingSizesEnum in_sizes );

  public ImagingSizeKeyEnum getMaxSizeKeyByRect ( Dimension in_dimension);

  public ImagingSizeKeyEnum getMaxSizeKeyByNumbers (
      long in_width,
      long in_height
  );

}



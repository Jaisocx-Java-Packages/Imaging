package com.jaisocx.imaging.sizes.Constants;


public enum ImagingSizesEnum {
  UNDEFINED(1, 1, ImagingSizeKeyEnum.WIDTH ),

  SIZE_1_1( 1, 1, ImagingSizeKeyEnum.WIDTH ),
  SIZE_1_2( 1, 2, ImagingSizeKeyEnum.HEIGHT ),
  SIZE_2_1( 2, 1, ImagingSizeKeyEnum.WIDTH );

  private final long poc_width;
  private final long poc_height;
  private final ImagingSizeKeyEnum poc_maxSizeKey;

  ImagingSizesEnum( long in_width, long in_height, ImagingSizeKeyEnum in_maxSizeKey ) {
    this.poc_width   = in_width;
    this.poc_height  = in_height;
    this.poc_maxSizeKey = in_maxSizeKey;
  }


  public long getWidth() {
    long loc_width = this.poc_width;

    return loc_width;
  }

  public long getHeight() {
    long loc_height = this.poc_height;

    return loc_height;
  }

  public ImagingSizeKeyEnum getMaxSizeKeyEnum() {
    return this.poc_maxSizeKey;
  }


  /**
  * @example: ImagingSizesEnum.fromSizes( "1:1" ) => ImagingSizesEnum.SIZE_1_1;
  *
  * @param: String in_size
  * @return ImagingSizesEnum
  */
  public static ImagingSizesEnum fromSizes( String in_size ) {

    ImagingSizesEnum r_imageSizeEnum = ImagingSizesEnum.UNDEFINED;

    String[] loc_sizes = in_size.split( ":" );
    String loc_widthStr = loc_sizes[0];
    String loc_heightStr = loc_sizes[1];

    int loc_widthInt = Integer.parseInt( loc_widthStr, 10 );
    int loc_heightInt = Integer.parseInt( loc_heightStr, 10 );

    ci_sizes: for ( ImagingSizesEnum ci_imageSizeEnum : ImagingSizesEnum.values() ) {
      if (
          ( ci_imageSizeEnum.poc_width == loc_widthInt )
          && ( ci_imageSizeEnum.poc_height == loc_heightInt )
      ) {
        r_imageSizeEnum = ci_imageSizeEnum;
        break ci_sizes;
      }
    }

    return r_imageSizeEnum;
  }

}



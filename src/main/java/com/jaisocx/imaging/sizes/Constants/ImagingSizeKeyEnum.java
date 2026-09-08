package com.jaisocx.imaging.sizes.Constants;


public enum ImagingSizeKeyEnum {
  UNDEFINED("undefined", "undefined" ),

  WIDTH( "w", "width" ),
  HEIGHT( "h", "height" );

  private final String poc_keySymbol;
  private final String poc_keyName;

  ImagingSizeKeyEnum( String in_keySymbol, String in_keyName ) {
    this.poc_keySymbol = in_keySymbol;
    this.poc_keyName   = in_keyName;
  }



  /**
  * @example: ImagingSizeKeyEnum.fromKey( "h" ) => ImagingSizeKeyEnum.HEIGHT;
  * @example: ImagingSizeKeyEnum.fromKey( "H" ) => ImagingSizeKeyEnum.HEIGHT;
  * @example: ImagingSizeKeyEnum.fromKey( "height" ) => ImagingSizeKeyEnum.HEIGHT;
  * @example: ImagingSizeKeyEnum.fromKey( "HeigHT" ) => ImagingSizeKeyEnum.HEIGHT;
  *
  * @param: String in_key CASE_INSensitive, performs equality action by lowercase symbol.
  * @return ImagingSizeKeyEnum
  */
  public static ImagingSizeKeyEnum fromKey( String in_key ) {

    ImagingSizeKeyEnum r_imageSizeKeyEnum = ImagingSizeKeyEnum.UNDEFINED;
    String loc_key = in_key.toLowerCase();

    ci_keys: for ( ImagingSizeKeyEnum ci_imageSizeKeyEnum : ImagingSizeKeyEnum.values() ) {
      if (
          ( ci_imageSizeKeyEnum.poc_keySymbol.equals( loc_key ) == true )
          || ( ci_imageSizeKeyEnum.poc_keyName.equals( loc_key ) == true )
      ) {
        r_imageSizeKeyEnum = ci_imageSizeKeyEnum;
        break ci_keys;
      }
    }

    return r_imageSizeKeyEnum;
  }

}



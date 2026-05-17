package com.jaisocx.imaging.Constants;



public enum ImageFormatEnum {
  UNDEFINED("undefined"),

  PNG("png"),
  JPG("jpeg"),
  BMP("bmp"),
  WEBP("webp");

  private final String filenameExtension;

  ImageFormatEnum( String extension ) {
      this.filenameExtension = extension;
  }

  public static ImageFormatEnum fromString( String extension ) {

    ImageFormatEnum r_imageFormatEnum = ImageFormatEnum.UNDEFINED;

    ci_formats: for ( ImageFormatEnum ci_imageFormatEnum : ImageFormatEnum.values() ) {
      if ( ci_imageFormatEnum.filenameExtension.equalsIgnoreCase( extension ) ) {
        r_imageFormatEnum = ci_imageFormatEnum;
        break ci_formats;
      }
    }

    return r_imageFormatEnum;
  }

  public String getFilenameExtension() {
    return filenameExtension;
  }
}



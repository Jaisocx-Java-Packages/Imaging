package com.jaisocx.imaging.Constants;

public enum ImageFormatEnum {
  PNG("png"),
  JPG("jpeg"),
  BMP("bmp"),
  WEBP("webp");

  private final String filenameExtension;

  ImageFormatEnum(String filenameExtension) {
      this.filenameExtension = filenameExtension;
  }

  public String getFilenameExtension() {
      return filenameExtension;
  }
}


package com.jaisocx.imaging.Constants;

import java.nio.file.Path;



public class ImagingConstants {

  protected static ImagingConstants instance = null;
  protected static int isInstantiated = 2;



  public final String PATH_SAVE_PRODUCED_MINI_IMAGE = "produced/mini_images";

  public ImagingConstants() {
      
  }





  public static ImagingConstants getInstance() {
    
    if ( isInstantiated == 3 ) {
      return instance;
    }
    


    if ( isInstantiated == 2 ) {
      instance = new ImagingConstants();

      isInstantiated = 3;
    }

    return instance;

  }

}





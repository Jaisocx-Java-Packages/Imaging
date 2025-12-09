package com.jaisocx.imaging;



import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import com.jaisocx.imaging.Constants.ImageFormatEnum;
import com.jaisocx.imaging.Constants.ImagingConstants;



public class Imaging implements ImagingInterface {

  public String pathToSaveProducedImages = "";



  public Imaging() {
    this.pathToSaveProducedImages = ImagingConstants.getInstance().PATH_SAVE_PRODUCED_MINI_IMAGE;


  }



  public String getPathToSaveProducedImages() {
    return this.pathToSaveProducedImages;
  }

  public void setPathToSaveProducedImages( String inPath ) {
    this.pathToSaveProducedImages = inPath;
  }



  public void save ( 
    BufferedImage bufferedImage, 
    ImageFormatEnum format, 
    String filename
  ) throws IOException {

    String pathToSave = Path.of( this.pathToSaveProducedImages, filename ).toString();
    File savedFile = new File( pathToSave );
    ImageIO.write ( bufferedImage, format.getFilenameExtension(), savedFile );

  }

}





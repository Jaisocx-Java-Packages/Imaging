`Jaisocx Java Packages`

# Imaging
  > 💡 Simple methods: copy, copy overlay, crop, convert.
  > 
  > ❌ doesn't do: ImageConverter.



| 📖️ **Library**  | `jaisocx_imaging` | 🏷️ **ver.**: `2.1` |

| 🗓 **Updated**  | 🌷 Spring 2026 | `Sam, 30 mai 2026 23:56:22 UTC` |

| 📐 **Size**     | 🗂 Folder: 200 KB | 📦 .jar: 155 KB | 📋 .java: 66 KB | 

---



![./readme/images/spread_shutterstock_464833178_550.jpg](./readme/images/spread_shutterstock_464833178_550.jpg)

![./readme/images/shutterstock_464833178_cropped_3_4.png](./readme/images/shutterstock_464833178_cropped_3_4.png)

![./readme/images/shutterstock_464833178_small_crop_3_4.png](./readme/images/shutterstock_464833178_small_crop_3_4.png)



## Console

  ```bash
    ./command/cropper_one.sh
  ```



## Examples

### Cropper

  ```java

    // ./src/main/java/com/jaisocx/app/CropperOne.java
    
    package com.jaisocx.app;

    import ...

    public class CropperOne {
    
      public static void main (String[] args) {


        boolean printsToConsole_true = true;

        Rect srcImage_Rect = new Rect();
        srcImage_Rect.x = Integer.valueOf( args[2] );
        srcImage_Rect.y = Integer.valueOf( args[1] );
        srcImage_Rect.height = Integer.valueOf( args[3] );
        srcImage_Rect.width = Integer.valueOf( args[4] );

        String imageVer = args[0];

        String pathOf_ProducedImageParent_to = args[6];
        String nameOf_ProducedImage_to = args[7];

        String pathOf_srcImage_from = args[8];

        ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[5] );



        Imaging imaging = new Imaging();

        imaging.crop (
            pathOf_srcImage_from,
            pathOf_ProducedImageParent_to,
            nameOf_ProducedImage_to,
            srcImage_Rect,
            imageFormatTo,
            imageVer,
            printsToConsole_true
        );
  
      }
    
    }
  ```



## Interfaces

### Imaging

  **Facade for the Imaging Java package**

  ```java

    // ./src/main/java/com/jaisocx/imaging/Imaging.java
    
    package com.jaisocx.imaging;
    
    import ...
    
    public interface ImagingInterface {
    
      public ImagingFilesystemHelperInterface getImagingFilesystemHelper();
    
      public BaseImagingInterface getBaseImaging();
      public ImageConverterInterface getImageConverter();
      public ImageCropperInterface getImageCropper();
    
    
      public String getPathToSaveProducedImages();
      public void setPathToSaveProducedImages( String inPath );
    
      public ImagingInterface convert (
          String pathOf_readImage_from,
          String pathOf_convertedImage_to,
          ImageFormatEnum imageFormat_readFrom,
          ImageFormatEnum imageFormat_convertTo
      );
    
      public ImagingInterface crop (
          String pathOf_srcImage_from,
          String pathOf_ProducedImageParent_to,
          String nameOf_ProducedImage_to,
          Rect srcImage_Rect,
          ImageFormatEnum imageFormatTo,
          String versionCounter,
          boolean printsToConsole
      );
    
      public BufferedImage cropBufferedImage (
          BufferedImage src_Img,
          Rect srcImage_Rect
      );
    
    }
  ```





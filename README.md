`Jaisocx Java Packages`

# Imaging
  > 💡 Simple methods: copy, copy overlay, crop, convert.
  > 
  > ❌ doesn't do: ImageConverter.



| 📖️ **Library**  | `jaisocx_imaging` | 🏷️ **ver.**: `1.4` |

| 🗓 **Updated**  | 🌷 Spring 2026 | `Sam, 16 mai 2026 23:56:22 UTC` |

| 📐 **Size**     | 🗂 Folder: 200 KB | 📦 .jar: 155 KB | 📋 .java: 66 KB | 

| ⚡ **JS Engine** | 🌐 Browser: ❌ no | 🧭 Express: ❌ no | 🖥️ Console: ✅ yes |

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
    
        Rect srcImage_Rect = new Rect();
        srcImage_Rect.x = Integer.valueOf( args[1] );
        srcImage_Rect.y = Integer.valueOf( args[2] );
        srcImage_Rect.width = Integer.valueOf( args[3] );
        srcImage_Rect.height = Integer.valueOf( args[4] );
    
        String versionCounter = args[0];
    
        String pathOf_srcImage_from = args[6];
    
        ImageFormatEnum imageFormatTo = ImageFormatEnum.fromString( args[5] );
    
    
    
        Imaging imaging = new Imaging();
    
        imaging.crop (
            pathOf_srcImage_from,
            srcImage_Rect,
            imageFormatTo,
            versionCounter
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
          Rect srcImage_Rect,
          ImageFormatEnum imageFormatTo,
          String versionCounter
      );
    
      public BufferedImage cropBufferedImage (
          BufferedImage src_Img,
          Rect srcImage_Rect
      );
    
    }
  ```





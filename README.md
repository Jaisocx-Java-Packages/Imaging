
![node_cdn_installs/software_labels/softlabel_jaisocx.svg](./node_cdn_installs/software_labels/softlabel_jaisocx.svg)

-  📙  You are HOME


# Imaging

  >  💡 Simple methods: copy, copy overlay, crop, downgrade.


## Techno

-  ❌  in engineering  **Downgrader**

-  ✅  **ImageCropper**


## Libraries

-  ✅  **Example Classes**  in  `com.jaisocx.imaging.app`

-  ✅  **Imaging** the main class to use other base classes of Imaging library

-  ✅  **Awt Base Imaging Classes** via `java.awt`

-  ✅  **Calculations Base Imaging Classes**


---


| 📖️ **Library**  | `jaisocx_imaging` | 🏷️ **ver.**: `2.4.3` |

| 🗓 **Updated**  |  🌾  Autumn  2026 | `01st of November in year AD 2026 12:40 UTC 24h` |

| 📐 **Size**     | 🗂 Folder: 1.4 MB | 📦 .jar: 79 KB | 📋 .java: 170 KB | 

| **Build tool for Java projects Conf** |  ✅  Maven |

| **Test** |  ✅  Examples call by command line shell  |  ✅  Examples  |  

| **Plan** |   ✅  Unit tests, ❌ No Upscaler planned. Imaging was thought by another technology, for the base tasks having cheaper. |

---



## News

- 🗓  🌾  Autumn 2026, 01 November 2026  ✅  **Upgrades for Java infrastructure** nice commands for .jar, and the upgraded Combiner moved to .jar

- 🗓  🌾  Autumn 2026, 06 September 2026  ❌  in engineering **Downgrader** new Java class, pair of new interfaces.

- 🗓  🌾  Autumn 2026, 06 September 2026  ✅  **Cropper by MaxSize of the original image's size** new Java class.

- 🗓  🌼  Summer 2026, 26 July 2026  ✅  **Cropper by Combiner** new Java class, preview in automated `.html` page.
 
- 🗓  🌼  Summer 2026, 25 July 2026 **documentation and examples on building .jars and pushing to Git repo.** `pom.xml` and `.gitignore`

---



##  📚 README

  >  **Contents**:  📚  [readme/README.md](./readme/README.md)

a. **Loading Font**:  📙  [readme/r_font.md](./readme/r_font.md)

b. **Maven conf** `pom.xml`:  📙  [readme/r_maven.md](./readme/r_maven.md)

c. **Publish control** `.gitignore`:  📙  [readme/r_ignores.md](./readme/r_ignores.md)



## Build tools for Java projects

-   ✅  **Maven**: Config gitignored: `./example_pom_xml.txt` -> `pom.xml`

```bash
  mvn clean
  mvn compile
  mvn package
```
  


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
    
    package com.jaisocx.imaging.app;

    import ...

    public class CropperOne {
    
      public static void main (String[] args) {


        boolean printsToConsole_true = true;

        Rect srcImage_Dimension = new Rect();
        srcImage_Dimension.x = Integer.valueOf( args[2] );
        srcImage_Dimension.y = Integer.valueOf( args[1] );
        srcImage_Dimension.height = Integer.valueOf( args[3] );
        srcImage_Dimension.width = Integer.valueOf( args[4] );

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
            srcImage_Dimension,
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
          Rect srcImage_Dimension,
          ImageFormatEnum imageFormatTo,
          String versionCounter,
          boolean printsToConsole
      );
    
      public BufferedImage cropBufferedImage (
          BufferedImage src_Img,
          Rect srcImage_Dimension
      );
    
    }
  ```


Have a nice day, ILLIA POLIANSKYI



package com.jaisocx.imaging.helpers;



import java.awt.*;
import com.jaisocx.imaging.helpers.helper_interface.ColorBaseInterface;



public class ColorBase implements ColorBaseInterface {

    public Color getColor (
      int a,
      String inColor
    ) {

      Color locColor = Color.decode ( inColor );

      int r = locColor.getRed();
      int g = locColor.getGreen();
      int b = locColor.getBlue();

      Color colorWithAlpha = new Color ( r, g, b, a );

      return colorWithAlpha;
    }

    public int getRgba (
      int r,
      int g,
      int b,
      int a
    ) {
      Color colorWithAlpha = new Color ( r, g, b, a );
      int rgbOfColorWithAlpha = colorWithAlpha.getRGB();

      return rgbOfColorWithAlpha;
    }

    public int getColorRgba (
      int Alpha,
      String inColor
    ) {

      Color colorWithAlpha = this.getColor ( Alpha, inColor );
      int rgbOfColorWithAlpha = colorWithAlpha.getRGB();


      return rgbOfColorWithAlpha;
    }

}





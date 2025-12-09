package com.jaisocx.imaging.helpers.helper_interface;

import java.awt.Color;



public interface ColorBaseInterface {

    public Color getColor (
      int Alpha,
      String inColor
    );

    public int getRgba (
      int r,
      int g,
      int b,
      int a
    );

    public int getColorRgba (
      int Alpha,
      String inColor
    );

}


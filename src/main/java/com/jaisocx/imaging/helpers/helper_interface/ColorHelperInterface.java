package com.jaisocx.imaging.helpers.helper_interface;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jaisocx.imaging.imaging_types.Dimension;


public interface ColorHelperInterface {

    public int overlayColor (
      int Alpha,
      Color inColor,
      Graphics2D g,
      Dimension dimension
    );

    public int fillVerticalGradient (
      Color topColor, 
      Color bottomColor,
      Graphics2D g,
      Dimension dimension
    );

}





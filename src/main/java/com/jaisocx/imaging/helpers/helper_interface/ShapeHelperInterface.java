package com.jaisocx.imaging.helpers.helper_interface;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jaisocx.imaging.imaging_types.Dimension;


public interface ShapeHelperInterface extends ColorBaseInterface {


  public void drawShadowRect (
      Color color, 
      int shadowSize,
      Graphics2D g,
      Dimension dimension
  );


  public void drawInnerBorder (
      Color color, 
      int borderPxNumber,
      Graphics2D g,
      Dimension dimension,
      int arcWH
  );

}
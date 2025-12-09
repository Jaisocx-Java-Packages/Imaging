package com.jaisocx.imaging.helpers.helper_interface;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jaisocx.imaging.types.Rect;

public interface ShapeHelperInterface {


  public void drawShadowRect (
      Color color, 
      int shadowSize,
      Graphics2D g,
      Rect rect
  );


  public void drawInnerBorder (
      Color color, 
      int borderPxNumber,
      Graphics2D g,
      Rect rect,
      int arcWH
  );

}
package com.jaisocx.imaging.helpers.helper_interface;



import java.awt.Font;
import java.awt.Graphics2D;

import com.jaisocx.imaging.types.Rect;



public interface TextHelperInterface {

    public TextHelperInterface setText (
      int Alpha,
      String inColor,
      Font font,
      String text,
      Rect rect,
      Graphics2D g,
      int x, 
      int y,
      boolean toCenter
    );

}



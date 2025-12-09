package com.jaisocx.imaging.helpers;



import java.awt.*;

import com.jaisocx.imaging.helpers.helper_interface.TextHelperInterface;
import com.jaisocx.imaging.types.Rect;



public class TextHelper extends ColorBase implements TextHelperInterface {

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
    ) {

      Color locColor = this.getColor( Alpha, inColor );
      g.setColor( locColor );


      g.setFont(font);

      // Get font metrics
      FontMetrics metrics = g.getFontMetrics(font);
      int textWidth = 0;
      int textHeight = 0;
      int ascent = 0;

      // Centering the text
      int centered_X_Offset = 0;
      int centered_Y_Offset = 0;

      if ( toCenter == true ) {
        // Get font metrics
        metrics = g.getFontMetrics(font);
        textWidth = metrics.stringWidth(text);
        textHeight = metrics.getHeight();
        ascent = metrics.getAscent();

        centered_X_Offset = ( ( rect.width - textWidth ) / 2 );
        centered_Y_Offset = ( ( rect.height - textHeight ) / 2 ) + ascent;

        x = centered_X_Offset;
        y = centered_Y_Offset;
      }



      g.drawString(text, x, y);

      //g.dispose();


      return this;
    }

}





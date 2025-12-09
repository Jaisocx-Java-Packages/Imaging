package com.jaisocx.imaging.helpers;



import java.awt.*;
import com.jaisocx.imaging.helpers.helper_interface.ColorHelperInterface;
import com.jaisocx.imaging.types.Rect;



public class ColorHelper implements ColorHelperInterface {

    public int overlayColor (
      int Alpha,
      Color inColor,
      Graphics2D g,
      Rect rect
    ) {

      AlphaComposite Alphacomposite = AlphaComposite.getInstance( AlphaComposite.SRC_OVER, Alpha / 255f );

      g.setComposite( Alphacomposite );
      g.setColor( inColor );

      g.fillRect( 
        rect.x, 
        rect.y, 
        rect.width, 
        rect.height 
      );

      // g.dispose();


      return 3;
    }

    public int fillVerticalGradient (
      Color topColor, 
      Color bottomColor,
      Graphics2D g,
      Rect rect
    ) {

        GradientPaint paint = new GradientPaint (
          rect.x, 
          rect.y, 
          topColor, 
          rect.x, 
          rect.height, 
          bottomColor
        );

        g.setPaint(paint);
        g.fillRect( rect.x, rect.y, rect.width, rect.height );
        
        // g.dispose();


      return 3;
    }

}





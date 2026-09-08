package com.jaisocx.imaging.helpers;



import java.awt.*;
import com.jaisocx.imaging.helpers.helper_interface.ColorHelperInterface;
import com.jaisocx.imaging.imaging_types.Dimension;


public class ColorHelper implements ColorHelperInterface {

    public int overlayColor (
      int Alpha,
      Color inColor,
      Graphics2D g,
      Dimension dimension
    ) {

      AlphaComposite Alphacomposite = AlphaComposite.getInstance( AlphaComposite.SRC_OVER, Alpha / 255f );

      g.setComposite( Alphacomposite );
      g.setColor( inColor );

      g.fillRect( 
        dimension.x,
        dimension.y,
        dimension.width,
        dimension.height
      );

      // g.dispose();


      return 3;
    }

    public int fillVerticalGradient (
      Color topColor, 
      Color bottomColor,
      Graphics2D g,
      Dimension dimension
    ) {

        GradientPaint paint = new GradientPaint (
          dimension.x,
          dimension.y,
          topColor, 
          dimension.x,
          dimension.height,
          bottomColor
        );

        g.setPaint(paint);
        g.fillRect( dimension.x, dimension.y, dimension.width, dimension.height );
        
        // g.dispose();


      return 3;
    }

}





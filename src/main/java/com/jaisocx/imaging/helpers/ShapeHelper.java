package com.jaisocx.imaging.helpers;



import java.awt.*;
import com.jaisocx.imaging.helpers.helper_interface.ShapeHelperInterface;
import com.jaisocx.imaging.imaging_types.Dimension;



public class ShapeHelper extends ColorBase implements ShapeHelperInterface {

    // Basic effect #2: drop shadow rectangle
    public void drawShadowRect (
      Color color, 
      int shadowSize,
      Graphics2D g,
      Dimension dimension
    ) {

        g.setColor(new Color(0, 0, 0, 80));
        g.fillRoundRect(dimension.x + shadowSize, dimension.y + shadowSize, dimension.width, dimension.height, 12, 12);
        g.setColor(color);
        g.fillRoundRect(dimension.x, dimension.y, dimension.width, dimension.height, 12, 12);

        //g.dispose();

    }

    // Basic effect #3: inner border
    public void drawInnerBorder (
      Color color, 
      int borderPxNumber,
      Graphics2D g,
      Dimension dimension,
      int arcWH
    ) {

        g.setColor(color);

        int iPlusOne = 1;
        int leftAndRightBordersNumber = 0;

        int leftBoderX = 0;
        int topBorderY = 0;
        int rightBorderX = 0;
        int bottomBorderY = 0;

        boolean toDrawRounded = ( arcWH != 0 );

        for (int i = 0; i < borderPxNumber; i++) {

            topBorderY = ( dimension.y + i );
            leftBoderX = ( dimension.x + i );

            iPlusOne = ( i + 1 );
            leftAndRightBordersNumber = ( iPlusOne );

            rightBorderX = ( dimension.x + ( dimension.width - leftAndRightBordersNumber ) );
            bottomBorderY = ( dimension.y + ( dimension.height - leftAndRightBordersNumber ) );

            if ( toDrawRounded == true ) {
              g.drawRoundRect ( 
                leftBoderX, 
                topBorderY, 
                rightBorderX, 
                bottomBorderY,
                arcWH,
                arcWH
              );
        
              arcWH -= 1;

            } else {
              g.drawRect ( 
                leftBoderX, 
                topBorderY, 
                rightBorderX, 
                bottomBorderY
              );

            }

        }

        // g.dispose();

    }

}





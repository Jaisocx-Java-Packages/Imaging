package com.jaisocx.imaging.helpers;



import java.awt.*;
import com.jaisocx.imaging.helpers.helper_interface.ShapeHelperInterface;
import com.jaisocx.imaging.types.Rect;



public class ShapeHelper extends ColorBase implements ShapeHelperInterface {

    // Basic effect #2: drop shadow rectangle
    public void drawShadowRect (
      Color color, 
      int shadowSize,
      Graphics2D g,
      Rect rect
    ) {

        g.setColor(new Color(0, 0, 0, 80));
        g.fillRoundRect(rect.x + shadowSize, rect.y + shadowSize, rect.width, rect.height, 12, 12);
        g.setColor(color);
        g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 12, 12);

        //g.dispose();

    }

    // Basic effect #3: inner border
    public void drawInnerBorder (
      Color color, 
      int borderPxNumber,
      Graphics2D g,
      Rect rect,
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

            topBorderY = ( rect.y + i );
            leftBoderX = ( rect.x + i );

            iPlusOne = ( i + 1 );
            leftAndRightBordersNumber = ( iPlusOne );

            rightBorderX = ( rect.x + ( rect.width - leftAndRightBordersNumber ) );
            bottomBorderY = ( rect.y + ( rect.height - leftAndRightBordersNumber ) );

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





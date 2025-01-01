// Krish Doshi
// 11/03/2024
// CSE 123 
// C2: Mondrian
// TA: Cynthia

import java.awt.*;

//Represents a Mondrian Painting in which the canvas is randomly divided by vertical and
//horizontal lines into different sections that are colored either white, red, cyan, or yellow
public class Mondrian {


    // Behavior: 
    //          - creates a Mondrian painting based on the size of the painting specified by
    //            the client. The painting is divided into smaller regions until the height and
    //            width of each region is less than one fourth of the height and the width of
    //            the total painting. If the height of a region is greater, then the region is
    //            divided horizontally. If the width of a region is greater, then the region is 
    //            divided vertically. If both are greater, then the region is divided both ways.
    // Parameters:
    //          - 'pixels': represents the whole mondrian painting as pixels (assumed
    //                      that it is non-null and that the width and length provided is >= 300
    //                      pixels)
    public void paintBasicMondrian(Color[][] pixels){
        paintBasicMondrian(pixels, 0, 0, 
                pixels[0].length - 1, pixels.length - 1);

    }

    // Behavior: 
    //          - creates a Mondrian painting based on the size of the painting specified by
    //            the client. The painting is divided into smaller regions until the height and
    //            width of each region is less than one fourth of the height and the width of
    //            the total painting. If the height of a region is greater, then the region is
    //            divided horizontally. If the width of a region is greater, then the region is 
    //            divided vertically. If both are greater, then the region is divided both ways.
    // Parameters:
    //          - 'pixels': represents the whole mondrian painting as pixels (assumed
    //                      that it is non-null and that the width and length provided is >= 300
    //                      pixels)
    //          - 'topLeftX': represents the top-left X coordinate of the current specified
    //                        region that is being filled a certain color
    //          - 'topLeftY': represents the top-left Y coordinate of the current specified
    //                        region that is being filled a certain color
    //          - 'bottomRightX': represents the bottom-right X coordinate of the current
    //                            specified region that is being filled a certain color
    //          - 'bottomRightY': represents the bottom-right Y coordinate of the current 
    //                            specified region that is being filled a certain color
    private void paintBasicMondrian(Color[][] pixels, int topLeftX, int topLeftY, 
            int bottomRightX, int bottomRightY){

        //specified region has a height and width less than one fourth of the height and width
        //of the whole painting
        if(bottomRightY - topLeftY < (pixels.length / 4) &&
                bottomRightX - topLeftX < (pixels[0].length / 4)){

            int colorChooser = (int)(Math.random() * 4);
            Color fillColor;

            if(colorChooser == 0){
                fillColor = Color.RED;
            }else if(colorChooser == 1){
                fillColor = Color.YELLOW;
            }else if(colorChooser == 2){
                fillColor = Color.CYAN;
            }else{
                fillColor = Color.WHITE;
            }

            fill(fillColor, pixels, topLeftX, topLeftY, bottomRightX, bottomRightY);
            
        }else{
        
            //specified region has a height and width greater than one fourth of the height
            //and width of the whole painting
            if(bottomRightY - topLeftY >= (pixels.length / 4) &&
                    bottomRightX - topLeftX >= (pixels[0].length / 4)){

                int horizontalDividerY = calculateHorizontalDividerY(topLeftY, bottomRightY);
                int verticalDividerX = calculateVerticalDividerX(topLeftX, bottomRightX);

                //divided top-left region
                paintBasicMondrian(pixels, topLeftX, topLeftY, 
                        verticalDividerX, horizontalDividerY);

                //divided top-right region
                paintBasicMondrian(pixels, verticalDividerX, topLeftY,
                        bottomRightX, horizontalDividerY);

                //divided bottom-left region
                paintBasicMondrian(pixels, topLeftX, horizontalDividerY, 
                        verticalDividerX, bottomRightY);

                //divided bottom-right region
                paintBasicMondrian(pixels, verticalDividerX, horizontalDividerY, 
                        bottomRightX, bottomRightY);

            
            //specified region has only a height greater than or equal to one fourth of the 
            //height of the whole painting
            }else if(bottomRightY - topLeftY >= (pixels.length / 4)){
                int horizontalDividerY = calculateHorizontalDividerY(topLeftY, bottomRightY);

                //divided top region
                paintBasicMondrian(pixels, topLeftX, topLeftY, bottomRightX, horizontalDividerY);

                //divided bottom region
                paintBasicMondrian(pixels, topLeftX, horizontalDividerY, 
                        bottomRightX, bottomRightY);

            //specified region has only a width greater than or equal to one fourth of the 
            //width of the whole painting
            }else if (bottomRightX - topLeftX >= (pixels[0].length / 4)){
                int verticalDividerX = calculateVerticalDividerX(topLeftX, bottomRightX);

                //divided left region
                paintBasicMondrian(pixels, topLeftX, topLeftY, verticalDividerX, bottomRightY);

                //divided right region
                paintBasicMondrian(pixels, verticalDividerX, topLeftY, 
                        bottomRightX, bottomRightY);


            }
        }
    }

    // Behavior: 
    //          - creates a Mondrian painting based on the size of the painting specified by
    //            the client. The painting is divided into smaller regions until the height and
    //            width of each region is less than one fourth of the height and the width of
    //            the total painting. If the height of a region is greater, then the region is
    //            divided horizontally. If the width of a region is greater, then the region is 
    //            divided vertically. If both are greater, then the region is divided both ways.
    //            Each region is more likely to be a certain color if they are in the 
    //            top-left (white), top-right (red), bottom-left (cyan), or bottom-right (yellow)
    //            of the painting
    // Parameters:
    //          - 'pixels': represents the whole mondrian painting as pixels (assumed
    //                      that it is non-null and that the width and length provided is >= 300
    //                      pixels)
    public void paintComplexMondrian(Color[][] pixels){
        paintComplexMondrian(pixels, 0, 0, 
                pixels[0].length - 1, pixels.length - 1);

    }

    // Behavior: 
    //          - creates a Mondrian painting based on the size of the painting specified by
    //            the client. The painting is divided into smaller regions until the height and
    //            width of each region is less than one fourth of the height and the width of
    //            the total painting. If the height of a region is greater, then the region is
    //            divided horizontally. If the width of a region is greater, then the region is 
    //            divided vertically. If both are greater, then the region is divided both ways.
    //            Each region is more likely to be a certain color if they are in the 
    //            top-left (white), top-right (red), bottom-left (cyan), or bottom-right (yellow)
    //            of the painting
    // Parameters:
    //          - 'pixels': represents the whole mondrian painting as pixels (assumed
    //                      that it is non-null and that the width and length provided is >= 300
    //                      pixels)
    //          - 'topLeftX': represents the top-left X coordinate of the current specified
    //                        region that is being filled a certain color
    //          - 'topLeftY': represents the top-left Y coordinate of the current specified
    //                        region that is being filled a certain color
    //          - 'bottomRightX': represents the bottom-right X coordinate of the current
    //                            specified region that is being filled a certain color
    //          - 'bottomRightY': represents the bottom-right Y coordinate of the current 
    //                            specified region that is being filled a certain color
    private void paintComplexMondrian(Color[][] pixels, int topLeftX, 
            int topLeftY, int bottomRightX, int bottomRightY){

        if(bottomRightY - topLeftY < (pixels.length / 4) &&
                bottomRightX - topLeftX < (pixels[0].length / 4)){

            double midPointX = (topLeftX + bottomRightX) / 2;
            double midPointY = (topLeftY + bottomRightY) / 2;

            double distanceFromTopLeft = calculateDistance(midPointX, 0, 
                    midPointY, 0);

            double distanceFromBottomLeft = calculateDistance(midPointX, 0, 
                    midPointY, (pixels.length - 1));

            double distanceFromTopRight = calculateDistance(midPointX, (pixels[0].length - 1), 
                    midPointY, 0);

            double distanceFromBottomRight = calculateDistance(midPointX, (pixels[0].length), 
                    midPointY, (pixels.length - 1));

            double smallestDistance = Math.min(distanceFromTopLeft, 
                    Math.min(distanceFromBottomLeft, Math.min(distanceFromTopRight, 
                            distanceFromBottomRight)));

            Color fillColor;

            //closest to top-left (white most likely)
            if(smallestDistance == distanceFromTopLeft){
                int colorChooser = (int)(Math.random() * 10);

                if(colorChooser == 0){
                    fillColor = Color.RED;
                }else if(colorChooser == 1){
                    fillColor = Color.YELLOW;
                }else if(colorChooser == 2){
                    fillColor = Color.CYAN;
                }else{
                    fillColor = Color.WHITE;
                }

            //closest to top-right (red most likely)
            }else if(smallestDistance == distanceFromTopRight){
                int colorChooser = (int)(Math.random() * 10);

                if(colorChooser == 0){
                    fillColor = Color.WHITE;
                }else if(colorChooser == 1){
                    fillColor = Color.YELLOW;
                }else if(colorChooser == 2){
                    fillColor = Color.CYAN;
                }else{
                    fillColor = Color.RED;
                }

            //closest to bottom-left (cyan most likely)
            }else if(smallestDistance == distanceFromBottomLeft){
                int colorChooser = (int)(Math.random() * 10);

                if(colorChooser == 0){
                    fillColor = Color.WHITE;
                }else if(colorChooser == 1){
                    fillColor = Color.YELLOW;
                }else if(colorChooser == 2){
                    fillColor = Color.RED;
                }else{
                    fillColor = Color.CYAN;
                }

            //closest to bottom-right (yellow most likely)
            }else{
                int colorChooser = (int)(Math.random() * 10);

                if(colorChooser == 0){
                    fillColor = Color.WHITE;
                }else if(colorChooser == 1){
                    fillColor = Color.CYAN;
                }else if(colorChooser == 2){
                    fillColor = Color.RED;
                }else{
                    fillColor = Color.YELLOW;
                }

            }

            fill(fillColor, pixels, topLeftX, topLeftY, bottomRightX, bottomRightY);
            
        }else{
        
            //specified region has a height and width greater than one fourth of the height
            //and width of the whole painting
            if(bottomRightY - topLeftY >= (pixels.length / 4) &&
                    bottomRightX - topLeftX >= (pixels[0].length / 4)){

                int horizontalDividerY = calculateHorizontalDividerY(topLeftY, bottomRightY);
                int verticalDividerX = calculateVerticalDividerX(topLeftX, bottomRightX);

                //divided top-left region 
                paintComplexMondrian(pixels, topLeftX, topLeftY, 
                        verticalDividerX, horizontalDividerY);

                //divided top-right region
                paintComplexMondrian(pixels, verticalDividerX, topLeftY, 
                        bottomRightX, horizontalDividerY);

                //divided bottom-left region
                paintComplexMondrian(pixels, topLeftX, horizontalDividerY, 
                        verticalDividerX, bottomRightY);

                //divided bottom-right region
                paintComplexMondrian(pixels, verticalDividerX, horizontalDividerY, 
                        bottomRightX, bottomRightY);

            //specified region has only a height greater than or equal to one fourth of the 
            //height of the whole painting
            }else if(bottomRightY - topLeftY >= (pixels.length / 4)){
                int horizontalDividerY = calculateHorizontalDividerY(topLeftY, bottomRightY);

                //divided top region
                paintComplexMondrian(pixels, topLeftX, topLeftY, 
                        bottomRightX, horizontalDividerY);

                //divided bottom region
                paintComplexMondrian(pixels, topLeftX, horizontalDividerY, 
                        bottomRightX, bottomRightY);

            //specified region has only a width greater than or equal to one fourth of the 
            //width of the whole painting
            }else if (bottomRightX - topLeftX >= (pixels[0].length / 4)){
                int verticalDividerX = calculateVerticalDividerX(topLeftX, bottomRightX);

                //divided left region
                paintComplexMondrian(pixels, topLeftX, topLeftY, 
                        verticalDividerX, bottomRightY);

                //divided right region
                paintComplexMondrian(pixels, verticalDividerX, topLeftY, 
                        bottomRightX, bottomRightY);


            }
        }
    }


    // Behavior: 
    //          - fills a specified region (that is less than one fourth of the painting's height
    //            and width) of the mondrian painting (specified by the top-left X, top-left Y, 
    //            bottom-right X, and bottom-right Y coordinates) that will be filled
    //            a specific color
    // Parameters:
    //          - 'fillColor': represents the color that the current specified region 
    //                         should be colored
    //          - 'pixels': represents the whole mondrian painting as pixels (assumed
    //                      that it is non-null and that the width and length provided is >= 300
    //                      pixels)
    //          - 'topLeftX': represents the top-left X coordinate of the current specified
    //                        region that is being filled a certain color
    //          - 'topLeftY': represents the top-left Y coordinate of the current specified
    //                        region that is being filled a certain color
    //          - 'bottomRightX': represents the bottom-right X coordinate of the current
    //                            specified region that is being filled a certain color
    //          - 'bottomRightY': represents the bottom-right Y coordinate of the current 
    //                            specified region that is being filled a certain color
    private void fill(Color fillColor, Color[][] pixels, int topLeftX, 
            int topLeftY, int bottomRightX, int bottomRightY){

        for(int i = topLeftY + 1; i < bottomRightY - 1; i++){
            for(int j = topLeftX + 1; j < bottomRightX - 1; j++){
                pixels[i][j] = fillColor;
            }
        }
    }

    // Behavior: 
    //          - randomly calculates the Y coordinate where the current specified region
    //            will be divided horizontally (making sure that the divider is no closer 
    //            than 10 pixels to the top edge or bottom edge of the specified region)
    // Returns: 
    //          - the Y coordinate of where the current specified region will be 
    //            divided horizontally
    // Parameters:
    //          - 'topLeftY': represents the top-left Y coordinate of the current
    //                        specified region
    //          - 'bottomRightY': represents the bottom-right Y coordinate of the current 
    //                            specified region
    private int calculateHorizontalDividerY(int topLeftY, int bottomRightY){
        int horizontalDividerY = (int)((Math.random() * (bottomRightY - topLeftY)) + topLeftY);
        horizontalDividerY = Math.max(topLeftY + 10, horizontalDividerY);
        horizontalDividerY = Math.min(bottomRightY - 10, horizontalDividerY);

        return horizontalDividerY;
    }

    // Behavior: 
    //          - randomly calculates the X coordinate where the current specified region
    //            will be divided vertically (making sure that the divider is no closer 
    //            than 10 pixels to the left edge or right edge of the specified region)
    // Returns: 
    //          - the X coordinate of where the current specified region will be
    //            divided vertically
    // Parameters:
    //          - 'topLeftX': represents the top-left X coordinate of the current
    //                        specified region
    //          - 'bottomRightX': represents the bottom-right X coordinate of the current 
    //                            specified region
    private int calculateVerticalDividerX(int topLeftX, int bottomRightX){
        int verticalDividerX = (int)((Math.random() * (bottomRightX - topLeftX)) + topLeftX);
        verticalDividerX = Math.max(topLeftX + 10, verticalDividerX);
        verticalDividerX = Math.min(bottomRightX - 10, verticalDividerX);

        return verticalDividerX;

    }

    // Behavior: 
    //          - calculates the distance between two points
    // Returns: 
    //          - the distance between two points
    // Parameters:
    //          - 'x1': x coordinate of first point
    //          - 'x2': x coordinate of second point
    //          - 'y1': y coordinate of first point
    //          - 'y2': y coordinate of second point
    private double calculateDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
          
}

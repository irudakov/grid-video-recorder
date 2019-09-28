package io.nirvagi.utils.recorder;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageProcessor {

    public static BufferedImage addTextCaption(BufferedImage image, String line1, String line2,
                                               String line3, String line4) {
        try {
            Graphics g = image.getGraphics();

            final int imageHeight = image.getHeight();
            final int imageWidth = image.getWidth();
            final int borderHeight = 52;
            final int firstLineStartHeight = imageHeight - borderHeight;
            final int textLeftStartPosition = 40;

            Color backgroundColor = new Color(0, 0, 0, 200);
            Color fontColor = new Color(255, 255, 255, 255);


            g.setColor(backgroundColor);

            //Add rectangle at the very bottom of the screen
            g.fillRect(0, firstLineStartHeight, imageWidth, borderHeight);

            g.setColor(fontColor);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
//      g.setFont(g.getFont().deriveFont(12f)); //Set text size

            g.drawString("" + line1, textLeftStartPosition, firstLineStartHeight + 12);
            g.drawString("" + line2, textLeftStartPosition, firstLineStartHeight + 24);
            g.drawString("" + line3, textLeftStartPosition, firstLineStartHeight + 36);
            g.drawString("" + line4, textLeftStartPosition, firstLineStartHeight + 48);
            g.dispose();

        } catch (Exception e) {
            System.out.println("Input lines where: " + line1 + " - " + line2 + " - " + line3 + " - " + line4);
            System.out.println("Problem with adding caption to screenshot");
            System.out.println(e);
            e.printStackTrace();
        }

        return image;


    }

    public static BufferedImage createTitleFrame(Dimension dimension, int imageType, String line1,
                                                 String line2,
                                                 String line3) {
        BufferedImage image = new BufferedImage(dimension.width, dimension.height, imageType);

        Graphics g = image.getGraphics();
        Color titleFrameFontColor = new Color(255,255,255,255);
        g.setColor(titleFrameFontColor);

        int height = image.getHeight();
        int width = image.getWidth();
        int firstLineX = ((Double) (width * 0.1)).intValue();
        int firstLineY = ((Double) (width * 0.1)).intValue();
        int secondLineY = firstLineY + 20;
        int thirdLineY = secondLineY + 12;

        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        g.drawString("" + line1, firstLineX, firstLineY);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g.drawString("" + line2, firstLineX, secondLineY);
        g.drawString("" + line3, firstLineX, thirdLineY);

        return image;
    }
}

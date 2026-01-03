package com.example.threads;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.Callable;

public class ScreenShotThread implements Callable<Void> {

    public void takeScreenShot() {
        try {
            for(int i=0; i<1000000000; i++) {
                // Create Robot instance
                Robot robot = new Robot();

                // Get screen size
                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

                // Capture screenshot
                BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

                Thread.sleep(500);
                // Save to file
                String folderPath = "/home/shamim/Pictures/ScreenShotsFromProgram/";
                String fileName = folderPath + "Screenshot" + i + ".png";
                ImageIO.write(screenFullImage, "png", new File(fileName));

                System.out.println("Screenshot saved: " + fileName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Void call() throws Exception {
        this.takeScreenShot();
        return null;
    }
}

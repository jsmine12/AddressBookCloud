package com.AddressBookClient.Utils;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
public class ScreenSize {
    public static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    public static double centerX = (screenBounds.getWidth()) / 2.6;
    public static double centerY = (screenBounds.getHeight()) / 3;
    public static double suitHomePageX = (screenBounds.getWidth()) / 3;
    public static double suitHomepageY = (screenBounds.getHeight()) / 8;
}
//            1649453887
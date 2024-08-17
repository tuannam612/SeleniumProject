package Locators;

import org.openqa.selenium.By;

public class AboutUsLocators {
    public By aboutUs_button = By.xpath("//a[normalize-space()='About us']");
    public By aboutUs_title = By.id("videoModalLabel");
    public By aboutUs_modal = By.xpath("//div[@id='videoModal']//div[@class='modal-content']");
    public By aboutUs_poster = By.cssSelector(".vjs-poster");
    public By aboutUs_video = By.id("example-video_html5_api");
    public By aboutUs_close_button = By.xpath("//div[@id='videoModal']//button[@type='button'][normalize-space()='Close']");
}

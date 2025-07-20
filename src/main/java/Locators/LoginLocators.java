package Locators;

import org.openqa.selenium.By;

public class LoginLocators {
    public By startBtn = By.xpath("//a[@class='rounded-/75 disabled:bg-(--ui-primary) aria-disabled:bg-(--ui-primary) focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-(--ui-primary) h-[66px] justify-center text-[23px] font-extrabold px-8']");
    public By usrNameField = By.id("//input[@id='email']");
    public By pwdField = By.xpath("//input[@id='password']");
    public By logginBtn = By.xpath("//button[@type='submit']"); 
}

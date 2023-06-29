import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Scanner;

@Slf4j
public class Automation {

    String XPATHPESQUISARGOOGLE = "//*[@id=\"APjFqb\"]";
    String XPATHBUSCARCONTATOWPP = "//*[@id=\"side\"]/div[1]/div/div/div[2]/div/div[1]/p";
    String XPATHBUSCARCONTATOMON = "//*[@id=\"pane-side\"]/div[1]/div/div/div[8]/div/div/div/div[2]/div[1]/div[1]/span/span";
    String XPATHCAIXADETEXTO = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p";
    String XPATHCAIXADETEXTOENVIAR = "//*[@id=\"app\"]/div/div/div[3]/div[2]/span/div/span/div/div/div[2]/div/div[1]/div[3]/div/div/div[2]/div[1]/div[1]/p";

    @Test
    public void sendMessagesWhatsAppMon() throws InterruptedException, AWTException {

        var currentDay = LocalDate.now();
        var weddingAquino = LocalDate.of(2023, 10, 14);
        Duration diff = Duration.between(currentDay.atStartOfDay(), weddingAquino.atStartOfDay());
        var missingDays = diff.toDays();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--user-data-dir=C:\\Users\\Lenovo\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");

        System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
        WebDriver navigator = new ChromeDriver(chromeOptions);
        navigator.manage().window().maximize();

        navigator.get("https://web.whatsapp.com/");
        String parent = navigator.getWindowHandle();

        WebDriver newPage = navigator.switchTo().newWindow(WindowType.TAB);
        newPage.get("https://www.google.com/search?q=macacos+tristes&tbm=isch&sa=X&ved=2ahUKEwjs7LrnwOf_AhWVvJUCHfnEDPYQ0pQJegQIDBAB&biw=1680&bih=894&dpr=1");

        try {
            // These coordinates are screen coordinates
            int xCoord = 1420;
            int yCoord = 400;

            // Move the cursor
            Robot robot = new Robot();
            robot.mouseMove(xCoord, yCoord);

            //Click on Image
            int mask = InputEvent.BUTTON1_DOWN_MASK;
            robot.mousePress(mask);
            robot.mouseRelease(mask);

            // Move the cursor to image Higher
            int xCoordNav = 2700;
            int yCoordNav = 410;
            robot.mouseMove(xCoordNav, yCoordNav);

            Thread.sleep(2000);
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            int xCoordShare = 2750;
            int yCoordShare = 650;

            robot.mouseMove(xCoordShare, yCoordShare);
            Thread.sleep(500);
            robot.mouseMove(2752, 650);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        } catch (AWTException e) {
        }

        newPage.close();
        navigator.switchTo().window(parent);

        Thread.sleep(20000);

        //TODO - FAZER UM CTRL C + CTRL V - CLICANDO E USANDO O ROBOT || FECHAR A JANELA E JOGAR NO MONKEYS - CTRL V E ENTER

        navigator.findElement(By.xpath(XPATHBUSCARCONTATOWPP)).sendKeys("monkeys");
        Thread.sleep(2000);
        navigator.findElement(By.xpath(XPATHBUSCARCONTATOMON)).click();
        Thread.sleep(1000);
        navigator.findElement(By.xpath(XPATHCAIXADETEXTO)).click();

        Actions actions = new Actions(navigator);
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("v");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
        Thread.sleep(500);

        navigator.findElement(By.xpath(XPATHCAIXADETEXTOENVIAR)).sendKeys("Faltam " + missingDays +
                " dias para o casamento do aquino, Gabri...seu tempo está se esgotando...", Keys.ENTER);

    }
}

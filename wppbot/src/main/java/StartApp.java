import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);

    }

    @Bean
    public WebDriver webDriver() {
        log.info("Instanciando o Seleniun webdriver");

        System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
        WebDriver navigator = new ChromeDriver();

        navigator.get("https://web.whatsapp.com/");
        return navigator;
    }

}

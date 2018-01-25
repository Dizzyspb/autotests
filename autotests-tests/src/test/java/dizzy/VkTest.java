package dizzy;

/**
 * Created by dizzy on 18.01.18.
 */

import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.runners.Parameterized.*;


@RunWith(value = Parameterized.class)

public class VkTest {

    WebDriver driver;

    String sendText;
    WebElement resultText;

    public VkTest(String sendText) {
        this.sendText = sendText;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"От"}, {"топота"}, {"копыт"}, {"пыль"}, {"по"}, {"полю"}, {"летит"}
        };
        return Arrays.asList(data);
    }

    @Before
    public void driver() throws MalformedURLException {
        //driver = new SafariDriver();
        driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setBinary(new File("/"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("63.0");
        capabilities.setCapability("enableVNC", true);

        //driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(),
        //        capabilities);
    }

    @After
    public void exitDriver() {
        driver.quit();
    }

    @Test
    public void send10MessagesToTheArtem() throws InterruptedException {


        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        final String formattedDate = sdf.format(date);

        String appUrl = "https://www.vk.com";
        driver.get(appUrl);


        //Логинимся в ВК
        WebElement email = driver.findElement(By.id("index_email"));
        email.click();
        email.clear();
        email.sendKeys(cfg.vkLogin());

        WebElement password = driver.findElement(By.id("index_pass"));
        password.click();
        password.clear();
        password.sendKeys(cfg.vkPassword());

        WebElement submitButton = driver.findElement(By.id("index_login_button"));
        submitButton.click();


        //Переходим на "Моя страница"
        System.out.println(driver.getTitle());
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[@id='side_bar']//li[@id='l_pr']")).isDisplayed();
            }
        });

        WebElement myPage = driver.findElement(By.xpath("//div[@id='side_bar']//li[@id='l_pr']"));
        myPage.click();

        //Переходим в "Сообщения"
        WebElement myMessages = driver.findElement(By.xpath("//div[@id='side_bar']//li[@id='l_msg']"));
        myMessages.click();

        System.out.println(driver.getTitle());
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[@id='page_body']//div[@id='wrap3']//div[@class='im-page--header ui_search _im_dialogs_search']//div[@class='im-dialog-select im-dialog-select_classic _im_search_croll']//button[@class='im-dialog-select--btn']")).isDisplayed();
            }
        });

        WebElement startTalking = driver.findElement(By.xpath("//div[@id='page_body']//div[@id='wrap3']//div[@class='im-page--header ui_search _im_dialogs_search']//div[@class='im-dialog-select im-dialog-select_classic _im_search_croll']//button[@class='im-dialog-select--btn']"));
        startTalking.click();


        //Нажимаем на + для добавления пользователя

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//ul[@class='ui_tabs clear_fix ']//button[@class='im-dialog-select--btn']")).isDisplayed();
            }
        });

        WebElement addMember = driver.findElement(By.xpath("//ul[@class='ui_tabs clear_fix ']//button[@class='im-dialog-select--btn']"));

        //Вводим cfg.sendToUser() в поиске

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//input[@id='im_dialogs_creation']")).isDisplayed();
            }
        });

        String findPeople = cfg.sendToUser();
        WebElement searchMember = driver.findElement(By.xpath("//input[@id='im_dialogs_creation']"));
        searchMember.sendKeys(findPeople);


        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//input[@id='im_dialogs_creation']")).isDisplayed();
            }
        });

        // Выбираем Спешнева
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[@class='olist_checkbox']")).isDisplayed();
            }
        });

        WebElement selectMember = driver.findElement(By.xpath("//div[@class='olist_checkbox']"));
        selectMember.click();

        // Кликаем на "Перейти к диалогу"
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[@class='im-create--footer ui_grey_block']//button[text()='Перейти к диалогу']")).isDisplayed();
            }
        });

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//div[@class='im-create--footer ui_grey_block']//button[text()='Перейти к диалогу']"))).click().build().perform();

        // Ищем строку для ввода текста и кнопку Отправить

        WebElement inputText = driver.findElement(By.xpath("//div[@class='im_editable im-chat-input--text _im_text']"));

        //Вводим произвольный текст
        inputText.clear();
        inputText.sendKeys(sendText + " " + formattedDate);

        //Нажимаем кнопку "Отправить
        WebElement sendButton = driver.findElement(By.xpath("//button[@class='im-send-btn im-chat-input--send _im_send im-send-btn_send']"));
        sendButton.click();

        //Дожидаемся отправки сообщения
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='"+ sendText + " " + formattedDate + "']")).isDisplayed();
            }
        });

        System.out.println(driver.getTitle());
        System.out.println(sendText);

        resultText = driver.findElement(By.xpath("//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='"+ sendText + " " + formattedDate + "']"));
        assertThat(resultText.getText(), equalTo(sendText + " " + formattedDate));


        //Deprecated

        //TimeUnit.SECONDS.sleep(5);

        //Закрываем pop-up
        /*System.out.println(driver.getTitle());
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[@class='eltt feature_intro_tt eltt_arrow_size_normal eltt_right eltt_vis']//div[@class='feature_intro_tt_hide']")).isDisplayed();
            }
        });

        WebElement closePopup = driver.findElement(By.xpath("//div[@id='side_bar']//li[@id='l_pr']"));
        closePopup.click();

        //System.out.println(driver.findElements(By.xpath("//div[@class='im-create--footer ui_grey_block']//button[text()='Перейти к диалогу']")).size());
        //WebElement goToDialog = driver.findElement(By.xpath("//div[@class='im-create--footer ui_grey_block']//button[text()='Перейти к диалогу']"));
        //goToDialog.click();

        //Дожидаемся открытия диалога
        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//span[@class='im-page--title-main-in']//a[text()='Александр Спешнев']")).isDisplayed();
            }
        });
        */

    }
}
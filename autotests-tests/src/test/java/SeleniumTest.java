import org.junit.Test;
import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.Assert.assertEquals;

/**
 * Created by dizzy on 17.01.18.
 */
public class SeleniumTest {

    @Test
    @Description("1. Открываем страницу yandex.ru\n" +
            "2. Выполняем поиск по запросу 'Колобашкин'\n" +
            "3. Проверяем заголовок страницы")

    public static void main(String[] args) {
        WebDriver driver = new SafariDriver();

        String appUrl = "https://www.yandex.ru";
        driver.get(appUrl);

        WebElement input__control = driver.findElement(By.xpath("//*[@id=\"text\"]"));
        input__control.click();
        input__control.sendKeys("Колобашкин");
        input__control.submit();

        String title = driver.getTitle();
        String expectedTitle = "Яндекс";

        assertEquals(title, expectedTitle);

   }


}

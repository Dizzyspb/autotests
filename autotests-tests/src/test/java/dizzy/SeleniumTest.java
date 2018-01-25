package dizzy;

import org.junit.Test;
//import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dizzy on 17.01.18.
 */
public class SeleniumTest {

    @Test
//    @Description("1. Открываем страницу yandex.ru\n" +
//            "2. Выполняем поиск по запросу 'Колобашкин'\n" +
//            "3. Проверяем заголовок страницы")


    public void shouldSeeTitle() throws InterruptedException {
        WebDriver driver = new SafariDriver();

        String appUrl = "https://www.yandex.ru";
        driver.get(appUrl);

        WebElement inputControl = driver.findElement(By.xpath("//*[@id=\"text\"]"));
        inputControl.click();
        inputControl.sendKeys("Колобашкин");
        inputControl.submit();


        (new WebDriverWait(driver, 2)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("Колобашкин");
            }
        });
        assertThat(driver.getTitle(), equalTo("Колобашкин — Яндекс: нашлось 132 тыс. результатов"));

        driver.quit();

    }


}

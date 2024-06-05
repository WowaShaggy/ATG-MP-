package tests.selenium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

@ExtendWith(ScreenshotUtil.class)
public class JsExecutorTest extends BaseTest {

    @Test
    public void testScrollToElement() {
        open("http://example.com");

        SelenideElement element = $("#elementToScroll");

        // Скроллинг к элементу
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);

        element.shouldBe(Condition.visible);
    }

    @Test
    public void testElementIsInView() {
        open("http://example.com");

        SelenideElement element = $("#elementToCheck");

        // Проверка, что элемент в видимости
        Boolean isInView = (Boolean) ((JavascriptExecutor) webDriver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                         " +
                        "return false;                            ",
                element);

        assertTrue(isInView, "Element is not in view");
    }

    @Test
    public void testClickUsingJS() {
        open("http://example.com");

        SelenideElement element = $("#elementToClick");

        // Клик по элементу с использованием JS
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);

        element.shouldBe(Condition.attribute("class", "clicked"));
    }
}

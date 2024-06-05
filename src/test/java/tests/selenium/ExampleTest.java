package tests.selenium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class ExampleTest extends BaseTest {

    @Test
    public void testExample() {
        open("http://example.com");

        $("#exampleElement").shouldBe(Condition.visible);

        $("#exampleElement").should(Condition.visible).click();
    }


    @Test
    public void testDragAndDrop() {
        open("http://example.com");

        SelenideElement source = $("#sourceElement");
        SelenideElement target = $("#targetElement");


        new Actions(webDriver)
                .dragAndDrop(source, target)
                .perform();

        target.should(Condition.exist);
    }

    @Test
    public void testResizeElement() {
        open("http://example.com");

        SelenideElement resizable = $("#resizableElement");
        SelenideElement handle = resizable.$(".ui-resizable-handle");

        // Изменение размера элемента
        new Actions(webDriver)
                .clickAndHold(handle)
                .moveByOffset(50, 50)
                .release()
                .perform();

        resizable.shouldHave(Condition.attribute("style", "width: 200px; height: 200px;"));
    }
}

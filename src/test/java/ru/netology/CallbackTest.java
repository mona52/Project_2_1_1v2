package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {

    @Test
    void shouldSubmitRequest() {
        open ("http://localchost:9999");
        ElementsCollection inputs = $$ (".input.control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Николай");
        inputs.find(Condition.attribute("type","tel")).setValue("+79850000000");
        $(".checkbox_box").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}



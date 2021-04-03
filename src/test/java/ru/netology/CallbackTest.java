package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CallbackTest {

    @Test
    void shouldSubmitRequest() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Николай");
        inputs.find(Condition.attribute("type","tel")).setValue("+79850000000");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldStopIfUncorrectNameWithNotRussian() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Ivanov Николай");
        inputs.find(Condition.attribute("type","tel")).setValue("+79850000000");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldStopIfUncorrectNameWithNumbers() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Николай -й");
        inputs.find(Condition.attribute("type","tel")).setValue("+79850000000");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldStopIfUncorrectTelWithoutPlus() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(attribute("type","text")).setValue("Иванов Николай");
        inputs.find(attribute("type","tel")).setValue("89850000000");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldStopIfUncorrectTelWith12Elements() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(attribute("type","text")).setValue("Иванов Николай");
        inputs.find(attribute("type","tel")).setValue("+798500000001");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldStopIfUncorrectTelWith1Element() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(attribute("type","text")).setValue("Иванов Николай");
        inputs.find(attribute("type","tel")).setValue("1");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldStopWithEmptyName() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("");
        inputs.find(Condition.attribute("type","tel")).setValue("+79850000000");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldStopWithEmptyTel() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(attribute("type","text")).setValue("Иванов Николай");
        inputs.find(attribute("type","tel")).setValue("");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldStopWithEmptyCheckbox() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(attribute("type","text")).setValue("Иванов Николай");
        inputs.find(attribute("type","tel")).setValue("+79850000000");
        //$(".checkbox__box").click();
        $(".button").click();
        $(".input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
    @Test
    void shouldStopWithAllEmpty() {
        open ("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(attribute("type","text")).setValue("");
        inputs.find(attribute("type","tel")).setValue("");
        //$(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}




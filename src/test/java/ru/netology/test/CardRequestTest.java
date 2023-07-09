package ru.netology.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardRequestTest {

    @BeforeEach

    public void setup(){
        open("http://localhost:9999");

    }



    @Test

    public void shouldSubmitRequest() {
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test

    public void shouldAlertIfNameFieldEmpty() {
        $("[data-test-id=phone] input").sendKeys("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    public void shouldAlertIfWrongName() {
        $("[data-test-id=name] input").sendKeys("John");
        $("[data-test-id=phone] input").sendKeys("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test

    public void shouldAlertIfPhoneNumberFieldIsEmpty() {
        $("[data-test-id=name] input").sendKeys("Иван Иванов");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    public void shouldAlertIfWrongPhoneNumber() {
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. " +
                "Должно быть 11 цифр, например, +79012345678."));
    }

    @Test

    public void shouldNotSubmitIfNotSignedAgreement() {
        $("[data-test-id=name] input").sendKeys("Иван Иванов");
        $("[data-test-id=phone] input").sendKeys("+79999999999");
        $("button").click();
        $("[data-test-id=agreement].input_invalid").shouldBe(visible);
    }


}


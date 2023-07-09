package ru.netology.test;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardRequestTest {

    @Test

    public void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test

    public void shouldNotSubmitIfWrongName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").sendKeys("John");
        $("[data-test-id=phone] input").sendKeys("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldAlertIfWrongName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").sendKeys("John");
        $("[data-test-id=phone] input").sendKeys("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = $(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test

    public void shouldNotSubmitIfWrongPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").sendKeys("Иван Иванов");
        $("[data-test-id=phone] input").sendKeys("79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test

    public void shouldNotSubmitIfWrongPhoneNumber2() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").sendKeys("Иван Иванов");
        $("[data-test-id=phone] input").sendKeys("+79999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldAlertIfWrongPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79");
        $("[data-test-id=agreement]").click();
        $("button").click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = $(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test

    public void shouldNotSubmitIfNotSignedAgreement() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").sendKeys("Иван Иванов");
        $("[data-test-id=phone] input").sendKeys("+79999999999");
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }


}


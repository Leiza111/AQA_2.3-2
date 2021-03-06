package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.getRegisteredUser;

public class TestMode {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }
    @Test
    void shouldActiveUserValidData() { //Действительные данные Активного Пользователя
        var registeredUser = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[id='root']").shouldBe(visible).shouldBe(Condition.text("Личный кабинет"));
    }

    @Test
    void shouldBlockedUserValidData() { //Действительные данные заблокированного Пользователя
        var registeredUser = getRegisteredUser("blocked");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[id='root']").shouldBe(visible).shouldBe(Condition.text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldActiveUserInvalidLogin() { // неверный логин активного пользователя
        var invalidData = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $("[data-test-id='action-login'] .button__content ").click();
        $("[data-test-id='error-notification'] .notification__title").shouldHave(text("Ошибка"));
        $("[data-test-id='error-notification'] .notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldActiveUserInvalidPassword() { // неверный пароль активного пользователя
        var invalidData = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $("[data-test-id='action-login'] .button__content ").click();
        $("[data-test-id='error-notification'] .notification__title").shouldHave(text("Ошибка"));
        $("[data-test-id='error-notification'] .notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldBlockedUserInvalidPassword() { // неверный пароль заблокированного пользователя
        var invalidData = getRegisteredUser("blocked");
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $("[data-test-id='action-login'] .button__content ").click();
        $("[data-test-id='error-notification'] .notification__title").shouldHave(text("Ошибка"));
        $("[data-test-id='error-notification'] .notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldBlockedUserInvalidLogin() { // неверный логин активного пользователя
        var invalidData = getRegisteredUser("blocked");
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $("[data-test-id='action-login'] .button__content ").click();
        $("[data-test-id='error-notification'] .notification__title").shouldHave(text("Ошибка"));
        $("[data-test-id='error-notification'] .notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

}

package test;

import dataHelper.CardInfo;
import dataHelper.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.PayPage;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.open;
import static dataHelper.DataHelper.*;
public class PayTourTests {
    private final String success = "Успешно";
    private final String error = "Ошибка";
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    // [Тесты для каждого вида оплаты]
    // [Оплата по дебетовой карте]
    // [Функциональные тесты для каждого вида оплаты] ------------------------------------------------------------------
    // [Оплата по кредитной карте] -------------------------------------------------------------------------------------
    @Test
    @DisplayName("Дебетовая карта. Успешная оплата с подтвержденной карты (со значением “APPROVED”)")
    void successfulPayFromApprovedDebitCard() {
        CardInfo cardInfo = DataHelper.getCardInfo(true);
        MainPage mainPage = new MainPage();
        PayPage payPage = mainPage.clickToPay();
        payPage.enterCardData(cardInfo);
        payPage.clickSubmit();
        String notice = payPage.getNoticeText();
        Assertions.assertTrue(notice.contains(success));
    }
    @Test
    @DisplayName("Дебетовая карта. Неудачная оплата с отклоненной карты (со значением “DECLINED”)")
    void failedPayFromApprovedDebitCard() {
        CardInfo cardInfo = DataHelper.getCardInfo(false);
        MainPage mainPage = new MainPage();
        PayPage payPage = mainPage.clickToPay();
        payPage.enterCardData(cardInfo);
        payPage.clickSubmit();
        String notice = payPage.getNoticeText();
        Assertions.assertTrue(notice.contains(error));
        //Баг - отображается уведомление об успехе операции
    }
    @Test
    @DisplayName("Дебетовая карта. Неудачная оплата картой, которой нет в базе")
    void failedPayFromNonexistenceDebitCard() {
        CardInfo cardInfo = new CardInfo(generateNumber(), generateMouth(), generateYear(), generateOwner(), generateCvc());
        MainPage mainPage = new MainPage();
        PayPage payPage = mainPage.clickToPay();
        payPage.enterCardData(cardInfo);
        payPage.clickSubmit();
        String notice = payPage.getNoticeText();
        Assertions.assertTrue(notice.contains(error));
        //Баг - отображается оба уведомления об успехе и неудаче операции
    }
    @Test
    @DisplayName("Дебетовая карта. Неудачная отправка пустой формы для проверки валидации полей")
    void failedSendEmptyFormDebitCard() {
        MainPage mainPage = new MainPage();
        PayPage payPage = mainPage.clickToPay();
        payPage.clickSubmit();

        payPage.getInputsSub().shouldHave(size(5));
    }

    // [Функциональные тесты для каждого вида оплаты] ------------------------------------------------------------------
    // [Оплата по кредитной карте] -------------------------------------------------------------------------------------

    @Test
    @DisplayName("Кредитная карта. Успешная оплата с подтвержденной карты (со значением “APPROVED”)")
    void successfulPayFromApprovedCreditCard() {
        CardInfo cardInfo = DataHelper.getCardInfo(true);

        MainPage mainPage = new MainPage();
        PayPage payPage = mainPage.clickToPayInCredit();
        payPage.enterCardData(cardInfo);
        payPage.clickSubmit();
        String notice = payPage.getNoticeText();

        Assertions.assertTrue(notice.contains(success));
    }

    @Test
    @DisplayName("Кредитная карта. Неудачная оплата с отклоненной карты (со значением “DECLINED”)")
    void failedPayFromApprovedCreditCard() {
        CardInfo cardInfo = DataHelper.getCardInfo(false);

        MainPage mainPage = new MainPage();
        PayPage payPage = mainPage.clickToPayInCredit();
        payPage.enterCardData(cardInfo);
        payPage.clickSubmit();
        String notice = payPage.getNoticeText();

        Assertions.assertTrue(notice.contains(error));
        //Баг - отображается уведомление об успехе операции
    }

    @Test
    @DisplayName("Кредитная карта. Неудачная оплата картой, которой нет в базе")
    void failedPayFromNonexistenceCreditCard() {
        CardInfo cardInfo = new CardInfo(generateNumber(), generateMouth(), generateYear(), generateOwner(), generateCvc());

        MainPage mainPage = new MainPage();
        PayPage payPage = mainPage.clickToPayInCredit();
        payPage.enterCardData(cardInfo);
        payPage.clickSubmit();
        String notice = payPage.getNoticeText();

        Assertions.assertTrue(notice.contains(error));
        //Баг - отображается оба уведомления об успехе и неудаче операции
    }

    @Test
    @DisplayName("Кредитная карта. Неудачная отправка пустой формы для проверки валидации полей")
    void failedSendEmptyFormCreditCard() {

        MainPage mainPage = new MainPage();
        PayPage payPage = mainPage.clickToPayInCredit();
        payPage.clickSubmit();

        payPage.getInputsSub().shouldHave(size(5));
    }

// [Валидация полей для каждого вида оплаты] -----------------------------------------------------------------------
// [Оплата по кредитной карте] -------------------------------------------------------------------------------------


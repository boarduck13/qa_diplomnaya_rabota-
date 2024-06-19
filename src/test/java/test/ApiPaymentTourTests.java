package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import dataHelper.CardInfo;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static dataHelper.ApiHelper.*;
import static dataHelper.DataHelper.*;
import static dataHelper.SqlHelper.*;

public class ApiPaymentTourTests {
    private final String approved = "APPROVED";
    private final String declined = "DECLINED";

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        cleanDB();
    }

    @AfterEach
    public void tearDown() {
        cleanDB();
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Успешная оплата с дебетовой карты (со значением “APPROVED”)")
    public void successfulPayFromApprovedDebitCard() {
        CardInfo card = getCardInfo(true);

        String paymentStatusResponse = debitCard(card);
        String paymentStatusDB = getStatusFromPaymentEntity();

        Assertions.assertEquals(approved, paymentStatusResponse);
        Assertions.assertEquals(approved, paymentStatusDB);
    }

    @Test
    @DisplayName("Успешная оплата с кредитной карты (со значением “APPROVED”)")
    public void successfulPayFromApprovedCreditCard() {
        CardInfo card = getCardInfo(true);

        String paymentStatusResponse = creditCard(card);
        String paymentStatusDB = getStatusFromCreditEntity();

        Assertions.assertEquals(approved, paymentStatusResponse);
        Assertions.assertEquals(approved, paymentStatusDB);
    }

    @Test
    @DisplayName("Неудачная оплата с дебетовой карты (со значением “DECLINED”)")
    public void failedPayFromApprovedDebitCard() {
        CardInfo card = getCardInfo(false);

        String paymentStatusResponse = debitCard(card);
        String paymentStatusDB = getStatusFromPaymentEntity();

        Assertions.assertEquals(declined, paymentStatusResponse);
        Assertions.assertEquals(declined, paymentStatusDB);
    }

    @Test
    @DisplayName("Неудачная оплата с кредитной карты (со значением “DECLINED”)")
    public void failedPayFromApprovedCreditCard() {
        CardInfo card = getCardInfo(false);

        String paymentStatusResponse = creditCard(card);
        String paymentStatusDB = getStatusFromCreditEntity();

        Assertions.assertEquals(declined, paymentStatusResponse);
        Assertions.assertEquals(declined, paymentStatusDB);
    }
}
package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement heading = $("h2");
    private ElementsCollection buttons = $$("button");

    private SelenideElement buttonPay = buttons.get(0);
    private SelenideElement buttonPayInCredit = buttons.get(1);

    private String textPayByCard = "Оплата по карте";
    private String textCreditByCard = "Кредит по данным карты";

    public MainPage() {
        heading.shouldBe(visible);
        buttonPay.shouldBe(visible).shouldHave(text("Купить"));
        buttonPayInCredit.shouldBe(visible).shouldHave(text("Купить в кредит"));
    }

    public PayPage clickToPay() {
        buttonPay.click();
        return new PayPage(textPayByCard);
    }

    public PayPage clickToPayInCredit() {
        buttonPayInCredit.click();
        return new PayPage(textCreditByCard);
    }
}
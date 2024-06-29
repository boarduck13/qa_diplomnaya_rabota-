package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.CardInfo;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayPage {

    private ElementsCollection inputs = $$(".input__inner");
    private ElementsCollection inputsSub = $$(".input__sub");

    private SelenideElement inputNumber = inputs.get(0);
    private SelenideElement inputMouth = inputs.get(1);
    private SelenideElement inputYear = inputs.get(2);
    private SelenideElement inputOwner = inputs.get(3);
    private SelenideElement inputCvc = inputs.get(4);

    private SelenideElement submit = $("form button");

    public PayPage(String text) {
        $(By.xpath("//h3[contains(text(), 'карт')]")).shouldBe(visible);
        inputs.shouldHave(size(5));
        inputs.get(0).$(".input__top").shouldBe(visible).shouldHave(text("Номер карты"));
        submit.shouldBe(visible).shouldHave(text("Продолжить"));
    }

    public void enterCardData(CardInfo cardInfo) {
        inputNumber.$(".input__control").setValue(cardInfo.getNumber());
        inputMouth.$(".input__control").setValue(cardInfo.getMouth());
        inputYear.$(".input__control").setValue(cardInfo.getYear());
        inputOwner.$(".input__control").setValue(cardInfo.getOwner());
        inputCvc.$(".input__control").setValue(cardInfo.getCvc());
    }

    public void clickSubmit() {
        submit.click();
    }

    public void getNoticeText(String msg) {
        $(".notification__title").shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(msg));
    }

    public void getInputsSub(int size) {
        inputsSub.shouldHave(size(size));
    }

    public String getInputValue(int index) {
        return inputs.get(index).$(".input__control").getValue();
    }

    public void getNoticeInputNumber(String msg) {
        inputNumber.$(".input__sub").shouldBe(visible).shouldHave(text(msg));
    }

    public void getNoticeInputMouth(String msg) {
        inputMouth.$(".input__sub").shouldBe(visible).shouldHave(text(msg));
    }

    public void getNoticeInputYear(String msg) {
        inputYear.$(".input__sub").shouldBe(visible).shouldHave(text(msg));
    }

    public void getNoticeInputOwner(String msg) {
        inputOwner.$(".input__sub").shouldBe(visible).shouldHave(text(msg));
    }

    public void getNoticeInputCvc(String msg) {
        inputCvc.$(".input__sub").shouldBe(visible).shouldHave(text(msg));
    }
}
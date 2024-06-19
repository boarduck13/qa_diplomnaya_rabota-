package dataHelper;

public class CardInfo {

    String number;
    String mouth;
    String year;
    String owner;
    String cvc;

    public CardInfo(String number, String mouth, String year, String owner, String cvc) {
        this.number = number;
        this.mouth = mouth;
        this.year = year;
        this.owner = owner;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public String getMouth() {
        return mouth;
    }

    public String getYear() {
        return year;
    }

    public String getOwner() {
        return owner;
    }

    public String getCvc() {
        return cvc;
    }
}
package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=\"dashboard\"]");
    private ElementsCollection refillButton = $$("[data-test-id=action-deposit]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }


    public TransferPage refillFirstCard() {
        refillButton.first().click();
        return new TransferPage();
    }




    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);

    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }



    public int getCardBalance(String id) {
        val text = cards.find(attribute("data-test-id", id)).getText();
        return extractBalance(text);
    }





    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}




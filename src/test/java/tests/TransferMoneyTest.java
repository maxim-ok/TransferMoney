package tests;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferMoneyTest {


    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        // можно заменить на var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);



    }



    @Test
    public void shouldTransferMoneyBetweenOwnCards2() {
        open("http://localhost:9999/");

        Configuration.holdBrowserOpen = true;

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo(); // получаем логин и пароль для авторизации
        var verificationPage = loginPage.validLogin(authInfo); // вводим данные для логина и переходим на страницу верификации
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo); // получаем код для верификации
        var dashboardPage = verificationPage.validVerify(verifyInfo); // вводим код верификации и переходим на страницу с картами

        var firstBalance = dashboardPage.getCardBalance(""); // получаем стартовый баланс по первой карте

//        var firstBalance = dashboardPage.getCardBalance(0); // получаем стартовый баланс по первой карте
        var transferPage = dashboardPage.refillFirstCard(); // переход на страницу пополнения карты
        var transferInfo = DataHelper.getTransferInfo(); // получаем сумму и номер карты для пополнения
        transferPage.transfer(transferInfo); // вводим данные для пополнения и нажимаем "Пополнить"
        var balanceAfterTransfer = firstBalance + (Integer.parseInt(transferInfo.getAmount()));
       // Assertions.assertEquals(firstBalance, balanceAfterTransfer);

    }









}
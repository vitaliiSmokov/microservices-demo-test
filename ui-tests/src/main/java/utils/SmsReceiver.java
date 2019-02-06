package utils;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

/**
 * @author Vitalii Smokov
 */
public class SmsReceiver {

  SelenideElement refreshBtn = $x("//*[contains(text(), 'Click Here To Refresh!')]");
  //    ElementsCollection messages = $$("table.table tbody tr td:nth-child(3)");
  ElementsCollection messages = $$("table.table tbody tr");

  public SmsReceiver open(String countryCode, String number) {
    Selenide
        .open(String.format("https://freephonenum.com/%s/receive-sms/%s", countryCode, number));
    return this;
  }

  public SmsReceiver refresh() {
    refreshBtn.click();
    return this;
  }

  public String getLastMessageTime() {
    return messages.get(0).find("td").text();
  }

  public String getLastMessageNumber() {
    return messages.get(0).find("td:nth-child(2)").text();
  }

  public String getLastMessageText() {
    return messages.get(0).find("td:nth-child(3)").text();
  }
}

package utils;

import com.codeborne.selenide.Selenide;

/**
 * @author Vitalii Smokov 20.02.2019
 */
public class GeolocationUtil {

  public static void setGeolocation(String latitude, String longitude) {
    Selenide.executeJavaScript(String.format("window.navigator.geolocation.getCurrentPosition = "
        + "function(success){"
        + "         var position = {\"coords\" : {"
        + "                                       \"latitude\": \"%s\", "
        + "                                       \"longitude\": \"%s\""
        + "                                     }"
        + "                         }; "
        + "         success(position);}", latitude, longitude));}

}

package dizzy.Adapter;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Created by dizzy on 01.02.18.
 */
public class Realty3Api {

    public static Response canCreateRedirect() {
        return given()
                .headers("Accept", "application/json", "x-vertis-platform", "android/d2", "x-authorization", "Vertis 02470ea1-2f88-49c5-9292-3479c2b7da1d 0bf525f1-5488-4890-af67-959736723b71",
                        "Authorization", "OAuth AQAAAADu08P7AAAFX-OHvz8o_EABmS1tIw6B79Q")
                .contentType("application/json")
                .when()
                .get("https://api.realty.test.vertis.yandex.net/1.0/phone/canCreateRedirect/4003976033?geoId=10945");
    }
}
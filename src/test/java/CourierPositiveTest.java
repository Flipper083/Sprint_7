import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description; // Импорт аннотации Description
import io.restassured.response.ValidatableResponse;

import org.example.Courier;
import org.example.CourierClient;
import org.example.CourierGenerator;
import org.example.Credentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo; // Добавленный импорт

public class CourierPositiveTest {
    private CourierClient courierClient;
    private Courier courier;
    private Integer id; // Изменено на Integer, чтобы можно было использовать null

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourier();
    }

    @After
    public void cleanUp() {
        if (id != null) {
            courierClient.delete(id);
        }
    }

    @Test
    @DisplayName("Check response when courier is created")
    @Description("This test verifies that a courier can be created successfully and the response indicates success.") // Описание теста
    public void courierCanBeCreatedTest() { // Изменено название метода
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id"); // Извлечение ID курьера из ответа

        responseCreate
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Check response when courier is logged in")
    @Description("This test verifies that a courier can log in successfully and the response contains the courier's ID.") // Описание теста
    public void courierCanBeLoginAndCheckResponseTest() { // Изменено название метода
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id"); // Извлечение ID курьера из ответа

        responseLogin
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Check response when courier is created without firstName")
    @Description("This test verifies that a courier can be created without providing a first name and the response indicates success.") // Описание теста
    public void courierCanBeCreatedWithoutFirstNameTest() { // Изменено название метода
        courier = CourierGenerator.getCourierWithoutFirstName();

        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id"); // Извлечение ID курьера из ответа

        responseCreate
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }
}
import io.qameta.allure.junit4.DisplayName;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierPositiveTest {
    private CourierClient courierClient;
    private Courier courier;
    private int id;

    @Before
    public void setUp() {
        // Инициализация объекта CourierClient и генерация случайного курьера
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourier();
    }

    @After
    public void cleanUp() {
        // Удаление созданного курьера после каждого теста
        courierClient.delete(id);
    }

    @Test
    @DisplayName("Check response when courier is created")
    public void courierCanBeCreated() {
        // Создание курьера и вход в систему
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id"); // Извлечение ID курьера из ответа

        // Проверка статус-кода ответа на создание курьера
        int actualStatusCode = responseCreate.extract().statusCode();
        boolean isCourierCreated = responseCreate.extract().path("ok"); // Проверка успешности создания

        // Утверждения для проверки результатов
        assertEquals("Status Code incorrect", actualStatusCode, SC_CREATED); // Проверка кодов состояния
        assertTrue("Expected true", isCourierCreated); // Проверка, что курьер был успешно создан
    }

    @Test
    @DisplayName("Check response when courier is logged in")
    public void courierCanBeLoginAndCheckResponse() {
        // Создание курьера и вход в систему
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id"); // Извлечение ID курьера из ответа

        // Проверка статус-кода ответа на вход в систему
        int actualStatusCode = responseLogin.extract().statusCode();

        // Утверждения для проверки результатов
        assertEquals("Status Code incorrect", actualStatusCode, SC_OK); // Проверка кодов состояния
        assertThat("Expected courier is logged In", id, notNullValue()); // Проверка, что ID курьера не null
    }
}
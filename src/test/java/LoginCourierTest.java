import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import org.example.Courier;
import org.example.CourierClient;
import org.example.CourierGenerator;
import org.example.Credentials;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    // Объект для взаимодействия с API курьеров
    CourierClient courierClient;

    // Объект курьера, который будет использоваться в тестах
    Courier courier;

    // ID курьера, который будет получен после успешного входа
    int courierId;

    // Объект для хранения учетных данных курьера
    Credentials courierCredentials;

    @Before
    public void setUp() {
        // Инициализация клиента курьеров
        courierClient = new CourierClient();

        // Генерация случайного курьера
        courier = CourierGenerator.getRandomCourier();

        // Создание курьера через API
        courierClient.create(courier);

        // Создание учетных данных курьера для тестирования
        courierCredentials = new Credentials(courier.getLogin(), courier.getPassword());
    }

    // Тест на успешный вход курьера
    @Test
    @DisplayName("Successful courier login")
    public void successLoginCourierTest() {
        // Попытка входа с корректными учетными данными
        ValidatableResponse loginResponse = courierClient.login(courierCredentials).statusCode(200);

        // Извлечение ID курьера из ответа
        courierId = loginResponse.extract().path("id");

        // Проверка, что ID курьера не равен null
        loginResponse.assertThat().body("id", notNullValue());

        // Вывод ID курьера в консоль
        System.out.println(courierId);
    }

    // Тест на вход курьера с некорректным логином
    @Test
    @DisplayName("Courier login with invalid login")
    public void failedLoginCourierIncorrectLoginTest() {
        // Попытка входа с корректными учетными данными
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);

        // Извлечение ID курьера (для корректной логики теста не обязательно)
        courierId = loginResponse.extract().path("id");

        // Создание неверных учетных данных для теста
        Credentials incorrectLoginCred = new Credentials(courier.getLogin() + "test", courier.getPassword());

        // Попытка входа с некорректными учетными данными и проверка кода статуса
        ValidatableResponse failedLoginResponse = courierClient.login(incorrectLoginCred).statusCode(404);

        // Проверка сообщения об ошибке
        failedLoginResponse.assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}
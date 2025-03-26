import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description; // Импортируем аннотацию Description
import io.restassured.response.ValidatableResponse;

import org.example.Courier;
import org.example.CourierClient;
import org.example.CourierGenerator;
import org.example.Credentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK; // Импортируем код статуса 200
import static org.apache.http.HttpStatus.SC_NOT_FOUND; // Импортируем код статуса 404
import static org.apache.http.HttpStatus.SC_BAD_REQUEST; // Импортируем код статуса 400
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

    @After
    public void cleanUp() {
        // Удаление созданного курьера после теста
        if (courierId > 0) {
            courierClient.delete(courierId);
        }
    }

    // Тест на успешный вход курьера
    @Test
    @DisplayName("Successful courier login")
    @Description("This test verifies that a courier can log in successfully with valid credentials.")
    public void successLoginCourierTest() {
        // Попытка входа с корректными учетными данными
        ValidatableResponse loginResponse = courierClient.login(courierCredentials).statusCode(SC_OK);

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
    @Description("This test verifies that a courier cannot log in with an invalid login.")
    public void failedLoginCourierIncorrectLoginTest() {
        // Создание неверных учетных данных для теста
        Credentials incorrectLoginCred = new Credentials(courier.getLogin() + "test", courier.getPassword());

        // Попытка входа с некорректными учетными данными и проверка кода статуса
        ValidatableResponse failedLoginResponse = courierClient.login(incorrectLoginCred).statusCode(SC_NOT_FOUND);

        // Проверка сообщения об ошибке
        failedLoginResponse.assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    // Тест на вход курьера с верным логином и неверным паролем
    @Test
    @DisplayName("Courier login with valid login and invalid password")
    @Description("This test verifies that a courier cannot log in with a valid login and an invalid password.")
    public void failedLoginCourierIncorrectPasswordTest() {
        // Создание неверных учетных данных для теста (верный логин, неверный пароль)
        Credentials incorrectPasswordCred = new Credentials(courier.getLogin(), courier.getPassword() + "wrong");

        // Попытка входа с некорректными учетными данными и проверка кода статуса
        ValidatableResponse failedLoginResponse = courierClient.login(incorrectPasswordCred).statusCode(SC_NOT_FOUND);

        // Проверка сообщения об ошибке
        failedLoginResponse.assertThat().body("message", equalTo("Учетная запись не найдена")); // Здесь может быть другое сообщение, в зависимости от API
    }

    // Тест на вход курьера с пустым логином
    @Test
    @DisplayName("Courier login with empty login")
    @Description("This test verifies that a courier cannot log in with an empty login.")
    public void failedLoginCourierEmptyLoginTest() {
        // Создание учетных данных с пустым логином
        Credentials emptyLoginCred = new Credentials("", courier.getPassword());

        // Попытка входа с пустым логином и проверка кода статуса
        ValidatableResponse failedLoginResponse = courierClient.login(emptyLoginCred).statusCode(SC_BAD_REQUEST);

        // Проверка сообщения об ошибке
        failedLoginResponse.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    // Тест на вход курьера с пустым паролем
    @Test
    @DisplayName("Courier login with empty password")
    @Description("This test verifies that a courier cannot log in with an empty password.")
    public void failedLoginCourierEmptyPasswordTest() {
        // Создание учетных данных с пустым паролем
        Credentials emptyPasswordCred = new Credentials(courier.getLogin(), "");

        // Попытка входа с пустым паролем и проверка кода статуса
        ValidatableResponse failedLoginResponse = courierClient.login(emptyPasswordCred).statusCode(SC_BAD_REQUEST);

        // Проверка сообщения об ошибке
        failedLoginResponse.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    // Тест на вход курьера с пустыми логином и паролем
    @Test
    @DisplayName("Courier login with empty login and password")
    @Description("This test verifies that a courier cannot log in with both empty login and password.")
    public void failedLoginCourierEmptyLoginAndPasswordTest() {
        // Создание учетных данных с пустыми логином и паролем
        Credentials emptyCred = new Credentials("", "");

        // Попытка входа с пустыми учетными данными и проверка кода статуса
        ValidatableResponse failedLoginResponse = courierClient.login(emptyCred).statusCode(SC_BAD_REQUEST);

        // Проверка сообщения об ошибке
        failedLoginResponse.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}
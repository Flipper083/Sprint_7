import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description; // Импорт аннотации Description
import io.restassured.response.ValidatableResponse;

import org.example.Courier;
import org.example.CourierClient;
import org.example.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierTest {
    private CourierClient courierClient; // Объект для работы с API курьеров
    private Courier courier; // Курьер, который будет использоваться в тестах
    private int statusCode; // Ожидаемый статус код
    private String message; // Ожидаемое сообщение
    private Integer id; // ID курьера

    // Конструктор для инициализации параметров теста
    public CourierTest(Courier courier, int statusCode, String message) {
        this.courier = courier;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Before
    public void setUp() {
        // Инициализация объекта CourierClient перед каждым тестом
        courierClient = new CourierClient();
    }

    @After
    public void cleanUp() {
        // Удаление созданного курьера после теста, если его ID больше 0
        if (id != null && id > 0) {
            courierClient.delete(id);
        }
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} | Ожидаемый статус: {1} | Ожидаемое сообщение: {2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {CourierGenerator.getWithPasswordOnly(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getWithLoginOnly(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getWithPasswordNull(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getWithPasswordEmpty(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи."},
                {CourierGenerator.getWithIdenticalCourier(), SC_CONFLICT, "Этот логин уже используется. Попробуйте другой."},
                {CourierGenerator.getWithExistingLogin(), SC_CONFLICT, "Этот логин уже используется. Попробуйте другой."}
        };
    }

    @Test
    @DisplayName("Courier cannot be created with invalid data")
    @Description("This test verifies that a courier cannot be created with invalid data and the appropriate error message is returned.") // Описание теста
    public void courierCannotBeCreatedTest() { // Изменено название метода
        // Сначала создаем курьера, если ожидается конфликт
        if (statusCode == SC_CONFLICT) {
            ValidatableResponse responseCreate = courierClient.create(courier);
            id = responseCreate.extract().path("id"); // Сохраняем ID созданного курьера
        }

        // Попытка создать курьера с неправильными данными
        ValidatableResponse responseCreate = courierClient.create(courier);
        int actualStatusCode = responseCreate.extract().statusCode();
        assertEquals(statusCode, actualStatusCode); // Проверка статус-кода
        String actualMessage = responseCreate.extract().path("message");
        assertEquals(message, actualMessage); // Проверка сообщения
    }
}
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;

import org.example.Order;
import org.example.OrderClient;
import org.example.OrderGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderClient orderClient;
    private Order order; // Заказ, который мы будем создавать
    private int statusCode; // Ожидаемый статус-код ответа

    // Конструктор для инициализации параметров теста
    public CreateOrderTest(Order order, int statusCode) {
        this.order = order; // Инициализация объекта заказа
        this.statusCode = statusCode; // Инициализация ожидаемого статус-кода
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, Ожидаемый статус: {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {OrderGenerator.getWithBlackColor(), SC_CREATED}, // Заказ с черным цветом
                {OrderGenerator.getWithGreyColor(), SC_CREATED}, // Заказ с серым цветом
                {OrderGenerator.getWithBlackAndGrayColors(), SC_CREATED}, // Заказ с черным и серым цветами
                {OrderGenerator.getWithoutColor(), SC_CREATED} // Заказ без цвета
        };
    }

    // Метод, который будет выполнен перед каждым тестом
    @Before
    public void setUp() {
        orderClient = new OrderClient(); // Инициализация клиента для создания заказа
    }

    // Тест, который проверяет создание заказа
    @Test
    @DisplayName("Checking if the body of the response contains track")
    @Description("This test verifies that an order can be created and the response contains a valid tracking number.") // Описание теста
    public void orderCanBeCreatedTest() {
        // Создание заказа и получение ответа
        ValidatableResponse responseCreate = orderClient.createOrder(order);

        // Извлечение фактического статус-кода из ответа
        int actualStatusCode = responseCreate.extract().statusCode();

        // Извлечение номера отслеживания из ответа
        int track = responseCreate.extract().path("track");

        // Проверка, что номер отслеживания не является null
        assertThat("Expected track number", track, notNullValue());

        // Проверка, что фактический статус-код совпадает с ожидаемым
        assertEquals("Status Code incorrect", statusCode, actualStatusCode);

        // Отмена созданного заказа после выполнения теста
        orderClient.cancelOrder(track);
    }
}
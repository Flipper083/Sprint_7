import io.qameta.allure.junit4.DisplayName;
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
    private OrderClient orderClient; // Клиент для работы с заказами
    private Order order; // Заказ, который мы будем создавать
    private int statusCode; // Ожидаемый статус-код ответа

    public CreateOrderTest(Order order, int statusCode) {
        this.order = order;
        this.statusCode = statusCode;
    }

    // Параметры для теста, включая различные объекты заказа и ожидаемый статус-код
    @Parameterized.Parameters
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
    public void orderCanBeCreated(){
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
    }
}
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description; // Импортируем аннотацию Description
import io.restassured.response.ValidatableResponse;

import org.example.OrderClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderListTest {
    // Объект для взаимодействия с API заказов
    private OrderClient orderClient;

    // Метод, который выполняется перед каждым тестом
    @Before
    public void setUp() {
        // Инициализация клиента заказов
        orderClient = new OrderClient();
    }

    // Тест на проверку получения списка заказов
    @Test
    @DisplayName("Проверка, что в тело ответа возвращается список заказов.")
    @Description("This test verifies that the response body returns a list of orders.")
    public void getOrderReturnedOrderListTest() {
        // Получение списка заказов через API
        ValidatableResponse responseOrderList = orderClient.returnOrderList();

        // Извлечение списка заказов из ответа
        ArrayList actualList = responseOrderList.extract().path("orders");

        // Определение размера списка заказов
        int ordersSize = actualList.size();

        // Проверка, что размер списка больше 0
        boolean actual = ordersSize > 0;

        // Извлечение кода статуса из ответа
        int actualStatusCode = responseOrderList.extract().statusCode();

        // Проверка, что код статуса равен 200 (OK)
        assertEquals("Status Code incorrect", SC_OK, actualStatusCode);

        // Проверка, что размер списка заказов больше 0
        assertTrue("Expected order list size more than 0", actual);
    }
}
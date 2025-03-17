package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    // Константа для хранения пути к API заказов
    private static final String PATH = "api/v1/orders";

    // Метод для получения списка заказов
    @Step("return List Order")
    public ValidatableResponse returnOrderList() {
        // Создание GET-запроса для получения списка заказов
        return given()
                .spec(getSpec())
                .when()
                .get(PATH) // Выполнение GET-запроса по указанному пути
                .then();
    }

    // Метод для создания нового заказа
    @Step("creating Order")
    public ValidatableResponse createOrder(Order order) {
        // Создание POST-запроса для создания нового заказа
        return given()
                .spec(getSpec()) // Получение спецификации запроса
                .body(order) // Установка тела запроса с объектом заказа
                .when()
                .post(PATH) // Выполнение POST-запроса по указанному пути
                .then();
    }

    // Метод для отмены заказа по номеру отслеживания
    @Step("cancel Order")
    public ValidatableResponse cancelOrder(int track) {
        return given()
                .spec(getSpec())
                .when()
                .delete(PATH + "/" + track)
                .then();
    }
}
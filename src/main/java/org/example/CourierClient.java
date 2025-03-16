package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

// Класс CourierClient отвечает за взаимодействие с API курьеров
public class CourierClient extends Client {
    // Константы для путей API
    private static final String CREATE_COURIER_PATH = "api/v1/courier"; // Путь для создания курьера
    private static final String LOGIN_COURIER_PATH = "api/v1/courier/login"; // Путь для логина курьера
    private static final String DELETE_COURIER_PATH = "api/v1/courier"; // Путь для удаления курьера

    // Метод для создания курьера
    @Step("creating courier")
    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier) // Устанавливает объект курьера в теле запроса
                .when()
                .post(CREATE_COURIER_PATH) // Отправляет POST-запрос на создание курьера
                .then();
    }

    // Метод для логина курьера
    @Step("login courier")
    public ValidatableResponse login(Credentials credentials) {
        return given()
                .spec(getSpec()) // Использует спецификацию, определённую в родительском классе Client
                .body(credentials) // Устанавливает объект с учётными данными в теле запроса
                .when()
                .post(LOGIN_COURIER_PATH) // Отправляет POST-запрос на логин курьера
                .then();
    }

    // Метод для удаления курьера
    @Step("delete courier")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpec()) // Использует спецификацию, определённую в родительском классе Client
                .when()
                .post(DELETE_COURIER_PATH + "id") // Отправляет POST-запрос на удаление курьера по ID (ошибка в коде: должен быть + id)
                .then();
    }
}
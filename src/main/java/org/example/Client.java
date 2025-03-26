package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    // Метод, который создает и возвращает спецификацию запроса
    protected RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON) // Установка типа контента для запросов в JSON
                .setBaseUri(BASE_URL) // Установка базового URI для запросов
                .build(); // Построение спецификации запроса и ее возврат
    }
}
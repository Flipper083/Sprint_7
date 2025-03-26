package org.example;

import com.github.javafaker.Faker; // Импортируем библиотеку JavaFaker

public class CourierGenerator {

    private static final Faker faker = new Faker(); // Создаем экземпляр Faker

    // Метод для генерации случайного курьера с уникальными логином, паролем и именем
    public static Courier getRandomCourier() {
        // Генерируем случайный логин, пароль и имя с помощью Faker
        String courierLogin = faker.name().username();
        String courierPassword = faker.internet().password();
        String courierFirstName = faker.name().firstName();

        // Возвращаем новый объект Courier с сгенерированными данными
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    // Метод возвращает объект Courier с пустым логином и заданным паролем
    public static Courier getWithPasswordOnly() {
        return new Courier("", "1234", ""); // Логин пустой, пароль задан
    }

    // Метод возвращает объект Courier с заданным логином и пустым паролем
    public static Courier getWithLoginOnly() {
        return new Courier("ninja", "", ""); // Логин задан, пароль пустой
    }

    // Метод возвращает объект Courier с заданным логином и именем, но с null паролем
    public static Courier getWithPasswordNull() {
        return new Courier("ninja", null, "saske"); // Логин и имя заданы, пароль null
    }

    // Метод возвращает объект Courier с заданным логином и именем, но с пустым паролем
    public static Courier getWithPasswordEmpty() {
        return new Courier("cateli", " ", "saske"); // Логин задан, пароль пустой (пробел)
    }

    // Метод возвращает объект Courier с одинаковыми логином, паролем и именем
    public static Courier getWithIdenticalCourier() {
        return new Courier("ninja", "546", "saske"); // Логин и имя заданы, пароль задан
    }

    // Метод возвращает объект Courier с уже существующим логином
    public static Courier getWithExistingLogin() {
        return new Courier("ninja", "123", "sasha"); // Логин "ninja" уже существует
    }
    public static Courier getCourierWithoutFirstName() {
        return new Courier("ninja", "password123", null); // Логин и пароль заданы, имя - null
    }
}
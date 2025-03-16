package org.example;

import org.apache.commons.lang3.RandomStringUtils; // Импортируем библиотеку для генерации случайных строк

public class CourierGenerator {

    // Метод для генерации случайного курьера с уникальными логином, паролем и именем
    public static Courier getRandomCourier() {
        // Генерируем случайный логин из 7 букв
        final String courierLogin = RandomStringUtils.randomAlphabetic(7);
        // Генерируем случайный пароль из 7 букв
        final String courierPassword = RandomStringUtils.randomAlphabetic(7);
        // Генерируем случайное имя из 7 букв
        final String courierFirstName = RandomStringUtils.randomAlphabetic(7);
        // Возвращаем новый объект Courier с сгенерированными данными
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    // Метод возвращает объект Courier с пустым логином и заданным паролем
    public static Object getWithPasswordOnly() {
        return new Courier("", "1234", ""); // Логин пустой, пароль задан
    }

    // Метод возвращает объект Courier с заданным логином и пустым паролем
    public static Object getWithLoginOnly() {
        return new Courier("ninja", "", ""); // Логин задан, пароль пустой
    }

    // Метод возвращает объект Courier с заданным логином и именем, но с null паролем
    public static Object getWithPasswordNull() {
        return new Courier("ninja", null, "saske"); // Логин и имя заданы, пароль null
    }

    // Метод возвращает объект Courier с заданным логином и именем, но с пустым паролем
    public static Object getWithPasswordEmpty() {
        return new Courier("cateli", " ", "saske"); // Логин задан, пароль пустой (пробел)
    }

    // Метод возвращает объект Courier с одинаковыми логином, паролем и именем
    public static Object getWithIdenticalCourier() {
        return new Courier("ninja", "546", "saske"); // Логин и имя заданы, пароль задан
    }

    // Метод возвращает объект Courier с уже существующим логином
    public static Object getWithExistingLogin() {
        return new Courier("ninja", "123", "sasha"); // Логин "ninja" уже существует
    }
}
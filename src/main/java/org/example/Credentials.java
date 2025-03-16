package org.example;

// Класс Credentials предназначен для хранения учетных данных (логин и пароль)
public class Credentials {
    // Приватные поля для хранения логина и пароля
    private String login;
    private String password;

    // Конструктор для инициализации полей логина и пароля
    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Статический метод, который создает объект Credentials на основе объекта Courier
    public static Credentials from(Courier courier){
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    // Метод для получения логина
    public String getLogin() {
        return login;
    }

    // Метод для установки логина
    public void setLogin(String login) {
        this.login = login;
    }

    // Метод для получения пароля
    public String getPassword() {
        return password;
    }

    // Метод для установки пароля
    public void setPassword(String password) {
        this.password = password;
    }
}
package org.example;

public class Courier {
    // Поля класса для хранения информации о курьере
    private String login;      // Логин курьера
    private String password;   // Пароль курьера
    private String firstName;  // Имя курьера

    public Courier() {
    }

    // Конструктор с параметрами для инициализации атрибутов курьера
    public Courier(String login, String password, String firstName) {
        this.login = login;          // Инициализация логина
        this.password = password;    // Инициализация пароля
        this.firstName = firstName;  // Инициализация имени
    }

    // Метод для получения логина курьера
    public String getLogin() {
        return login;
    }

    // Метод для установки нового логина курьера
    public void setLogin(String login) {
        this.login = login;
    }

    // Метод для получения пароля курьера
    public String getPassword() {
        return password;
    }

    // Метод для установки нового пароля курьера
    public void setPassword(String password) {
        this.password = password;
    }

    // Метод для получения имени курьера
    public String getFirstName() {
        return firstName;
    }

    // Метод для установки нового имени курьера
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
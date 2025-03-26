package org.example;

import java.util.List;

public class Order {
    // Поля для хранения информации о заказе
    private String firstName;       // Имя заказчика
    private String lastName;        // Фамилия заказчика
    private String address;         // Адрес доставки
    private String metroStation;    // Станция метро рядом с адресом
    private String phone;           // Номер телефона заказчика
    private int rentTime;           // Время аренды в часах
    private String deliveryDate;    // Дата доставки
    private String comment;         // Комментарий к заказу
    private List<String> color;     // Список выбранных цветов

    // Конструктор для создания объекта Order с необходимыми параметрами
    public Order(String firstName, String lastName, String address, String metroStation, String phone,
                 int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;  // Установка имени
        this.lastName = lastName;    // Установка фамилии
        this.address = address;      // Установка адреса
        this.metroStation = metroStation; // Установка станции метро
        this.phone = phone;          // Установка номера телефона
        this.rentTime = rentTime;    // Установка времени аренды
        this.deliveryDate = deliveryDate; // Установка даты доставки
        this.comment = comment;      // Установка комментария
        this.color = color;          // Установка выбранных цветов
    }

    // Геттеры и сеттеры для доступа и изменения полей класса

    public String getFirstName() {
        return firstName; // Возвращает имя заказчика
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName; // Устанавливает новое имя заказчика
    }

    public String getLastName() {
        return lastName; // Возвращает фамилию заказчика
    }

    public void setLastName(String lastName) {
        this.lastName = lastName; // Устанавливает новую фамилию заказчика
    }

    public String getAddress() {
        return address; // Возвращает адрес доставки
    }

    public void setAddress(String address) {
        this.address = address; // Устанавливает новый адрес доставки
    }

    public String getMetroStation() {
        return metroStation; // Возвращает станцию метро
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation; // Устанавливает новую станцию метро
    }

    public String getPhone() {
        return phone; // Возвращает номер телефона заказчика
    }

    public void setPhone(String phone) {
        this.phone = phone; // Устанавливает новый номер телефона
    }

    public int getRentTime() {
        return rentTime; // Возвращает время аренды
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime; // Устанавливает новое время аренды
    }

    public String getDeliveryDate() {
        return deliveryDate; // Возвращает дату доставки
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate; // Устанавливает новую дату доставки
    }

    public String getComment() {
        return comment; // Возвращает комментарий к заказу
    }

    public void setComment(String comment) {
        this.comment = comment; // Устанавливает новый комментарий к заказу
    }

    public List<String> getColor() {
        return color; // Возвращает список выбранных цветов
    }

    public void setColor(List<String> color) {
        this.color = color; // Устанавливает новый список выбранных цветов
    }
}
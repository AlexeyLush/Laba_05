package models;

import java.util.Objects;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long weight; //Значение поля должно быть больше 0
    private String passportID; //Поле не может быть null

    // Конструктор
    public Person(String name, long weight, String passportID){
        this.name = name;
        this.weight = weight;
        this.passportID = passportID;
    }

    // Геттеры
    public String getName(){
        return name;
    }
    public long getWeight(){
        return weight;
    }
    public String getPassportID(){
        return passportID;
    }

    // Сеттеры
    public void setName(String name){
        this.name = name;
    }
    public void setWeight(long weight){
        this.weight = weight;
    }
    public void setPassportID(String passportID){
        this.passportID = passportID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return weight == person.weight && Objects.equals(name, person.name) && Objects.equals(passportID, person.passportID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, passportID);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                '}';
    }
}

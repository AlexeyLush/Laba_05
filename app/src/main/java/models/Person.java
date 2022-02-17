package models;

import java.util.Objects;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long weight; //Значение поля должно быть больше 0
    private String passportID; //Поле не может быть null

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
    public boolean setName(String name){
        if (name == null || name.isEmpty() || name.split(" ").length == 0 || name.split("\t").length == 0){
            return false;
        }
        this.name = name;
        return true;
    }
    public boolean setWeight(long weight){
        if (weight <= 0){
            return false;
        }
        this.weight = weight;
        return true;
    }
    public boolean setPassportID(String passportID){
        if (passportID == null || passportID.isEmpty() || passportID.split(" ").length == 0
                || passportID.split("\t").length == 0){
            return false;
        }
        this.passportID = passportID;
        return true;
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

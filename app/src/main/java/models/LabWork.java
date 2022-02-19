package models;

import models.service.GenerationID;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

public class LabWork{

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
    private String description; //Строка не может быть пустой, Поле не может быть null
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле может быть null

    public LabWork(){
        setId(GenerationID.newId());
    }

    public boolean setId(int id){
        if (id <= 0){
            return false;
        }
        this.id = id;
        return true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean setName(String name) {
        if (name == null || name.isEmpty() || name.split(" ").length == 0 || name.split("\t").length == 0){
            return false;
        }
        this.name = name;
        return true;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Float getMinimalPoint() {
        return minimalPoint;
    }

    public boolean setMinimalPoint(Float minimalPoint) {
        if (minimalPoint <= 0){
            return false;
        }
        this.minimalPoint = minimalPoint;
        return true;
    }

    public String getDescription() {
        return description;
    }

    public boolean setDescription(String description) {
        if (description == null || description.isEmpty() || description.split(" ").length == 0
                || description.split("\t").length == 0){
            return false;
        }
        this.description = description;
        return true;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}

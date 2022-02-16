package models;

import models.service.GenerationID;

import java.time.ZonedDateTime;
import java.util.Map;

public class LabWork{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
    private String description; //Строка не может быть пустой, Поле не может быть null
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле может быть null

    public LabWork() {
        setId();
    }

    private void setId(){
        this.id = GenerationID.newId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean setName(String name) {
        if (name == null || name.isEmpty()){
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

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Float getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(Float minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

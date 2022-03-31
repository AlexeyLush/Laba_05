package models;

/**
 * Класс координат
 */

public class Coordinates {
    private Long x; //Максимальное значение поля: 713, Поле не может быть null
    private Integer y;
    private final Long maxCoordinateX = 713L;


    // Геттеры
    public Long getX(){
        return x;
    }
    public Integer getY(){
        return y;
    }
    public Long getMaxCoordinateX(){
        return maxCoordinateX;
    }
    // Сеттеры
    public void setX(Long x){
        this.x = x;
    }
    public void setY(Integer y){
        this.y = y;
    }
    // toString
    public String toString(){
        return "Coordinates: {" + "x = " + x + ", y = " + y + "}";
    }
}

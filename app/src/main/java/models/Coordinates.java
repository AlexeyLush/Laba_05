package models;

public class Coordinates {
    private Long x; //Максимальное значение поля: 713, Поле не может быть null
    private int y;
    private Long maxCoordinateX = 713L;

    // Геттеры
    public Long getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Long getMaxCoordinateX(){
        return this.maxCoordinateX;
    }
    // Сеттеры
    public boolean setX(Long x){
        if (x > maxCoordinateX){
            return false;
        }
        this.x = x;
        return true;
    }
    public void setY(int y){
        this.y = y;
    }
    // toString
    public String toString(){
        return "Coordinates: {" + "x = " + x + ", y = " + y + "}";
    }
}

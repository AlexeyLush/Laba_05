package models;

public class Coordinates {
    private Long x; //Максимальное значение поля: 713, Поле не может быть null
    private int y;


    public Coordinates(long x, int y){
        this.x = x;
        this.y = y;
    }

    // Геттеры
    public long getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    // Сеттеры
    public void setX(long x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    // toString
    public String toString(){
        return "Coordinates: {" + "x = " + x + ", y = " + y + "}";
    }
}

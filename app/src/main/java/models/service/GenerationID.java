package models.service;

public class GenerationID {
    private static int id = 1;
    public static int newId(){
        return id++;
    }
    public static void reset(){
        id = 1;
    }
}

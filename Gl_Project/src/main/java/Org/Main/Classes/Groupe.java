package Org.Main.Classes;

public class Groupe {
    private int Id;

    public Groupe(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    private String name;

    public Groupe(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

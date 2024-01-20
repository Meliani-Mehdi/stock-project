package Org.Main.Classes;

public class Deposit {
    private int id;

    public int getId() {
        return id;
    }

    public Deposit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

    public Deposit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

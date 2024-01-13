package Org.Main.Classes;

public class Personne {
    private int id;
    private String name;
    private String adresse;
    private String Phone_Num;
    private Double Solde;
    private Double rest;

    public Personne(int id, String name, String adresse, String phone_Num, Double solde, Double rest) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        Phone_Num = phone_Num;
        Solde = solde;
        this.rest = rest;
    }

    public Personne(String name, String adresse, String phone_Num, Double solde, Double rest) {
        this.name = name;
        this.adresse = adresse;
        Phone_Num = phone_Num;
        Solde = solde;
        this.rest=rest;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPhone_Num(String phone_Num) {
        Phone_Num = phone_Num;
    }

    public void setSolde(Double solde) {
        Solde = solde;
    }

    public void setRest(Double rest) {
        this.rest = rest;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getRest() {
        return rest;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPhone_Num() {
        return Phone_Num;
    }

    public Double getSolde() {
        return Solde;
    }
}

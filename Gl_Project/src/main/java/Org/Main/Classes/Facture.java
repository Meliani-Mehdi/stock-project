package Org.Main.Classes;

import java.util.Date;

public class Facture {
    private final int id;
    private final java.sql.Date date;
    private java.sql.Date UpdateDate;

    private final Double solde;
    private  Double Rest;
    private  Double Payed;

    public Facture(int id, java.sql.Date date, java.sql.Date updateDate, Double solde, Double payed) {
        this.id = id;
        this.date = date;
        UpdateDate = updateDate;
        this.solde = solde;
        Payed = payed;
    }

    public Facture(int id, java.sql.Date date, Double solde, Double rest, Double payed) {
        this.id = id;
        this.date = date;
        this.solde = solde;
        Rest = rest;
        Payed = payed;
    }

    public Facture(int id, java.sql.Date date, Double solde) {
        this.id = id;
        this.date = date;
        this.solde = solde;
    }

    public Facture(int id, java.sql.Date date, Double solde, Double rest) {
        this.id = id;
        this.date = date;
        this.solde = solde;
        Rest = rest;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Double getSolde() {
        return solde;
    }
    public Double getRest() {
        return Rest;
    }
    public Double getPayed() {
        return Payed;
    }

    public java.sql.Date getUpdateDate() {
        return UpdateDate;
    }
}

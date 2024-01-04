package Org.Main.Classes;

import java.sql.Date;

public class Bon_de_versement {
    private int Id;
    private String Seller;
    private int Id_Owner;
    private Date date;
    private double Old_Reste;
    private double Reste;
    private double payment;

    public Bon_de_versement(int id, String Seller, int Id_Owner, Date date, double old_Reste, double reste, double payment) {
        Id = id;
        this.Seller = Seller;
        this.Id_Owner = Id_Owner;
        this.date = date;
        Old_Reste = old_Reste;
        Reste = reste;
        this.payment = payment;
    }

    public int getId() {
        return Id;
    }

    public String getSeller() {
        return Seller;
    }

    public int getId_Owner() {
        return Id_Owner;
    }

    public Date getDate() {
        return date;
    }

    public double getOld_Reste() {
        return Old_Reste;
    }

    public double getReste() {
        return Reste;
    }

    public double getPayment() {
        return payment;
    }
}

package Org.Main.Classes;

public class Facture_Product {
    private int num;
    private int id_Product;
    private String reference;
    private String Bar_Code;
    private String Name;
    private int QTE;
    private Double Unit_Price;
    private Double TVA;
    private Double Price_With_Tva;
    private Double Total_Price;
    private Double Total_Price_With_Tva;
    private String Deposit;

    public Facture_Product(int num,int id_Product, String reference, String bar_Code, String name, int QTE, Double unit_Price, Double TVA, Double price_With_Tva, Double total_Price, Double total_Price_With_Tva, String deposit) {
        this.num = num;
        this.id_Product = id_Product;
        this.reference = reference;
        Bar_Code = bar_Code;
        Name = name;
        this.QTE = QTE;
        Unit_Price = unit_Price;
        this.TVA = TVA;
        Price_With_Tva = price_With_Tva;
        Total_Price = total_Price;
        Total_Price_With_Tva = total_Price_With_Tva;
        Deposit = deposit;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setQTE(int QTE) {
        this.QTE = QTE;
    }

    public void setUnit_Price(Double unit_Price) {
        Unit_Price = unit_Price;
    }

    public void setTVA(Double TVA) {
        this.TVA = TVA;
    }

    public int getId_Product() {
        return id_Product;
    }

    public String getReference() {
        return reference;
    }

    public String getBar_Code() {
        return Bar_Code;
    }

    public String getName() {
        return Name;
    }

    public int getQTE() {
        return QTE;
    }

    public Double getUnit_Price() {
        return Unit_Price;
    }

    public Double getTVA() {
        return TVA;
    }

    public Double getPrice_With_Tva() {
        return Price_With_Tva;
    }

    public Double getTotal_Price() {
        return Total_Price;
    }

    public Double getTotal_Price_With_Tva() {
        return Total_Price_With_Tva;
    }

    public String getDeposit() {
        return Deposit;
    }

    public void setPrice_With_Tva() {
        Price_With_Tva = getTVA()+getUnit_Price();
    }

    public void setTotal_Price() {
        Total_Price = getQTE()*getUnit_Price();
    }

    public void setTotal_Price_With_Tva() {
        Total_Price_With_Tva = getQTE()*(getTVA()+getUnit_Price());
    }
}

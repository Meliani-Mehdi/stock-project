package Org.Main.Classes;

public class Product {
    private Integer Id;
    private String  Codebar;
    private String  Reference;
    private String  name;
    private Double Buying_Price;
    private Double Selling_Price;
    private Integer Stock;
    private String depots;
    private String picture;
    private String Groupe;
    public Product( Integer id,String codebar,String Reference,String name, Double buying_Price, Double selling_Price, Integer stock,String depots,String picture,String Groupe) {
        this.Id=id;
        this.Codebar=codebar;
        this.Reference=Reference;
        this.name = name;
        this.Buying_Price = buying_Price;
        this.Selling_Price = selling_Price;
        this.Stock = stock;
        this.depots=depots;
        this.picture=picture;
        this.Groupe=Groupe;
    }

    public Product() {

    }

    public Integer getId() {
        return Id;
    }

    public Product(Integer id, String codebar, String reference, String name,Double buying_Price, Double selling_Price, Integer stock) {
        Id = id;
        this.Codebar = codebar;
        Reference = reference;
        this.name = name;
        Buying_Price=buying_Price;
        Selling_Price = selling_Price;
        Stock = stock;
    }

    public Product(Integer id, String reference, String name, Double buying_Price, Double selling_Price, Integer stock) {
        Id = id;
        Reference = reference;
        this.name = name;
        Buying_Price = buying_Price;
        Selling_Price = selling_Price;
        Stock = stock;
    }

    public Product(Integer id, String codebar, String name, Double selling_Price) {
        Id = id;
        Codebar = codebar;
        this.name = name;
        Selling_Price = selling_Price;
    }

    public Product(int id, String barCode, String name) {
        Id = id;
        Codebar = barCode;
        this.name = name;
    }

    public Product(String codebar, String name) {
        Codebar = codebar;
        this.name = name;
    }
    public String getGroupe() {
        return Groupe;
    }

    public String getReference() {
        return Reference;
    }

    public String getCodebar() {
        return Codebar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBuying_Price() {
        return Buying_Price;
    }

    public Double getSelling_Price() {
        return Selling_Price;
    }

    public Integer getStock() {
        return Stock;
    }

    public String getDepots() {
        return depots;
    }

    public String getPicture() {
        return picture;
    }

}

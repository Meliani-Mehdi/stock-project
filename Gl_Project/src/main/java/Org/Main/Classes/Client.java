package Org.Main.Classes;

public class Client extends Personne{
    public Double paid;
    public Client(int id, String name, String adresse, String phone_Num, Double solde, Double rest,Double paid) {
        super(id, name, adresse, phone_Num, solde, rest);
        this.paid = paid;
    }
    public Double getpaid(){
        return this.getSolde()-this.getRest();
    }

}

package Org.Main.Classes;

public class Client extends Personne{

    public Client(String name, String adresse, String phone_Num, Double solde,Double rest) {
        super(name, adresse, phone_Num, solde,rest);
    }
    public Double getpaid(){
        return this.getSolde()-this.getRest();
    }

}

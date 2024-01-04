package Org.Main.Classes;

import java.sql.Date;

public class Facture_Vente extends Facture{

    private int idClient;
    private String ClientName;
    private Double Client_Rest;
    private String Username;

    public Facture_Vente(int id, Date date, Date updateDate, Double solde, Double payed, String clientName, Double client_Rest, String username) {
        super(id, date, updateDate, solde, payed);
        ClientName = clientName;
        Client_Rest = client_Rest;
        Username = username;
    }

    public Facture_Vente(int id, Date date, Double solde, Double Rest, Double Payed, int idClient) {
        super(id, date, solde, Rest, Payed);
        this.idClient = idClient;

    }

    public Facture_Vente(int id, Date date, Double solde, int idClient) {
        super(id, date, solde);
        this.idClient=idClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getClientName() {
        return ClientName;
    }

    public Double getClient_Rest() {
        return Client_Rest;
    }

    public String getUsername() {
        return Username;
    }
}

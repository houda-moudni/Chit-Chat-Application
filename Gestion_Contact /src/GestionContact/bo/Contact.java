package GestionContact.bo;

public class Contact {
    private int ID;
    private String nom;
    private String prenom;
    private String telephone1;
    private String telephone2;
    private String adresse;
    private String emailPer;
    private String emailPro;
    private String genre;


    // Constructeur

    public Contact() {
    }

    public Contact(String nom, String prenom, String telephone1, String telephone2, String adresse, String emailPer, String emailPro, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
        this.adresse = adresse;
        this.emailPer = emailPer;
        this.emailPro = emailPro;
        this.genre = genre;
    }

    public Contact(int pId , String pNom, String pPrenom, String pTelephone1, String pTelephone2,
                   String  pAdresse, String pEmailPer, String pEmailPro, String pGenre) {
        ID = pId;
        nom = pNom;
        prenom = pPrenom;
        telephone1 = pTelephone1;
        telephone2 = pTelephone2;
        adresse = pAdresse;
        emailPer = pEmailPer ;
        emailPro = pEmailPro;
        genre = pGenre;

    }


    // Getters
    public int getID() {
        return ID;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmailPer() {
        return emailPer;
    }

    public String getEmailPro() {
        return emailPro;
    }

    public String getGenre() {
        return genre;
    }

    // ToString

    public String toString() {
        return "Contact [" +
                "ID=" + ID +
                ", nom='" + nom + " " +
                ", prenom='" + prenom + " " +
                ", telephone1='" + telephone1 + " " +
                ", telephone2='" + telephone2 + " " +
                ", adresse='" + adresse + " " +
                ", emailPer='" + emailPer + " " +
                ", emailPro='" + emailPro + " " +
                ", genre=" + genre +
                "]\n";
    }
}

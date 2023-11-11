package GestionContact.bo;

public class Groupe {

    private long idGroupe;
    private String nomGroupe;


    public Groupe(long idGroupe, String nomGroupe, Contact contact) {
        this.idGroupe = idGroupe;
        this.nomGroupe = nomGroupe;
    }

    public Groupe(String nomGroupe, Contact contact) {
        this.nomGroupe = nomGroupe;
//        this.contact = contact;
    }

    public long getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(long idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }



    @Override
    public String toString() {
        return "Groupe{" +
                "idGroupe=" + idGroupe +
                ", nomGroupe='" + nomGroupe + '\'' +
                '}';
    }
}

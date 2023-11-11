package GestionContact.bo;

public class GroupeContact {
    private int id;
    private Groupe groupe;
    private Contact contact;

    public GroupeContact(int id, Groupe groupe, Contact contact) {
        this.id = id;
        this.groupe = groupe;
        this.contact = contact;
    }

    public GroupeContact(Groupe groupe, Contact contact) {
        this.groupe = groupe;
        this.contact = contact;
    }


    public GroupeContact() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "GroupeContact{" +
                "id=" + id +
                ", groupe=" + groupe +
                ", contact=" + contact +
                '}';
    }
}

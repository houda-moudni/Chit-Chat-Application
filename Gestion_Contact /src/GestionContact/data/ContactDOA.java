package GestionContact.data;


import GestionContact.bo.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDOA {


    // Creer un nouveau contact
    public void create(Contact pContact) throws SQLException, Exception{

        Connection c = DBConnexion.getInstance();

        String sqlInsert = " INSERT INTO Contact(nom,prenom,telephone1,telephone2," +
                "adresse,emailper,emailpro,genre) values (?,?,?,?,?,?,?,?) ";

        PreparedStatement stm = c.prepareStatement(sqlInsert);

        stm.setString(1,pContact.getNom());
        stm.setString(2,pContact.getPrenom());
        stm.setString(3,pContact.getTelephone1());
        stm.setString(4,pContact.getTelephone2());
        stm.setString(5,pContact.getAdresse());
        stm.setString(6,pContact.getEmailPer());
        stm.setString(7,pContact.getEmailPro());
        stm.setString(8,pContact.getGenre());

        stm.executeUpdate();


    }


    // Afficher la liste des contacts par ordre alphabetique
    public List<Contact> AllContact () throws SQLException, DataBaseException{

        Connection c = DBConnexion.getInstance();
        String sqlSelect = " Select * from Contact Order by nom ";
        PreparedStatement stm = c.prepareStatement(sqlSelect);
        ResultSet rs = stm.executeQuery();
        List<Contact> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultToContact(rs));
        }
        rs.close();
        return list;
    }


    // Supprimer un contact

    public void delete(int pId)throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement("DELETE FROM Contact WHERE idContact = ?");
        stm.setInt(1,pId);
        stm.executeUpdate();
    }


    // Modifier un contact

    public void updateName(String pNom, int pId) throws  Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement("UPDATE Contact SET nom = ? Where idContact =?");
        stm.setString(1,pNom);
        stm.setInt(2,pId);
        stm.executeUpdate();
    }

    public void updatePrenom(String pPrenom , int pId) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement("UPDATE Contact SET prenom = ? Where idContact=?");
        stm.setString(1,pPrenom);
        stm.setInt(2,pId);
        stm.executeUpdate();
    }


    public void updateTelephone1(String pTele , int pId) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement("UPDATE Contact SET telephone1 = ? Where idContact=?");
        stm.setString(1,pTele);
        stm.setInt(2,pId);
        stm.executeUpdate();
    }


    public void updateTelephone2(String pTele , int pId) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement("UPDATE Contact SET telephone2 = ? Where idContact=?");
        stm.setString(1,pTele);
        stm.setInt(2,pId);
        stm.executeUpdate();
    }

    public void updateAdresse(String pAdresse, int pId) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement("UPDATE Contact SET adresse = ? where idContact =?");
        stm.setString(1,pAdresse);
        stm.setInt(2,pId);
        stm.executeUpdate();
    }

    public void updateEmail1(String pEmail, int pId) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement("UPDATE Contact SET emailper = ? where idContact =?");
        stm.setString(1,pEmail);
        stm.setInt(2,pId);
        stm.executeUpdate();
    }
    public void updateEmail2(String pEmail, int pId) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement("UPDATE Contact SET emailpro = ? where idContact =?");
        stm.setString(1,pEmail);
        stm.setInt(2,pId);
        stm.executeUpdate();
    }


    // Rechercher un contact par nom

    public List<Contact> findContact(String pNom) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement( "Select * from Contact where nom = ?");
        stm.setString(1,pNom);
        ResultSet rs = stm.executeQuery();
        List<Contact> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultToContact(rs));
        }
        rs.close();
        return list;
    }


    // Rechercher un contact par numero (PRO ou PER)

    public List<Contact> findContactPhonePro(String pTelephone) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement( "Select * from Contact where telephone1 = ? ");
        stm.setString(1,pTelephone);
        ResultSet rs = stm.executeQuery();
        List<Contact> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultToContact(rs));
        }
        rs.close();
        return list;
    }

    public List<Contact> findContactPhonePer(String pTelephone) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement( "Select * from Contact where telephone2 = ? ");
        stm.setString(1,pTelephone);
        ResultSet rs = stm.executeQuery();
        List<Contact> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultToContact(rs));
        }
        rs.close();
        return list;
    }

    // recherche Phonetique
    public List<Contact> findContactSoundex(String pNom) throws Exception{
        Connection c = DBConnexion.getInstance();
        PreparedStatement stm = c.prepareStatement( "SELECT * FROM Contact WHERE SOUNDEX(nom) = SOUNDEX(?)");
        stm.setString(1,pNom);
        ResultSet rs = stm.executeQuery();
        List<Contact> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultToContact(rs));
        }
        rs.close();
        return list;
    }



    private Contact resultToContact(ResultSet rs) throws SQLException {
        return new Contact(rs.getInt("idContact"), rs.getString("nom"),
                rs.getString("prenom"),rs.getString("telephone1"),
                rs.getString("telephone2"),rs.getString("adresse"),
                rs.getString("emailper"),rs.getString("emailpro"),
                rs.getString("genre"));
    }


}








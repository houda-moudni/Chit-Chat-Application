package GestionContact.data;

import GestionContact.bo.Contact;
import GestionContact.bo.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupeDAO {

    // Creation de groupe
    public void createGrp(String nomGroupe) throws SQLException, DataBaseException {
        Connection c = DBConnexion.getInstance();
        String sqlCreate = "insert into grp(nomGroupe) values (?)";

        PreparedStatement stm = c.prepareStatement(sqlCreate);
        stm.setString(1,nomGroupe);

        stm.executeUpdate();
    }

    public void AddContactGroup(String pNomGroupe , int pIdContact) throws SQLException, DataBaseException {

        Connection c = DBConnexion.getInstance();
        String sqlIdGrp = "Select idGroupe from grp where nomGroupe = ?";
        PreparedStatement stmt = c.prepareStatement(sqlIdGrp);
        stmt.setString(1,pNomGroupe);
        ResultSet rs = stmt.executeQuery();
        int pIdGroupe = 0;
        while(rs.next()){
            pIdGroupe = rs.getInt("idGroupe");
            System.out.println(pIdGroupe);
        }

        String sqlInsert = " Insert GroupeContact(idGroupe,idContact) Values (?,?)";

        PreparedStatement stm = c.prepareStatement(sqlInsert);

        stm.setInt(1,pIdGroupe);
        stm.setInt(2,pIdContact);

        stm.executeUpdate();

    }

    public List<Object> AllGroups () throws SQLException, DataBaseException{

        Connection c = DBConnexion.getInstance();
        String sqlSelect = " SELECT g.*, c.*\n" +
                "FROM Grp g\n" +
                "LEFT JOIN groupecontact gc ON g.idGroupe = gc.idGroupe\n" +
                "LEFT JOIN Contact c ON c.idContact = gc.idContact\n" +
                "ORDER BY g.nomGroupe;\n ;" ;
        PreparedStatement stm = c.prepareStatement(sqlSelect);
        ResultSet rs = stm.executeQuery();
        List<Object> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("nomgroupe"));
            list.add(resultToContact(rs));

        }
        rs.close();
        return list;
    }


    public void DeleteGroup(String pNomGroupe) throws DataBaseException, SQLException {
        Connection c = DBConnexion.getInstance();
        String sqlIdGrp = "Select idGroupe from grp where nomGroupe = ?";
        PreparedStatement stmt = c.prepareStatement(sqlIdGrp);
        stmt.setString(1,pNomGroupe);
        ResultSet rs = stmt.executeQuery();
        int pIdGroupe = 0;
        while(rs.next()){
            pIdGroupe = rs.getInt("idGroupe");
            System.out.println(pIdGroupe);
        }

        String sqlDelete = "DELETE from groupecontact WHERE idGroupe = ? ";
        PreparedStatement stm = c.prepareStatement(sqlDelete);
        stm.setInt(1,pIdGroupe);
        stm.executeUpdate();
        String sql = "DELETE from grp WHERE idGroupe = ?";
        PreparedStatement st = c.prepareStatement(sql);
        st.setInt(1,pIdGroupe);
        st.executeUpdate();
    }
    private Contact resultToContact(ResultSet rs) throws SQLException {
        return new Contact(rs.getString("nom"),
                rs.getString("prenom"),rs.getString("telephone1"),
                rs.getString("telephone2"),rs.getString("adresse"),
                rs.getString("emailper"),rs.getString("emailpro"),
                rs.getString("genre"));
    }



}

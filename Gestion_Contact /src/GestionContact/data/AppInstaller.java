package GestionContact.data;

import GestionContact.utils.FileManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AppInstaller {
    private static Logger LOGGER = Logger.getLogger(AppInstaller.class);
    public static void run() throws DataBaseException, SQLException {
        Connection con = DBConnexion.getInstance();
        Statement stmt = con.createStatement();

        // Drop the "Contact" table if it already exists
        String dropTableSql = "DROP TABLE IF EXISTS Contact";
        stmt.executeUpdate(dropTableSql);

        // Create the "Contact" table
        String createTableSql = "CREATE TABLE Contact ("
                + "idContact bigint auto_increment,"
                + "nom VARCHAR(255),"
                + "prenom VARCHAR(255),"
                + "telephone1 VARCHAR(255),"
                + "telephone2 VARCHAR(255),"
                + "adresse VARCHAR(255),"
                + "emailper VARCHAR(255),"
                + "emailpro VARCHAR(255),"
                + "genre VARCHAR(255),"
                + "PRIMARY KEY (idContact)"
                + ")";
        stmt.executeUpdate(createTableSql);

        String dropGrpSql = "DROP TABLE IF EXISTS Grp";
        stmt.executeUpdate(dropGrpSql);

        String createGroupe = """              
                create table Grp (
                			idGroupe bigint auto_increment,
                            nomGroupe varchar(255) unique,
                            PRIMARY KEY (idGroupe)
                            );
                     
                """;
        stmt.executeUpdate(createGroupe);

        String dropGrpConSql = "DROP TABLE IF EXISTS GroupeContact";
        stmt.executeUpdate(dropGrpConSql);

        String createGrpCon = """
                                
                CREATE TABLE GroupeContact (
                                    id bigint auto_increment,
                                    idGroupe bigint,
                                    idContact bigint,
                					PRIMARY KEY (id),
                                    CONSTRAINT `FKGroup` FOREIGN KEY (`idGroupe`) REFERENCES `Grp` (`idGroupe`) ON DELETE SET NULL ON UPDATE CASCADE,
                                    CONSTRAINT `FKcontact` FOREIGN KEY (`idContact`) REFERENCES `Contact` (`idContact`) ON DELETE SET NULL ON UPDATE CASCADE
                                   );            
                """;
        stmt.executeUpdate(createGrpCon);
    }

    public static  boolean checkIfAlreadyInstalled() throws IOException, DataBaseException, SQLException{
        String userHomeDirectory = System.getProperty("/Users/houdamoudni");
        Properties dbProperties = DBPropertiesLoader.loadPoperties("conf.properties");
        String dbName = dbProperties.getProperty("db.name");
        String dataBaseFile = userHomeDirectory+"/"+dbName+".mv.db";
        LOGGER.debug("End");
        return FileManager.fileExists(dataBaseFile);

    }

    public static void main(String[] args) throws SQLException, DataBaseException, IOException {
        System.out.println("ok");
        if(AppInstaller.checkIfAlreadyInstalled()){
            System.out.println("Database already exists");
        }
        else{
            //create the database
            AppInstaller.run();
            System.out.println("Database created correctelly");
        }

    }









}

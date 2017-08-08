package project5;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class talentedMusiciansBean{

    // Variables
    private String dbURL = "jdbc:derby://localhost:1527/TalentedMusicians;create=true;";
    private String dbDriver = "org.apache.derby.jdbc.ClientDriver";
    private Connection conn = null;
    private String search;

    /**
     * Gets DB Connection
     */
    private void getConnection() {
        try {
            Class.forName(dbDriver).newInstance();
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all records from DB
     * @return ArrayList of records
     * @throws SQLException is thrown if an error occurs during interaction with DB
     */
    public List<talentedMusicians> getAllTalentedMusicians() throws SQLException {

        getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM MUSICIANS");

        List<talentedMusicians> talentedMusicians = getTalentedMusicians(rs);

        // Close Resources
        rs.close();
        stmt.close();
        conn.close();

        return talentedMusicians;
    }

    /**
     * Inserts a new record into the DB
     * @param newID id of new record
     * @param newArtistName artistName of new record
     * @param newFirstName firstName of new record
     * @param newLastName lastName of new record
     * @param newAge age of new record
     * @param newGenre genre of new record
     * @param newGrammyNoms number of grammyNoms for new record
     * @param newGrammyWins number of grammyWins for new record
     */
    void addTalentedMusicians(int newID, String newArtistName, String newFirstName, String newLastName,
                                     int newAge, String newGenre, int newGrammyNoms, int newGrammyWins) {

        getConnection();

        try {

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO MUSICIANS VALUES (?,?,?,?,?,?,?,?)");

            stmt.setInt(1,newID);
            stmt.setString(2,newArtistName);
            stmt.setString(3,newFirstName);
            stmt.setString(4,newLastName);
            stmt.setInt(5,newAge);
            stmt.setString(6,newGenre);
            stmt.setInt(7,newGrammyNoms);
            stmt.setInt(8,newGrammyWins);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Cannot Add Record"));
        }
    }

    /**
     * Gets a list of records from DB (helper method)
     * @param rs SQL statement ResultSet
     * @return list of specified records
     * @throws SQLException is thrown if an error occurs during interaction with DB
     */
    private List<talentedMusicians> getTalentedMusicians(ResultSet rs) throws SQLException {

        List<talentedMusicians> talentedMusicians = new ArrayList<>();

        // Iterate through DB
        while (rs.next()) {
            talentedMusicians tm = new talentedMusicians();
            tm.setId(rs.getInt("ID"));
            tm.setArtistName(rs.getString("ARTIST_NAME"));
            tm.setFirstName(rs.getString("FIRST_NAME"));
            tm.setLastName(rs.getString("LAST_NAME"));
            tm.setAge(rs.getInt("AGE"));
            tm.setGenre(rs.getString("GENRE"));
            tm.setGrammyNoms(rs.getInt("GRAMMY_NOMS"));
            tm.setGrammyWins(rs.getInt("GRAMMYS_WON"));
            talentedMusicians.add(tm);
        }

        return talentedMusicians;
    }

    public void searchArtistName(String artistName) {

        String s = "SELECT * FROM MUSICIANS WHERE ARTIST_NAME LIKE '%" + artistName + "%'";
        setSearch(s);
    }

    public void searchID(int id) {

        String s = "SELECT * FROM MUSICIANS WHERE ID =" + id;
        setSearch(s);

    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<talentedMusicians> searchDB() {

        getConnection();
        List<talentedMusicians> talentedMusicians = null;

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(search);
            talentedMusicians = getTalentedMusicians(rs);

            // Close Resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Cannot Find Record"));
        }

        return talentedMusicians;
    }
}
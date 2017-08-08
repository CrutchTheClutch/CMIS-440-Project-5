package project5;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class talentedMusiciansManager {

    @ManagedProperty(value = "#{talentedMusiciansBean}")
    private talentedMusiciansBean talentedMusiciansBean;
    private int id;
    private String artistName;
    private String firstName;
    private String lastName;
    private int age;
    private String genre;
    private int grammyNoms;
    private int grammyWins;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getGrammyNoms() {
        return grammyNoms;
    }

    public void setGrammyNoms(int grammyNoms) {
        this.grammyNoms = grammyNoms;
    }

    public int getGrammyWins() {
        return grammyWins;
    }

    public void setGrammyWins(int grammyWins) {
        this.grammyWins = grammyWins;
    }

    public project5.talentedMusiciansBean getTalentedMusiciansBean() {
        return talentedMusiciansBean;
    }

    public void setTalentedMusiciansBean(project5.talentedMusiciansBean talentedMusiciansBean) {
        this.talentedMusiciansBean = talentedMusiciansBean;
    }

    public void addNewTalentedMusician() {
        talentedMusiciansBean.addTalentedMusicians(id, artistName, firstName, lastName, age, genre, grammyNoms, grammyWins);
    }

    public void searchID(){
        talentedMusiciansBean.searchID(id);
    }

    public void searchArtistName(){
        talentedMusiciansBean.searchArtistName(artistName);
    }

    public talentedMusiciansManager() {}
}

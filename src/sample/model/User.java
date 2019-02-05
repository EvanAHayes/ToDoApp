package sample.model;

//model user
public class User {

    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;
    private String Location;
    private String gender;

    public User() {
    }

    public User(String firstName, String lastName, String userName, String password, String location, String gender) {

        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        Password = password;
        Location = location;
        this.gender = gender;
    }

    public String getFirstName() {

        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLocation() {
        return Location;
    }

    public void setLoccation(String loccation) {
        Location = loccation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}

// redirect to '../.adt/DllUser.java'
// redirect to '../.nodes/NodeTransaksi.java'
// redirect to '../.nodes/NodeUser.java'


package data.schemas.models;

import util.AppEnums;

public class User {
    int id;
    String username, password, email;
    AppEnums.Roles role;

    public User(int id, String username, String password, String email, AppEnums.Roles role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppEnums.Roles getRole() {
        return role;
    }

    public void setRole(AppEnums.Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User Data\n" +
                "id: " + id +
                "\nusername: " + username +
                "\nemail: " + email +
                "\nrole: " + role;
    }
}

package de.hsrm.mi.web.projekt.security;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FotoUser {
    @Id
    private String username;

    private String password;
    private String role;

    public FotoUser(){
        password = "";
        role ="";
        username ="";
    }

    public FotoUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public FotoUser username(String username) {
        setUsername(username);
        return this;
    }

    public FotoUser password(String password) {
        setPassword(password);
        return this;
    }

    public FotoUser role(String role) {
        setRole(role);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FotoUser)) {
            return false;
        }
        FotoUser fotoUser = (FotoUser) o;
        return Objects.equals(username, fotoUser.username) && Objects.equals(password, fotoUser.password) && Objects.equals(role, fotoUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
    
}

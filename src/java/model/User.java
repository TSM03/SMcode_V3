package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This works with GENERATED ALWAYS AS IDENTITY in Derby
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private LocalDate birth; // Using LocalDate instead of java.sql.Date

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String mobileNo;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role;

    // Constructors
    public User() {}

    public User(String name, String username, LocalDate birth, String email, String mobileNo, String password, String role) {
        this.name = name;
        this.username = username;
        this.birth = birth;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
        this.role = role;
    }
    
    public User(Long id, String name, String username, LocalDate birth, String email, String mobileNo, String password, String role) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.birth = birth;
    this.email = email;
    this.mobileNo = mobileNo;
    this.password = password;
    this.role = role;
}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
    return role;
}

    public void setRole(String role) {
        this.role = role;
    }

}
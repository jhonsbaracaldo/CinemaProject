package ProyectoIntegradorCine.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(schema =  "\"userregistration\"")
public class UserRegistration {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@Column(unique = true)
    private String name;
    private String lastName;
    private Integer idUser;
    private String email;

    private String o;
    public UserRegistration() {
    }


    public UserRegistration(String name, String lastName, Integer idUser, String email) {
        this.name = name;
        this.lastName = lastName;
        this.idUser = idUser;
        this.email = email;
    }

    public UserRegistration(Integer id, String name, String lastName, Integer idUser, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.idUser = idUser;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

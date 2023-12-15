package ProyectoIntegradorCine.domain.model.entity.user;

import ProyectoIntegradorCine.Security.ERole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema =  "\"userregistration\"")
public class UserResgitration implements UserDetails {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@Column(unique = true)
    private String name;
    private String lastName;

    private String password;
    private Integer idUser;
    private Boolean enable;
@Enumerated(EnumType.ORDINAL)
    private ERole erole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
@Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    private String email;


    public UserResgitration(String name, String lastName, Integer idUser, String email,String password) {
        this.name = name;
        this.lastName = lastName;
        this.idUser = idUser;
        this.email = email;
        this.password= password;
    }

    public UserResgitration(Integer id, String name, String lastName, Integer idUser, String email,String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.idUser = idUser;
        this.email = email;
        this.password= password;
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

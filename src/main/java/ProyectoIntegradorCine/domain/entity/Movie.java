package ProyectoIntegradorCine.domain.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(schema =  "movie", name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String  name;

    private String category;

    private Date date;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public Movie() {
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    private  String Descripcion;

    public Movie(String name, String category, Date date, String descripcion) {
        this.name = name;
        this.category = category;
        this.date = date;
        Descripcion = descripcion;
    }

    public Movie(Integer id, String name, String category, Date date, String descripcion) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.date = date;
        Descripcion = descripcion;
    }
}

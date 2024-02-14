package ProyectoIntegradorCine.domain.models.Dto;

import java.util.Date;

public class MovieDto {
    private Integer id ;

    private String  name;

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

    private String category;

    private Date date;

    public MovieDto(String name, String category, Date date) {
        this.name = name;
        this.category = category;
        this.date = date;
    }

    public MovieDto(Integer id, String name, String category, Date date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.date = date;
    }

    public MovieDto() {
    }
}

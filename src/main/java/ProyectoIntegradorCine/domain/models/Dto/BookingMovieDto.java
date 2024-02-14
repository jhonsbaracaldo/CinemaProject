package ProyectoIntegradorCine.domain.models.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class BookingMovieDto {
    public BookingMovieDto() {
    }
    @JsonIgnore
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    private String sala;
    private Integer asientos;

    public BookingMovieDto(String name, String sala, Integer asientos, LocalDate horaFuncion) {
        this.name = name;
        this.sala = sala;
        this.asientos = asientos;
        this.horaFuncion = horaFuncion;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Integer getAsientos() {
        return asientos;
    }

    public void setAsientos(Integer asientos) {
        this.asientos = asientos;
    }

    public LocalDate getHoraFuncion() {
        return horaFuncion;
    }

    public void setHoraFuncion(LocalDate horaFuncion) {
        this.horaFuncion = horaFuncion;
    }

    private LocalDate horaFuncion;

}

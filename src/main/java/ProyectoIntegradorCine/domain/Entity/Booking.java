package ProyectoIntegradorCine.domain.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
    @Table(schema =  "movie")
    public class Booking {
        //    @Getter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String name;

        private String sala;
        private Integer asientos;
    private LocalDate horaFuncion;



    public Booking(Integer id, String name, String sala, Integer asientos, LocalDate horaFuncion) {
        this.id = id;
        this.name = name;
        this.sala = sala;
        this.asientos = asientos;
        this.horaFuncion = horaFuncion;
    }


    public Booking() {
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

    public Booking(String name, String sala, Integer asientos, LocalDate horaFuncion) {
        this.name = name;
        this.sala = sala;
        this.asientos = asientos;
        this.horaFuncion = horaFuncion;
    }
}



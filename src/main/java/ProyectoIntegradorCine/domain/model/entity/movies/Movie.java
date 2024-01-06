package ProyectoIntegradorCine.domain.model.entity.movies;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
    @Table(schema =  "\"movie\"")
    public class Movie {
        //    @Getter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(unique = true)
        private String name;


        private String category;

        private Double price;

        private Integer asientos;

        private LocalDate horaFuncion;

        public Movie() {
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

        public String getCategory() {
            return category;
        }

        public void setHoraFuncion(LocalDate horaFuncion) {
            this.horaFuncion = horaFuncion;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public LocalDate getHoraFuncion() {
            return horaFuncion;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getAsientos() {
            return asientos;
        }

        public void setAsientos(Integer asientos) {
            this.asientos = asientos;
        }


        public Movie(Integer id, String name, String category, Double price, Integer asientos, LocalDate horaFuncion) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
            this.asientos = asientos;
            this.horaFuncion = horaFuncion;


        }

        public Movie(String name, String category, Double price, Integer asientos, LocalDate horaFuncion) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.asientos = asientos;
            this.horaFuncion = horaFuncion;
        }



    }



package com.example.demo.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "zadanie")
@Getter
@Setter
public class ZadanieModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tytul;
    private String opis;
    private String status;
    private LocalDate termin;

    @ManyToMany
    @JoinTable(
            name = "task_users",
            joinColumns = @JoinColumn(name = "zadanie_id"),
            inverseJoinColumns = @JoinColumn(name = "urzytkownik_id"))
    private List<UzytkownikModel> przypisanyUrzytkownik;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZadanieModel)) return false;
        ZadanieModel task = (ZadanieModel) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.example.demo.repo;

import com.example.demo.modele.UzytkownikModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UzytkownikRepo extends JpaRepository<UzytkownikModel, Long> {

    List<UzytkownikModel> findByImie(String imie);

    List<UzytkownikModel> findByNazwisko(String nazwisko);

    List<UzytkownikModel> findByEmail(String email);

}
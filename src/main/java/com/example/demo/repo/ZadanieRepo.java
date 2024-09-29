package com.example.demo.repo;

import com.example.demo.modele.ZadanieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ZadanieRepo extends JpaRepository<ZadanieModel, Long> {
    List<ZadanieModel> findByTytul(String tytul);

    List<ZadanieModel> findByStatus(String status);

    Collection<Object> findByTermin(LocalDate termin);

    Optional<ZadanieModel> findById(Long id);
}
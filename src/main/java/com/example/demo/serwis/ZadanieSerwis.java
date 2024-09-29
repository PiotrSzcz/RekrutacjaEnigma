package com.example.demo.serwis;


import com.example.demo.modele.UzytkownikModel;
import com.example.demo.modele.ZadanieModel;
import com.example.demo.repo.ZadanieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ZadanieSerwis {
    @Autowired
    private ZadanieRepo zadanieRepo;

    public List<ZadanieModel> findAll() {
        return zadanieRepo.findAll();
    }

    public ZadanieModel save(ZadanieModel zadanieModel) {
        return zadanieRepo.save(zadanieModel);

    }

    public ResponseEntity<?> findUserByTytul(String tytul) {
        return zadanieRepo.findByTytul(tytul).isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(zadanieRepo.findByTytul(tytul));
    }

    public ResponseEntity<?> findUserByStatus(String status) {
        return zadanieRepo.findByStatus(status).isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(zadanieRepo.findByStatus(status));
    }

    public ResponseEntity<?> findUserByTermin(LocalDate termin) {
        return zadanieRepo.findByTermin(termin).isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(zadanieRepo.findByTermin(termin));
    }

    public boolean updateZadanie(Long id, ZadanieModel zadanieDetails) {
        Optional<ZadanieModel> zmotyfikowane = zadanieRepo.findById(id).map(zadanie -> {
            zadanie.setTytul(zadanieDetails.getTytul());
            zadanie.setOpis(zadanieDetails.getOpis());
            zadanie.setTermin(zadanieDetails.getTermin());
            zadanie.setPrzypisanyUrzytkownik(zadanieDetails.getPrzypisanyUrzytkownik());
            zadanie.setStatus(zadanieDetails.getStatus());
            return zadanieRepo.save(zadanie);
        });
        return zmotyfikowane.isPresent();
    }

    public boolean deleteZadanie(Long id) {
        Optional<ZadanieModel> zadanie = zadanieRepo.findById(id);
        if (zadanie.isPresent()) {
            zadanieRepo.delete(zadanie.get());
            return true;
        }
        return false;
    }

    public boolean updateStatus(Long id, String status) {
        Optional<ZadanieModel> zmotyfikowane = zadanieRepo.findById(id).map(zadanie -> {
            zadanie.setStatus(status);
            return zadanieRepo.save(zadanie);
        });
        return zmotyfikowane.isPresent();
    }

    public boolean przypiszUzytkownikow(Long id, List<UzytkownikModel> uzytkownicyIds) {
        Optional<ZadanieModel> zmotyfikowane = zadanieRepo.findById(id).map(zadanie -> {
            zadanie.setPrzypisanyUrzytkownik(uzytkownicyIds);
            return zadanieRepo.save(zadanie);
        });
        return zmotyfikowane.isPresent();
    }
}


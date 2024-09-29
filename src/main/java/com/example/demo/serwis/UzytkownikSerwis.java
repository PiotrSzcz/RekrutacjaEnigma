package com.example.demo.serwis;

import com.example.demo.modele.UzytkownikModel;
import com.example.demo.repo.UzytkownikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UzytkownikSerwis {

    @Autowired
    private UzytkownikRepo uzytkownikRepo;

    public List<UzytkownikModel> findAll() {
        return uzytkownikRepo.findAll();
    }

    public UzytkownikModel save(UzytkownikModel uzytkownikModel) {
        return uzytkownikRepo.save(uzytkownikModel);
    }

    public boolean deleteUzytkownik(Long id) {
        Optional<UzytkownikModel> uzytkownik = uzytkownikRepo.findById(id);
        if (uzytkownik.isPresent()) {
            uzytkownikRepo.delete(uzytkownik.get());
            return true;
        }
        return false;
    }
}


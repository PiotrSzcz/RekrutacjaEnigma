package com.example.demo.kontroler;

import com.example.demo.modele.UzytkownikModel;
import com.example.demo.serwis.UzytkownikSerwis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uzytkownik")
public class UzytkownikKontroler {

    @Autowired
    private UzytkownikSerwis uzytkownikSerwis;

    @GetMapping
    public List<UzytkownikModel> getAllUsers() {
        return uzytkownikSerwis.findAll();
    }

    @PostMapping
    public UzytkownikModel createUser(@RequestBody UzytkownikModel uzytkownikModel) {
        return uzytkownikSerwis.save(uzytkownikModel);
    }

    @DeleteMapping("/usuwanie/{id}")
    public ResponseEntity<?> deleteUzytkownik(@PathVariable Long id) {
        if (uzytkownikSerwis.deleteUzytkownik(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

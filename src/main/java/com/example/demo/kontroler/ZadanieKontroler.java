package com.example.demo.kontroler;

import com.example.demo.modele.UzytkownikModel;
import com.example.demo.modele.ZadanieModel;
import com.example.demo.serwis.ZadanieSerwis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/zadanie")
public class ZadanieKontroler {

    @Autowired
    private ZadanieSerwis zadanieSerwis;

    @GetMapping
    public List<ZadanieModel> getAllUsers() {
        return zadanieSerwis.findAll();
    }

    @GetMapping("/byTutul/{tytul}")
    public ResponseEntity<?> getUserByTytul(@PathVariable String tytul) {
        return zadanieSerwis.findUserByTytul(tytul);
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<?> getUserByStatus(@PathVariable String status) {
        return zadanieSerwis.findUserByStatus(status);
    }

    @GetMapping("/byTermin/{termin}")
    public ResponseEntity<?> getUserByTermin(@PathVariable String termin) {
        LocalDate data = LocalDate.now();
        try {
            data = LocalDate.parse(termin);
            return zadanieSerwis.findUserByTermin(data);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body("Błędny format daty.");
        }
    }

    @PostMapping
    public ZadanieModel createUser(@RequestBody ZadanieModel zadanieModel) {
        return zadanieSerwis.save(zadanieModel);
    }

    @PutMapping("/zmiana/{id}")
    public ResponseEntity<?> updateZadanie(@PathVariable Long id, @RequestBody ZadanieModel zadanieDetails) {
        return zadanieSerwis.updateZadanie(id, zadanieDetails) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuwanie/{id}")
    public ResponseEntity<?> deleteZadanie(@PathVariable Long id) {
        if (zadanieSerwis.deleteZadanie(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateZadanieStatus(@PathVariable Long id, @RequestParam String status) {
        if (zadanieSerwis.updateStatus(id, status)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/przypiszUzytkownikow")
    public ResponseEntity<?> przypiszUzytkownikow(@PathVariable Long id, @RequestBody List<UzytkownikModel> uzytkownicyIds) {
        if (zadanieSerwis.przypiszUzytkownikow(id, uzytkownicyIds)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


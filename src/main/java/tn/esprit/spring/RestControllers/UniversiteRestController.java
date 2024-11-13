package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import tn.esprit.spring.DAO.Entities.Universite;
=======
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.Services.Foyer.IFoyerService;
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf
import tn.esprit.spring.Services.Universite.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("universite")
@AllArgsConstructor
public class UniversiteRestController {
<<<<<<< HEAD

    IUniversiteService service;

    @PostMapping("addOrUpdate")
    public Universite addOrUpdate(@RequestBody Universite u) {
=======
    IUniversiteService service;

    @PostMapping("addOrUpdate")
    Universite addOrUpdate(@RequestBody Universite u) {
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf
        return service.addOrUpdate(u);
    }

    @GetMapping("findAll")
<<<<<<< HEAD
    public List<Universite> findAll() {
=======
    List<Universite> findAll() {
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf
        return service.findAll();
    }

    @GetMapping("findById")
<<<<<<< HEAD
    public Universite findById(@RequestParam long id) {
=======
    Universite findById(@RequestParam long id) {
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf
        return service.findById(id);
    }

    @DeleteMapping("delete")
<<<<<<< HEAD
    public void delete(@RequestBody Universite u) {
=======
    void delete(@RequestBody Universite u) {
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf
        service.delete(u);
    }

    @DeleteMapping("deleteById")
<<<<<<< HEAD
    public void deleteById(@RequestParam long id) {
        service.deleteById(id);
    }

    // New functionality 1: Find universities by foyer ID and student enrollment status
    @GetMapping("findByFoyerAndStudentStatus")
    public List<Universite> findByFoyerAndStudentStatus(@RequestParam long foyerId, @RequestParam String status) {
        return service.findUniversitesByFoyerAndStudentStatus(foyerId, status);
    }

    // New functionality 2: Count universities by address
    @GetMapping("countByAddress")
    public long countByAddress(@RequestParam String adresse) {
        return service.countUniversitiesByAddress(adresse);
    }
}
=======
    void deleteById(@RequestParam long id) {
        service.deleteById(id);
    }


}
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf

package tn.esprit.spring.Services.Universite;

import tn.esprit.spring.DAO.Entities.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite addOrUpdate(Universite u);
    List<Universite> findAll();
    Universite findById(long id);
    void deleteById(long id);
    void delete(Universite u);
<<<<<<< HEAD

    // New functionality 1: Find universities by foyer ID and student enrollment status
    List<Universite> findUniversitesByFoyerAndStudentStatus(long foyerId, String status);

    // New functionality 2: Count universities by address
    long countUniversitiesByAddress(String adresse);
}
=======
}
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf

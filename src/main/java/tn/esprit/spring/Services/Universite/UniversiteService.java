package tn.esprit.spring.Services.Universite;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import tn.esprit.spring.DAO.Entities.Universite;
=======
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteService implements IUniversiteService {
<<<<<<< HEAD

    private final UniversiteRepository repo;
=======
    UniversiteRepository repo;
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf

    @Override
    public Universite addOrUpdate(Universite u) {
        return repo.save(u);
    }

    @Override
    public List<Universite> findAll() {
        return repo.findAll();
    }

    @Override
    public Universite findById(long id) {
<<<<<<< HEAD
        return repo.findById(id).orElse(null);
=======
        return repo.findById(id).get();
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf
    }

    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    @Override
    public void delete(Universite u) {
        repo.delete(u);
    }
<<<<<<< HEAD

    // New functionality 1: Find universities by foyer ID and student enrollment status
    @Override
    public List<Universite> findUniversitesByFoyerAndStudentStatus(long foyerId, String status) {
        return repo.findByFoyerIdAndFoyerBlocsChambresReservationsEtudiantsStatus(foyerId, status);
    }

    // New functionality 2: Count universities by address
    @Override
    public long countUniversitiesByAddress(String adresse) {
        return repo.countByAdresse(adresse);
    }
}
=======
}
>>>>>>> 94e3795eee2e156635c933ee79ce07c3edc3e0cf

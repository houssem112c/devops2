package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.Services.Etudiant.EtudiantService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Use MockitoExtension to initialize mocks
public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;  // Mock the repository

    @InjectMocks
    private EtudiantService etudiantService;  // Inject mocks into the service

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEt("Doe")
                .prenomEt("John")
                .cin(12345678L)
                .ecole("ESPRIT")
                .dateNaissance(LocalDate.of(2000, 1, 1))
                .build();
    }

    @Test
    public void testAddOrUpdate() {
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        Etudiant savedEtudiant = etudiantService.addOrUpdate(etudiant);
        assertNotNull(savedEtudiant);
        assertEquals("Doe", savedEtudiant.getNomEt());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    public void testFindAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        Etudiant foundEtudiant = etudiantService.findById(1L);
        assertNotNull(foundEtudiant);
        assertEquals("Doe", foundEtudiant.getNomEt());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(etudiantRepository).deleteById(1L);

        etudiantService.deleteById(1L);
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDelete() {
        doNothing().when(etudiantRepository).delete(etudiant);

        etudiantService.delete(etudiant);
        verify(etudiantRepository, times(1)).delete(etudiant);
    }
}

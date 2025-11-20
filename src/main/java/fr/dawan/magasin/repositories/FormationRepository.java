package fr.dawan.magasin.repositories;

import fr.dawan.magasin.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


public interface FormationRepository extends JpaRepository<Formation, Long> {
//Sujet By, les prédicats
    // Un prédicat commence toujours par une variable d'instance de l'entité
    List<Formation> findByPrix(BigDecimal searchPrice);
}

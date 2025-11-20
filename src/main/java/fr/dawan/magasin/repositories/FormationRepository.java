package fr.dawan.magasin.repositories;

import fr.dawan.magasin.entities.Formation;
import fr.dawan.magasin.enums.StatusFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


public interface FormationRepository extends JpaRepository<Formation, Long> {
//Sujet By, les prédicats
    // Un prédicat commence toujours par une variable d'instance de l'entité
    List<Formation> findByPrix(BigDecimal searchPrice);
    // Pour le prédicat la variable d'instance peut être suivie d'un opérateur
// LessThan => <
    List<Formation> findByPrixLessThan(BigDecimal maxPrice);

    // GreaterThan => >
    List<Formation> findByDureeGreaterThan(int minDuree);

    // And -> combiner plusieurs conditions avec l'opérateur and
    List<Formation> findByEtatAndPrixLessThan(StatusFormation etat, BigDecimal prixMax);

    // Or -> combiner plusieurs conditions avec l'opérateur or
    List<Formation> findByEtatOrPrixLessThan(StatusFormation etat, BigDecimal prixMax);

}

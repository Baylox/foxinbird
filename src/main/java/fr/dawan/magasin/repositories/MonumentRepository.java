package fr.dawan.magasin.repositories;

import fr.dawan.magasin.entities.Monument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, Long> {

    /**
     * Rechercher les monuments dont l'année de construction est supérieure à une valeur
     * @param annee l'année de référence
     * @return liste des monuments construits après cette année
     */
    List<Monument> findByAnneeConstructionGreaterThan(int annee);

    /**
     * Rechercher les monuments dans un intervalle d'années de construction
     * Résultats triés par année de construction décroissante
     * @param anneeDebut année de début (incluse)
     * @param anneeFin année de fin (incluse)
     * @return liste des monuments triés par année décroissante
     */
    List<Monument> findByAnneeConstructionBetweenOrderByAnneeConstructionDesc(int anneeDebut, int anneeFin);

    /**
     * Rechercher les monuments dont le nom correspond à un pattern (LIKE)
     * @param pattern le pattern de recherche (ex: "%Tour%")
     * @return liste des monuments correspondants
     */
    List<Monument> findByNomLike(String pattern);

    /**
     * Rechercher les monuments dont la description contient un mot
     * @param mot le mot recherché
     * @return liste des monuments dont la description contient le mot
     */
    List<Monument> findByDescriptionContaining(String mot);

}

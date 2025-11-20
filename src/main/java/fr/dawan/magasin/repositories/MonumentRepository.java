package fr.dawan.magasin.repositories;

import fr.dawan.magasin.entities.Monument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Rechercher les monuments en fonction d'une latitude et longitude
     * Path Expression : coordonnee.lattitude et coordonnee.longitude
     * @param latitude la latitude
     * @param longitude la longitude
     * @return liste des monuments à ces coordonnées
     */
    List<Monument> findByCoordonnee_LattitudeAndCoordonnee_Longitude(double latitude, double longitude);

    /**
     * Rechercher les monuments en fonction de plusieurs pays (paginé)
     * Path Expression : localisation.pays
     * @param pays liste des pays
     * @param pageable objet de pagination
     * @return page de monuments correspondants
     */
    Page<Monument> findByLocalisation_PaysIn(List<String> pays, Pageable pageable);

    /**
     * Rechercher les 5 monuments les plus anciens
     * Triés par année de construction croissante, limités à 5 résultats
     * @return liste des 5 monuments les plus anciens
     */
    List<Monument> findTop5ByOrderByAnneeConstructionAsc();

    /**
     * Rechercher les monuments en fonction d'un intitulé d'étiquette
     * Path Expression : etiquettes.intitule
     * @param intitule l'intitulé de l'étiquette
     * @return liste des monuments avec cette étiquette
     */
    List<Monument> findByEtiquettes_Intitule(String intitule);

    // ========== REQUÊTES JPQL PERSONNALISÉES ==========

    /**
     * Compter le nombre de monuments par pays
     * Requête JPQL avec agrégation
     * @param pays le nom du pays
     * @return le nombre de monuments dans ce pays
     */
    @Query("SELECT COUNT(m) FROM Monument m WHERE m.localisation.pays = :pays")
    Long countMonumentsByPays(@Param("pays") String pays);

    /**
     * Récupérer les monuments avec leur prix de visite dans une fourchette
     * Requête JPQL avec paramètres nommés
     * @param prixMin prix minimum
     * @param prixMax prix maximum
     * @return liste des monuments dans cette fourchette de prix
     */
    @Query("SELECT m FROM Monument m WHERE m.prixVisite BETWEEN :prixMin AND :prixMax ORDER BY m.prixVisite ASC")
    List<Monument> findMonumentsByPrixVisiteRange(@Param("prixMin") double prixMin, @Param("prixMax") double prixMax);

    /**
     * Récupérer les monuments avec leurs coordonnées (projection)
     * Requête JPQL avec jointure
     * @return liste des monuments avec coordonnées non nulles
     */
    @Query("SELECT m FROM Monument m JOIN m.coordonnee c WHERE c IS NOT NULL")
    List<Monument> findMonumentsWithCoordinates();

    /**
     * Calculer le prix moyen de visite par pays
     * Requête JPQL avec agrégation et GROUP BY (retourne Object[])
     * @return liste de tableaux [pays, prixMoyen]
     */
    @Query("SELECT m.localisation.pays, AVG(m.prixVisite) FROM Monument m GROUP BY m.localisation.pays")
    List<Object[]> findAveragePriceByCountry();

}

package fr.dawan.magasin;

import fr.dawan.magasin.entities.Formation;
import fr.dawan.magasin.entities.Monument;
import fr.dawan.magasin.entities.Session;
import fr.dawan.magasin.enums.StatusFormation;
import fr.dawan.magasin.repositories.FormationRepository;
import fr.dawan.magasin.repositories.MonumentRepository;
import fr.dawan.magasin.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RepositoryRunner implements CommandLineRunner {

    private final FormationRepository formationRepository;
    private final MonumentRepository monumentRepository;
    private final SessionRepository sessionRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Formation> formations = formationRepository.findAll();
        for(Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByPrix(new BigDecimal(800));
        for(Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByPrixLessThan(new BigDecimal(1000.0));
        for(Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByDureeGreaterThan(25);
        for(Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByEtatAndPrixLessThan(StatusFormation.ACTIVE, BigDecimal.valueOf(500));
        for (Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByEtatOrPrixLessThan(StatusFormation.DEPRECIEE, BigDecimal.valueOf(500));
        for (Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByPrixBetween(BigDecimal.valueOf(100), BigDecimal.valueOf(1000));
        for (Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByPrixIn(BigDecimal.valueOf(850), BigDecimal.valueOf(1450));
        for (Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByTitreStartsWith("Java");
        for (Formation f : formations) {
            System.out.println(f);
        }

        formations = formationRepository.findByTitreStartsWith("Java");
        for (Formation f : formations) {
            System.out.println(f);
        }

        System.out.println("\n========== TESTS MONUMENT REPOSITORY ==========\n");

        // 1. Monuments dont l'année de construction > 1800
        System.out.println("--- Monuments construits après 1800 ---");
        List<Monument> monuments = monumentRepository.findByAnneeConstructionGreaterThan(1800);
        for (Monument m : monuments) {
            System.out.println(m.getNom() + " (" + m.getAnneeConstruction() + ")");
        }

        // 2. Monuments entre 1500 et 1900, triés par année décroissante
        System.out.println("\n--- Monuments entre 1500 et 1900 (triés décroissants) ---");
        monuments = monumentRepository.findByAnneeConstructionBetweenOrderByAnneeConstructionDesc(1500, 1900);
        for (Monument m : monuments) {
            System.out.println(m.getNom() + " (" + m.getAnneeConstruction() + ")");
        }

        // 3. Recherche par nom avec LIKE
        System.out.println("\n--- Monuments dont le nom contient 'Tour' ---");
        monuments = monumentRepository.findByNomLike("%Tour%");
        for (Monument m : monuments) {
            System.out.println(m.getNom());
        }

        // 4. Recherche dans la description
        System.out.println("\n--- Monuments dont la description contient 'gothique' ---");
        monuments = monumentRepository.findByDescriptionContaining("gothique");
        for (Monument m : monuments) {
            System.out.println(m.getNom() + " : " + m.getDescription());
        }

        System.out.println("\n========== TESTS SESSION REPOSITORY ==========\n");

        // Recherche des sessions pour la formation avec id=1
        System.out.println("--- Sessions pour la formation ID=1 ---");
        Formation f = formationRepository.findById(1L).orElse(null);
        if (f != null) {
            System.out.println("Formation: " + f.getTitre());
            List<Session> sessions = sessionRepository.findByFormation(f);
            if (sessions.isEmpty()) {
                System.out.println("  Aucune session trouvée");
            } else {
                for (Session s : sessions) {
                    System.out.printf("  Session #%d - Lieu: %s | Date: %s | Places: %d%n",
                        s.getId(), s.getLieu(), s.getDateDebut(), s.getNbPlace());
                }
            }
        } else {
            System.out.println("Formation non trouvée");
        }

        System.out.println("\n========== TESTS AVANCÉS MONUMENT REPOSITORY ==========\n");

        // 5. Recherche par latitude et longitude
        System.out.println("--- Monuments aux coordonnées lat=48.8566, long=2.3522 ---");
        monuments = monumentRepository.findByCoordonnee_LattitudeAndCoordonnee_Longitude(48.8566, 2.3522);
        if (monuments.isEmpty()) {
            System.out.println("  Aucun monument trouvé à ces coordonnées");
        } else {
            for (Monument m : monuments) {
                System.out.println("  " + m.getNom());
            }
        }

        // 6. Recherche par plusieurs pays (paginé)
        System.out.println("\n--- Monuments en France, Italie ou Espagne (page 0, 5 éléments) ---");
        List<String> pays = Arrays.asList("France", "Italie", "Espagne");
        Pageable pageable = PageRequest.of(0, 5); // Page 0, 5 éléments
        Page<Monument> monumentsPage = monumentRepository.findByLocalisation_PaysIn(pays, pageable);
        System.out.printf("  Total: %d monuments | Page: %d/%d%n",
            monumentsPage.getTotalElements(),
            monumentsPage.getNumber() + 1,
            monumentsPage.getTotalPages());
        for (Monument m : monumentsPage.getContent()) {
            System.out.println("  " + m.getNom() + " (" + (m.getLocalisation() != null ? m.getLocalisation().getPays() : "N/A") + ")");
        }

        // 7. Les 5 monuments les plus anciens
        System.out.println("\n--- Les 5 monuments les plus anciens ---");
        monuments = monumentRepository.findTop5ByOrderByAnneeConstructionAsc();
        for (Monument m : monuments) {
            System.out.println("  " + m.getNom() + " (" + m.getAnneeConstruction() + ")");
        }

        // 8. Recherche par intitulé d'étiquette
        System.out.println("\n--- Monuments avec l'étiquette 'Historique' ---");
        monuments = monumentRepository.findByEtiquettes_Intitule("Historique");
        if (monuments.isEmpty()) {
            System.out.println("  Aucun monument trouvé avec cette étiquette");
        } else {
            for (Monument m : monuments) {
                System.out.println("  " + m.getNom());
            }
        }

    }

}

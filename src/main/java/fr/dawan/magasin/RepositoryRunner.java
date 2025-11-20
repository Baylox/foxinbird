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
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
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



    }

}

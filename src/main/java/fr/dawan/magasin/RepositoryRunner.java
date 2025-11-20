package fr.dawan.magasin;

import fr.dawan.magasin.entities.Formation;
import fr.dawan.magasin.enums.StatusFormation;
import fr.dawan.magasin.repositories.FormationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RepositoryRunner implements CommandLineRunner {

    private final FormationRepository formationRepository;

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

    }

}

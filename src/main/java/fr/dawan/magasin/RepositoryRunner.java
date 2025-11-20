package fr.dawan.magasin;

import fr.dawan.magasin.entities.Formation;
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
    }

}

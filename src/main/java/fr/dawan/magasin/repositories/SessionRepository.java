package fr.dawan.magasin.repositories;

import fr.dawan.magasin.entities.Formation;
import fr.dawan.magasin.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByFormation(Formation formation);

    // TODO: Ajouter d'autres méthodes ici si nécessaire

}


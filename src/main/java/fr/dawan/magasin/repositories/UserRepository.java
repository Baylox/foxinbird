package fr.dawan.magasin.repositories;

import fr.dawan.magasin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Rechercher un User en fonction de son email
     * @param email l'email de l'utilisateur
     * @return l'utilisateur correspondant ou vide
     */
    Optional<User> findByEmail(String email);

}

package fr.dawan.magasin.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(length = 50)
    private String prenom;

    @Column(length = 50, nullable = false)
    private String nom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(length = 200, nullable = false, unique = true)
    private String email;

    @Column(length = 80, nullable = false)
    private String password;

    private boolean enable;

}

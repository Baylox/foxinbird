package fr.dawan.magasin.formation.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)

@Entity
@Table(name = "sessions")
public class Session extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(length = 50, nullable = false)
    private String lieu;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(name="nb_place")
    private int nbPlace;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;
}

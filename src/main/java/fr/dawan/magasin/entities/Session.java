package fr.dawan.magasin.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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

    @Column(name = "date", nullable = false)
    private LocalDate dateDebut;

    @Column(name="nb_place")
    private int nbPlace;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToMany
    private Set<Lieu> lieux=new HashSet<>();
}

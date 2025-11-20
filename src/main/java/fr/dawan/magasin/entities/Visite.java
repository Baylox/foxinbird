package fr.dawan.magasin.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "visites")
public class Visite extends BaseEntity {

    @Column(nullable = false)
    private int nombrePersonne;

    @Column(nullable = false)
    private LocalDate dateVisite;

    @ManyToOne
    @JoinColumn(name = "monument_id")
    @ToString.Exclude
    private Monument monument;

}

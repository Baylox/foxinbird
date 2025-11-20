package fr.dawan.magasin.tourisme.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "coordonnees")
public class Coordonnee extends BaseEntity {

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double lattitude;

    @OneToOne(mappedBy = "coordonnee")
    private Monument monument;

}

package fr.dawan.magasin.entities;

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

    @ToString.Exclude
    @OneToOne(mappedBy = "coordonnee") // Il faut un MappedBy car la clé étrangère est dans Monument (OnetoOne pour ne pas créer une deuxième relation)
    private Monument monument;

}

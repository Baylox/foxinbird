package fr.dawan.magasin.tourisme.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "localisations")
public class Localisation extends BaseEntity {

    @Column(nullable = false, length = 80)
    private String pays;

    @Column(nullable = false, length = 80)
    private String ville;

    @OneToMany(mappedBy = "localisation")
    private List<Monument> monuments = new ArrayList<>();

}

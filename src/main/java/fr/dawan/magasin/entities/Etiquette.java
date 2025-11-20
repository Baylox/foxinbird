package fr.dawan.magasin.entities;

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
@Table(name = "etiquettes")
public class Etiquette extends BaseEntity {

    @Column(nullable = false, length = 60)
    private String intitule;

    @ToString.Exclude
    @ManyToMany(mappedBy = "etiquettes")
    private List<Monument> monuments = new ArrayList<>();

}

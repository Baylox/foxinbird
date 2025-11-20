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
@Table(name = "monuments")
public class Monument extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String nom;

    @Column(nullable = false)
    private double prixVisite;

    @Column(nullable = false)
    private int anneeConstruction;

    @Column(length = 255)
    private String description;

    @Lob
    @Column(columnDefinition = "BLOB", length = 65000)
    private byte[] photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordonnee_id")
    private Coordonnee coordonnee;

    @ManyToOne
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
        name = "monument_etiquette",
        joinColumns = @JoinColumn(name = "monument_id"),
        inverseJoinColumns = @JoinColumn(name = "etiquette_id")
    )
    private List<Etiquette> etiquettes = new ArrayList<>();

    @OneToMany(mappedBy = "monument")
    private List<Visite> visites = new ArrayList<>();

}

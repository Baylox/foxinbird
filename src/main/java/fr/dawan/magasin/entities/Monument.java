package fr.dawan.magasin.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 150, message = "Le nom doit contenir entre 2 et 150 caractères")
    @Column(nullable = false, length = 150)
    private String nom;

    @Min(value = 0, message = "Le prix doit être positif")
    @Column(nullable = false)
    private double prixVisite;

    @Min(value = 0, message = "L'année doit être positive")
    @Max(value = 2100, message = "L'année ne peut pas dépasser 2100")
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

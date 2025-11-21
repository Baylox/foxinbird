package fr.dawan.magasin.Forms;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class MonumentForm {

    private String nom;
    private double prixVisite;
    private int anneeConstruction;
    private String description;
}



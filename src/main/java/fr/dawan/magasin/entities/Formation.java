package fr.dawan.magasin.entities;

import fr.dawan.magasin.enums.StatusFormation;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "formations")
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @Column(length = 100, nullable = false)
    private String titre;

    @Column(length = 8, nullable = false)
    private String code;

    @Column(precision = 6, scale = 2)
    private BigDecimal prix;

    private int duree;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private StatusFormation etat;


    @Transient
    private transient int nePasPersister;

}

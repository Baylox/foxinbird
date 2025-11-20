package fr.dawan.magasin.formation.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import fr.dawan.magasin.enums.StatusFormation;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "formations")
public class Formation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Version
    private int version;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "formation")
    private Set<Session> sessions = new HashSet<>();

}

package fr.dawan.magasin.entities;

import fr.dawan.magasin.shared.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "lieux")
public class Lieu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable=false, length=140)
    private String ville;

    @Column(nullable=false)
    private String adresse;

    @Column(nullable=false, length=12)
    private String codePostal;

    @ManyToMany(mappedBy="lieux")
    private Set<Session> session=new HashSet<>();
}

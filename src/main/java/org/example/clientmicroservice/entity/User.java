package org.example.clientmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user")
@Data
@NoArgsConstructor

public class User {
    @Id
    @Column(name = "cin", nullable = false, unique = true, updatable = false)
    private String cin;

    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String emploi;
    private String adresse;
    private int nbreEnfants;
    private String statutMarital;
    private float revenue;
    private String email;
    private String role="CLIENT";
    private String typeAssurance;
    private String adresseWallet;
    private String genre;






}

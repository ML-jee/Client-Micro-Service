package org.example.clientmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
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
    private String token;
    private String genre;

    private AddAssuranceDto assurance;

}

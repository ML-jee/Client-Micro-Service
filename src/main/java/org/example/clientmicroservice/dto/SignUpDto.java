package org.example.clientmicroservice.dto;

import java.util.Date;


public record SignUpDto(String cin, String nom, String prenom, String adresseWallet,
                        String email, String adresse, Date dateNaissance,
                        String statutMarital, int nbreEnfants, float revenue, String emploi) { }

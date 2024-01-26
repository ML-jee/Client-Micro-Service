package org.example.clientmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddAssuranceDto {
    private String idAssurance;
    private String nom;
    private String description;
    private float prix;
}

package com.estudos.rocketseat.certificacaoNLW.modules.questions.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternativeResultDto {
    private UUID id;
    private String description;
}

package com.estudos.rocketseat.certificacaoNLW.modules.questions.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResultDto {
    private UUID id;
    private String technology;
    private String description;

    private List<AlternativeResultDto> alternatives;
}

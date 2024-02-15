package com.estudos.rocketseat.certificacaoNLW.modules.questions.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.rocketseat.certificacaoNLW.modules.questions.dto.AlternativeResultDto;
import com.estudos.rocketseat.certificacaoNLW.modules.questions.dto.QuestionResultDto;
import com.estudos.rocketseat.certificacaoNLW.modules.questions.entities.AlternativesEntity;
import com.estudos.rocketseat.certificacaoNLW.modules.questions.entities.QuestionEntity;
import com.estudos.rocketseat.certificacaoNLW.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDto> findByTechnology(@PathVariable String technology) {
        System.out.println("TECH === " + technology);
        var result = this.questionRepository.findByTechnology(technology);

        return result.stream().map(question -> mapQuestionToDTO(question))
                .collect(Collectors.toList());
    }

    static QuestionResultDto mapQuestionToDTO(QuestionEntity question) {
        List<AlternativeResultDto> alternativesResultDTOs = question.getAlternatives()
                .stream().map(alternative -> mapAlternativeDTO(alternative))
                .collect(Collectors.toList());

        return new QuestionResultDto(question.getId(), question.getTechnology(), question.getDescription(), alternativesResultDTOs);
    }

    static AlternativeResultDto mapAlternativeDTO(AlternativesEntity alternativesResultDTO) {
        return new AlternativeResultDto(alternativesResultDTO.getId(), alternativesResultDTO.getDescription());
    }
}

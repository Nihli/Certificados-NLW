package com.estudos.rocketseat.certificacaoNLW.modules.students.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.rocketseat.certificacaoNLW.modules.questions.entities.QuestionEntity;
import com.estudos.rocketseat.certificacaoNLW.modules.questions.repositories.QuestionRepository;
import com.estudos.rocketseat.certificacaoNLW.modules.students.dto.StudentCertificationAnswerDto;

@Service
public class StudentCertificationAnswerUseCase {
    @Autowired
    private QuestionRepository questionRepository;

    public StudentCertificationAnswerDto execute(StudentCertificationAnswerDto dto) {
        List<QuestionEntity> questions = questionRepository.findByTechnology(dto.getTechnology());

        dto.getQuestionsAnswers()
                .stream().forEach(questionAnswer -> {
                    var questionEntity = questions.stream()
                            .filter(question -> question.getId().equals(questionAnswer.getQuestionID())).findFirst()
                            .get();
                    var findCorrectAlternative = questionEntity.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true);
                    } else {
                        questionAnswer.setCorrect(false);
                    }
                });

        return dto;
    }
}

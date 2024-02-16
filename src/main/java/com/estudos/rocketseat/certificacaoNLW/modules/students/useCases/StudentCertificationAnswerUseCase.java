package com.estudos.rocketseat.certificacaoNLW.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.rocketseat.certificacaoNLW.modules.questions.entities.QuestionEntity;
import com.estudos.rocketseat.certificacaoNLW.modules.questions.repositories.QuestionRepository;
import com.estudos.rocketseat.certificacaoNLW.modules.students.dto.StudentCertificationAnswerDto;
import com.estudos.rocketseat.certificacaoNLW.modules.students.entities.AnswersCertificationsEntity;
import com.estudos.rocketseat.certificacaoNLW.modules.students.entities.CertificationStudentEntity;
import com.estudos.rocketseat.certificacaoNLW.modules.students.entities.StudentEntity;
import com.estudos.rocketseat.certificacaoNLW.modules.students.repositories.CertificationStudentRepository;
import com.estudos.rocketseat.certificacaoNLW.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswerUseCase {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public CertificationStudentEntity execute(StudentCertificationAnswerDto dto) {
        List<QuestionEntity> questions = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers()
                .stream().forEach(questionAnswer -> {
                    var questionEntity = questions.stream()
                            .filter(question -> question.getId().equals(questionAnswer.getQuestionID())).findFirst()
                            .get();
                    var findCorrectAlternative = questionEntity.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true);
                        correctAnswers.incrementAndGet();
                    } else {
                        questionAnswer.setCorrect(false);
                    }

                    answersCertifications.add(
                            AnswersCertificationsEntity.builder()
                                    .answerID(questionAnswer.getAlternativeID())
                                    .questionID(questionAnswer.getQuestionID())
                                    .isCorrect(questionAnswer.isCorrect())
                                    .build());
                });

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get(0).getId();
        }

        var certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .studentID(studentID)
                .grade(correctAnswers.get())
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answerCertification -> {
            answerCertification.setCertificationID(certificationStudentCreated.getId());
            answerCertification.setCertificationStudentEntity(certificationStudentCreated);
        });

        certificationStudentCreated.setAnswersCertificationsEntity(answersCertifications);
        certificationStudentRepository.save(certificationStudentCreated);

        return certificationStudentCreated;
    }
}

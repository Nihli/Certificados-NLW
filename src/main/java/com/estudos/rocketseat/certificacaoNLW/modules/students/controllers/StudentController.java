package com.estudos.rocketseat.certificacaoNLW.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.rocketseat.certificacaoNLW.modules.students.dto.StudentCertificationAnswerDto;
import com.estudos.rocketseat.certificacaoNLW.modules.students.dto.VerifyHasCertificationDTO;
import com.estudos.rocketseat.certificacaoNLW.modules.students.useCases.StudentCertificationAnswerUseCase;
import com.estudos.rocketseat.certificacaoNLW.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswerUseCase studentCertificationAnswerUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        System.out.println(verifyHasCertificationDTO);
        var result = this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);

        if (result) {
            return "Usuario ja fez a prova";
        }
        return "Usuario pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> CertificationAnswer(@RequestBody StudentCertificationAnswerDto dto) {
        try {
            return ResponseEntity.ok().body(this.studentCertificationAnswerUseCase.execute(dto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

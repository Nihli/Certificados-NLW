package com.estudos.rocketseat.certificacaoNLW.modules.certifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.rocketseat.certificacaoNLW.modules.certifications.useCases.Top10RankingUseCases;
import com.estudos.rocketseat.certificacaoNLW.modules.students.entities.CertificationStudentEntity;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10RankingUseCases top10RankingUseCases;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return this.top10RankingUseCases.execute();
    }
}

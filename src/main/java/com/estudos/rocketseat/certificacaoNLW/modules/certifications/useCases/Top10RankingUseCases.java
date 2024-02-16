package com.estudos.rocketseat.certificacaoNLW.modules.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.rocketseat.certificacaoNLW.modules.students.entities.CertificationStudentEntity;
import com.estudos.rocketseat.certificacaoNLW.modules.students.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCases {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationStudentEntity> execute() {
        return this.certificationStudentRepository.findByOrderByGradeDesc();
    }
}

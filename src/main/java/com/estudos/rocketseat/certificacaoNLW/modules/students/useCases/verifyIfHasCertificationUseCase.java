package com.estudos.rocketseat.certificacaoNLW.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.rocketseat.certificacaoNLW.modules.students.dto.VerifyHasCertificationDTO;
import com.estudos.rocketseat.certificacaoNLW.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUseCase {

    @Autowired
    private CertificationStudentRepository repository;

    public boolean execute(VerifyHasCertificationDTO dto) {
        var result = this.repository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());

        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}

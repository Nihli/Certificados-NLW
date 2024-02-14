package com.estudos.rocketseat.certificacaoNLW.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.estudos.rocketseat.certificacaoNLW.modules.students.dto.VerifyHasCertificationDTO;

@Service
public class verifyIfHasCertificationUseCase {
    public boolean execute(VerifyHasCertificationDTO dto) {
        if (dto.getEmail().equals("lia.alflen@gmail.com") && dto.getTechnology().equals("Java")) {
            return true;
        }
        return false;
    }
}

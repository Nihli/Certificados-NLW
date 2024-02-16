package com.estudos.rocketseat.certificacaoNLW.modules.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudos.rocketseat.certificacaoNLW.modules.students.entities.StudentEntity;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    public Optional<List<StudentEntity>> findByEmail(String email);
}

package com.example.sisvita.api.documenttype.infrastructure;

import com.example.sisvita.api.documenttype.domain.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaDocumentTypeRepository extends JpaRepository<DocumentType, Integer>{
    Optional<DocumentType> findByType(String type);
}

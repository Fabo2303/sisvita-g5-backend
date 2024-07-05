package com.example.sisvita.api.documenttype.domain;

import com.example.sisvita.api.documenttype.infrastructure.JpaDocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {
    private final JpaDocumentTypeRepository documentTypeRepository;

    @Autowired
    public DocumentTypeService(JpaDocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    public DocumentType findById(Integer id) {
        return documentTypeRepository.findById(id).orElse(null);
    }

    public DocumentType findByType(String type) {
        return documentTypeRepository.findByType(type).orElse(null);
    }
}

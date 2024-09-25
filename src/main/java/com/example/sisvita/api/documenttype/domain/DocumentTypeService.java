package com.example.sisvita.api.documenttype.domain;

import com.example.sisvita.api.documenttype.infrastructure.DocumentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public Boolean save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    public DocumentType findById(Integer id) {
        return documentTypeRepository.findById(id);
    }

    public DocumentType findByType(String type) {
        return documentTypeRepository.findByType(type);
    }
}

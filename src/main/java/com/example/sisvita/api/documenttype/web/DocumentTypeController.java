package com.example.sisvita.api.documenttype.web;

import com.example.sisvita.api.documenttype.domain.DocumentType;
import com.example.sisvita.api.documenttype.domain.DocumentTypeService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_DOCUMENT_TYPE)
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody DocumentType documentType) {
        Boolean savedDocumentType = documentTypeService.save(documentType);
        if (!savedDocumentType) {
            throw new CustomException("Document type not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Document type saved");
    }

    @GetMapping()
    public ResponseEntity<List<DocumentType>> findAll() {
        List<DocumentType> documentTypes = documentTypeService.findAll();
        if (documentTypes.isEmpty()) {
            throw new CustomException("No document types found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(documentTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentType> findById(@PathVariable Integer id) {
        DocumentType documentType = documentTypeService.findById(id);
        if (documentType == null) {
            throw new CustomException("Document type not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(documentType);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<DocumentType> findByType(@PathVariable String type) {
        DocumentType documentType = documentTypeService.findByType(type);
        if (documentType == null) {
            throw new CustomException("Document type not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(documentType);
    }
}

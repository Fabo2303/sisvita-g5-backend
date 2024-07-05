package com.example.sisvita.api.documenttype.web;

import com.example.sisvita.api.documenttype.domain.DocumentType;
import com.example.sisvita.api.documenttype.domain.DocumentTypeService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-type")
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping()
    public ResponseEntity<?> createDocumentType(@RequestBody DocumentType documentType) {
        DocumentType savedDocumentType = documentTypeService.save(documentType);
        return ResponseEntity.ok(savedDocumentType);
    }

    @GetMapping()
    public ResponseEntity<?> getAllDocumentTypes() {
        List<DocumentType> documentTypes = documentTypeService.findAll();
        if (documentTypes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Document types not found")
                    .build());
        }
        return ResponseEntity.ok(documentTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDocumentTypeById(@PathVariable Integer id) {
        DocumentType documentType = documentTypeService.findById(id);
        if (documentType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Document type not found")
                    .build());
        }
        return ResponseEntity.ok(documentType);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getDocumentTypeByType(@PathVariable String type) {
        DocumentType documentType = documentTypeService.findByType(type);
        if (documentType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Document type not found")
                    .build());
        }
        return ResponseEntity.ok(documentType);
    }
}

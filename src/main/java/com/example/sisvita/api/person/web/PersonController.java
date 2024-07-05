package com.example.sisvita.api.person.web;

import com.example.sisvita.api.person.domain.Person;
import com.example.sisvita.api.person.domain.PersonService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;
    
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    @GetMapping()
    public ResponseEntity<?> getAllPersons() {
        List<Person> persons = personService.findAll();
        if (persons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persons not found").build());
        }
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{document}")
    public ResponseEntity<?> getPersonByDocument(@PathVariable String document) {
        Person person = personService.findByDocument(document);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Person not found").build());
        }
        return ResponseEntity.ok(person);
    }
}

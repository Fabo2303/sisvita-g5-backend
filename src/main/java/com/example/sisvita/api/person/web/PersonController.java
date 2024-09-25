package com.example.sisvita.api.person.web;

import com.example.sisvita.api.person.domain.Person;
import com.example.sisvita.api.person.domain.PersonService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_PERSON)
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.findAll();
        if (persons.isEmpty()) {
            throw new CustomException("No persons found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{document}")
    public ResponseEntity<Person> getPersonByDocument(@PathVariable String document) {
        Person person = personService.findByDocument(document);
        if (person == null) {
            throw new CustomException("Person not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(person);
    }

    @PostMapping()
    public ResponseEntity<String> savePerson(@RequestBody Person person) {
        Boolean savedPerson = personService.savePerson(person);
        if (!savedPerson) {
            throw new CustomException("Person not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Person saved");
    }
}

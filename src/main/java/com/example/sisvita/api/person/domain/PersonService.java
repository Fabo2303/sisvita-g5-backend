package com.example.sisvita.api.person.domain;

import com.example.sisvita.api.person.infrastructure.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    public final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Boolean savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person findByDocument(String document) {
        return personRepository.findByDocument(document);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}

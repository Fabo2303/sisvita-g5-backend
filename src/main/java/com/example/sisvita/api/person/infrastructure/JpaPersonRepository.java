package com.example.sisvita.api.person.infrastructure;

import com.example.sisvita.api.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPersonRepository extends JpaRepository<Person, String>{

}

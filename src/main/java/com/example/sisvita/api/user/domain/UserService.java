package com.example.sisvita.api.user.domain;

import com.example.sisvita.api.person.domain.Person;
import com.example.sisvita.api.person.domain.PersonService;
import com.example.sisvita.api.person.dto.PersonRequest;
import com.example.sisvita.api.user.dto.UserRequest;
import com.example.sisvita.api.user.infrastructure.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final JpaUserRepository jpaUserRepository;
    private final PersonService personService;

    @Autowired
    public UserService(
            JpaUserRepository jpaUserRepository,
            PersonService personService
    ) {
        this.jpaUserRepository = jpaUserRepository;
        this.personService = personService;
    }

    public User saveUser(UserRequest userRequest) {
        PersonRequest personRequest = userRequest.getPersonRequest();
        User user = UserRequest.toEntity(userRequest);
        if (personRequest != null) {
            Person persona = personService.savePerson(personRequest);
            user.setPerson(persona);
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return jpaUserRepository.save(user);
    }

    public User findByUsername(String username) {
        return jpaUserRepository.findByUsername(username).orElse(null);
    }

    public User findById(Integer id) {
        return jpaUserRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }
}

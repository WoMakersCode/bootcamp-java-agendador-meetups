package com.bootcamp.microservicemeetup.repository;

import com.bootcamp.microservicemeetup.model.entity.Registration;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class RegistrationRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    RegistrationRepository repository;

    @Test
    @DisplayName("Should get an registration by id")
    public void findByIdTest() {

        Registration registration_Class_attribute = createNewRegistration();
        entityManager.persist(registration_Class_attribute);

        Optional<Registration> foundRegistration = repository
                .findById(registration_Class_attribute.getId());

        assertThat(foundRegistration.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should save an registration")
    public void saveRegistrationTest() {

        Registration registration_Class_attribute = createNewRegistration();

        Registration savedRegistration = repository.save(registration_Class_attribute);

        assertThat(savedRegistration.getId()).isNotNull();

    }

    @Test
    @DisplayName("Should delete and registration from the base")
    public void deleteRegistation() {

        Registration registration_Class_attribute = createNewRegistration();
        entityManager.persist(registration_Class_attribute);

        Registration foundRegistration = entityManager
                .find(Registration.class, registration_Class_attribute.getId());
        repository.delete(foundRegistration);

        Registration deleteRegistration = entityManager
                .find(Registration.class, registration_Class_attribute.getId());

        assertThat(deleteRegistration).isNull();

    }

    public static Registration createNewRegistration() {
        return Registration.builder()
                .name("Ana Neri")
                .dateOfRegistration("10/10/2021")
                .build();
    }
}

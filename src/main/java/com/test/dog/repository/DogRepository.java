package com.test.dog.repository;

import com.test.dog.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DogRepository extends JpaRepository<Dog,Long>, JpaSpecificationExecutor<Dog> {
}

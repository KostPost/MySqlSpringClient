package com.kostpost.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    //List<Client> findByFirstName = new ArrayList<>();

    List<Client> findByFirstName(String FirstName);
    List<Client> findBySecondName(String FirstName);
}

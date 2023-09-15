package com.kostpost.client;

import com.kostpost.bank.BankAcc;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Entity
@SpringBootApplication
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(name = "first_name")
    public String firstName;
    public String secondName;

}

package com.kostpost.bank;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Getter
@Setter
@Entity
@SpringBootApplication
@Table(name = "bankacc")
public class BankAcc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public double balance;
}



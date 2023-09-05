package com.kostpost.bank;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@Setter
@Entity
@SpringBootApplication
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public double AllMoney;
}

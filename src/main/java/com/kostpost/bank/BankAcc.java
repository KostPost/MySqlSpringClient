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

    public BankAcc CreateBankAcc(double balance)
    {
        BankAcc createdAcc = new BankAcc();
        this.balance = balance;
        createdAcc.setBalance(balance);

        return createdAcc;
    }

    public void print()
    {
        System.out.println("\n");
        System.out.println("ID - " + id);
        System.out.println("Balance - " + balance);
        System.out.println("\n");
    }


}



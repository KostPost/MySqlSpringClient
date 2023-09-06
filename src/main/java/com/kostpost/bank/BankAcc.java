package com.kostpost.bank;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@Setter
@Entity
@SpringBootApplication
@Table(name = "bankacc")
public class BankAcc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public double AllMoney;

    public BankAcc CreateBankAcc(double AllMoney)
    {
        BankAcc createdAcc = new BankAcc();
        this.AllMoney = AllMoney;
        createdAcc.setAllMoney(AllMoney);

        return createdAcc;
    }
}

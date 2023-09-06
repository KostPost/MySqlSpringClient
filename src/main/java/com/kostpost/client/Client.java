package com.kostpost.client;

import com.kostpost.bank.BankAcc;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@Setter
@Entity
@SpringBootApplication
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String FirstName;
    public String SecondName;

    public Client CreateClient(String FirstName, String SecondName)
    {
        Client createdAcc = new Client();
        this.FirstName = FirstName;
        this.SecondName = SecondName;

        createdAcc.setFirstName(FirstName);
        createdAcc.setSecondName(SecondName);

        return createdAcc;
    }

}

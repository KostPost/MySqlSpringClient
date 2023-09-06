package com.kostpost.mysqlspringclient;

import com.kostpost.bank.BankAcc;
import com.kostpost.bank.BankRepository;
import com.kostpost.client.Client;
import com.kostpost.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class MainController {

    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;

    @Autowired
    public MainController(ClientRepository clientRepository, BankRepository bankRepository) {
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
    }

    public void ShowAllClients(String nameClass)
    {
        switch (nameClass)
        {
            case "client" -> {
                List<Client> clients = clientRepository.findAll();

                for(Client client : clients)
                {
                    System.out.println("\n");
                    System.out.println("User ID: " + client.getId());
                    System.out.println("FirstName: " + client.getFirstName());
                    System.out.println("SecondName: " + client.getSecondName());
                    System.out.println("\n");
                }
            }

            case "bankacc" -> {
                List<BankAcc> bankAccs = bankRepository.findAll();

                for(BankAcc bankAcc : bankAccs)
                {
                    System.out.println("\n");
                    System.out.println("Bank account ID:" +  bankAcc.getId());
                    System.out.println("Bank account money: " + bankAcc.getAllMoney());
                    System.out.println("\n");
                }
            }

        }

        List<Client> clients = clientRepository.findAll();



    }

    @PostMapping("/add-data")
    public Client addDataClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PostMapping("/add-data-Bank")
    public BankAcc addDataBank(@RequestBody BankAcc bankAcc) {
        return bankRepository.save(bankAcc);
    }



}

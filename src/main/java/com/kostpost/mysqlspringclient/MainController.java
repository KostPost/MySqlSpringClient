package com.kostpost.mysqlspringclient;

import com.kostpost.bank.BankAcc;
import com.kostpost.bank.BankRepository;
import com.kostpost.client.Client;
import com.kostpost.client.ClientRepository;
import com.kostpost.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
@ComponentScan(basePackages = {"com.kostpost.showtable",})
@ComponentScan(basePackages = {"com.kostpost.client",})
@ComponentScan(basePackages = {"com.kostpost.bank",})
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
                    ClientPrint(client);
                }
            }

            case "bankacc" -> {
                List<BankAcc> bankAccs = bankRepository.findAll();

                for(BankAcc bankAcc : bankAccs)
                {
                    BankPrint(bankAcc);
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

    public BankAcc CreateBankAcc(double balance)
    {
        BankAcc createdAcc = new BankAcc();
        createdAcc.balance = balance;
        createdAcc.setBalance(balance);

        return createdAcc;
    }

    public Client CreateClient(String FirstName, String SecondName)
    {
        Client createdAcc = new Client();
        createdAcc.firstName = FirstName;
        createdAcc.SecondName = SecondName;

        createdAcc.setFirstName(FirstName);
        createdAcc.setSecondName(SecondName);

        return createdAcc;
    }

    public void BankPrint(BankAcc accPrint)
    {
        System.out.println("\n");
        System.out.println("ID - " + accPrint.id);
        System.out.println("Balance - " + accPrint.balance);
        System.out.println("\n");
    }

    public void ClientPrint(Client clientPrint)
    {
        System.out.println("\n");
        System.out.println("ID - " + clientPrint.id);
        System.out.println("First Name - " + clientPrint.firstName);
        System.out.println("Second Name - " + clientPrint.SecondName);
        System.out.println("\n");
    }

    public Client ClientFindByID(int id)
    {
        return clientRepository.findById(id).orElse(null);
    }

    @GetMapping("/find-by-firstname")
    public List<Client> findByFirstName(@RequestParam String FirstName) {
        return clientRepository.findByFirstName(FirstName);
    }

//    @GetMapping("/find-by-secondname")
//    public List<Client> findBySecondName(@RequestParam String FirstName) {
//        return clientRepository.findByFirstName(FirstName);
//    }

    public BankAcc BankAccFindByID(int id)
    {
        return bankRepository.findById(id).orElse(null);
    }



    public void FindBy(String databaseName, ArrayList<String> Tables, MainController controller)
    {
        Scanner AskChoiceTable = new Scanner(System.in);
        String  choiceTable;

        MySqlSpringClientApplication.ShowAllTables(databaseName, Tables);
        choiceTable = AskChoiceTable.next();

        System.out.println(Tables.get(Integer.parseInt(choiceTable)));

        Scanner AskFindAccount = new Scanner(System.in);
        String FindAccount;

        switch (Tables.get(Integer.parseInt(choiceTable))){

            case "bankacc" -> {

                System.out.println("Find by: \n1 - Find by ID");
                FindAccount = AskFindAccount.next();

                switch (FindAccount){

                    case "1" -> {
                        Scanner FindById = new Scanner(System.in);
                        int id;

                        System.out.println("Enter ID");
                        id = FindById.nextInt();

                        BankAcc bankAcc = controller.BankAccFindByID(id);

                        if(bankAcc != null) controller.BankPrint(bankAcc);
                        else System.out.println("This account doesn't exist");
                    }
                }
            }

            case "client" -> {
                System.out.println("Find by: \n1 - Find by ID\n2 - Find by First Name\n3 - Find by Second Name");
                FindAccount = AskFindAccount.next();

                switch (FindAccount){

                    case "1" -> {
                        Scanner FindById = new Scanner(System.in);
                        int id;

                        System.out.println("Enter ID");
                        id = FindById.nextInt();

                        Client client = controller.ClientFindByID(id);

                        if(client != null) controller.ClientPrint(client);
                        else System.out.println("This account doesn't exist");
                    }

                    case "2" -> {
                        Scanner FindByFirstName = new Scanner(System.in);
                        String FirstName;

                        System.out.println("Enter First Name");
                        FirstName = FindByFirstName.next();

//                        List<Client> clients = controller.findByFirstName(FirstName);
//
//                        if(clients != null) {
//
//                            for(Client client : clients)
//                            {
//                                controller.ClientPrint(client);
//                            }
//                        }
//                        else System.out.println("This account doesn't exist");
                    }

                    case "3" -> {
                        Scanner FindBySecondName = new Scanner(System.in);
                        String SecondName;


                    }



                }
            }

        }

    }


}

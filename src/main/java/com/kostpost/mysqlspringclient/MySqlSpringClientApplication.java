package com.kostpost.mysqlspringclient;

import com.kostpost.bank.BankAcc;
import com.kostpost.client.Client;
import com.kostpost.client.ClientRepository;
import com.kostpost.showtable.TableLister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kostpost.showtable",})
@ComponentScan(basePackages = {"com.kostpost.client",})
@ComponentScan(basePackages = {"com.kostpost.bank",})
public class MySqlSpringClientApplication {

    public static void main(String[] args) {


        ConfigurableApplicationContext context = SpringApplication.run(MySqlSpringClientApplication.class, args);
        TableLister tableLister = context.getBean(TableLister.class);
        String databaseName = "clientspring";

        ArrayList<String> Tables; // = new ArrayList<>();
        Tables = tableLister.listAllTablesInDatabase(databaseName);

        Scanner AskChoiceAction = new Scanner(System.in);
        String action;

        Scanner AskChoiceTable = new Scanner(System.in);
        String  choiceTable;

        boolean working = true;

        MainController controller = context.getBean(MainController.class);

        do {

            System.out.println("Choice action\n1 - See all tables\n2 - See dates in table\n3 - Add dates in Table\n" +
                    "4 - Find Account\n");
            action = AskChoiceAction.next();


            switch (action) {
                case "1" -> //Show all tables
                        MySqlSpringClientApplication.ShowAllTables(databaseName, Tables);

                case "2" -> {
                    MySqlSpringClientApplication.ShowAllTables(databaseName, Tables);
                    choiceTable = AskChoiceTable.next();

                    //System.out.println(Tables.get(Integer.parseInt(choiceTable)));

                    controller.ShowAllClients(Tables.get(Integer.parseInt(choiceTable)));
                }

                case "3" ->{
                    MySqlSpringClientApplication.ShowAllTables(databaseName, Tables);
                    choiceTable = AskChoiceTable.next();

                    //System.out.println(Tables.get(Integer.parseInt(choiceTable)));

                    switch (Tables.get(Integer.parseInt(choiceTable)))
                    {
                        case "client" -> {
                            Scanner AskFirstName = new Scanner(System.in);
                            String FirstName;

                            Scanner AskSecondName = new Scanner(System.in);
                            String SecondName;

                            System.out.print("Set First Name: ");
                            FirstName = AskFirstName.next();

                            System.out.print("Set Second Name: ");
                            SecondName = AskSecondName.next();

                            Client createdClient = new Client();
                            createdClient = controller.CreateClient(FirstName,SecondName);

                            controller.addDataClient(createdClient);
                        }

                        case "bankacc" -> {
                            Scanner AskMoney = new Scanner(System.in);
                            double money;

                            System.out.print("Set account balance: ");
                            money = AskMoney.nextDouble();

                            BankAcc createdBankAcc = new BankAcc();
                            createdBankAcc = controller.CreateBankAcc(money);

                            controller.addDataBank(createdBankAcc);
                        }
                    }

                }

                case "4" ->{

                    //System.out.println(Tables.get(Integer.parseInt(choiceTable)));

                    System.out.println("Choice a table");

                    MySqlSpringClientApplication.ShowAllTables(databaseName, Tables);
                    choiceTable = AskChoiceTable.next();

                    Scanner FindById = new Scanner(System.in);
                    int id;

                    switch (Tables.get(Integer.parseInt(choiceTable))){

                        case "client" -> {

                            Scanner FindBy = new Scanner(System.in);
                            int findBy;

                            System.out.println("Find by:\n1 - by ID\n2 - by FirstName\n3 - by SecondName");
                            findBy = FindBy.nextInt();

                            switch (findBy){

                                case 1 -> {
                                    System.out.print("Write a Account ID to find: ");
                                    id = FindById.nextInt();

                                    Client clientAcc = controller.ClientFindByID(id);

                                    if(clientAcc != null) controller.ClientPrint(clientAcc);
                                    else System.out.println("This account doesn't exist");
                                }

                                case 2 -> {
                                    Scanner AskFistName = new Scanner((System.in));
                                    String FirstName;

                                    System.out.print("Write a name:\t");
                                    FirstName = AskFistName.next();

                                    List<Client> client = controller.findByFirstName(FirstName);

                                    for(Client c : client){
                                        controller.ClientPrint(c);
                                    }
                                }

                                case 3 -> {

                                }

                            }


                        }

                        case "bankacc" -> {
                            System.out.print("Write a Account ID to find: ");
                            id = FindById.nextInt();

                            BankAcc bankAcc = controller.BankAccFindByID(id);

                            if(bankAcc != null) controller.BankPrint(bankAcc);
                            else System.out.println("This account doesn't exist");

                        }
                    }

                }

                case "123" -> {

                    controller.FindBy(databaseName,Tables,controller);

                }

                case "6" ->
                    working = false;
            }

        }while(working);





        context.close();
        SpringApplication.run(MySqlSpringClientApplication.class, args);

    }

    public static void ShowAllTables(String databaseName, ArrayList<String> Tables)
    {
        for(int i = 0; i < Tables.size(); i++){
            System.out.println("Table " + i + " - " + Tables.get(i));
        }
        System.out.println("\n");
    }

}

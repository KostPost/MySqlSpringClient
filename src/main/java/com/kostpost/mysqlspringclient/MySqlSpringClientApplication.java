package com.kostpost.mysqlspringclient;

import com.kostpost.showtable.TableLister;

import com.kostpost.client.ClientRepository;
import com.kostpost.client.Client;

import com.kostpost.bank.Bank;
import com.kostpost.bank.BankRepository;

import jakarta.persistence.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
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

        ArrayList<String> Tables = new ArrayList<>();
        Tables = tableLister.listAllTablesInDatabase(databaseName);

        Scanner AskChoiceAction = new Scanner(System.in);
        String action;

        Scanner AskChoiceTable = new Scanner(System.in);
        String  choiceTable;

        boolean working = true;

        do {

            System.out.println("Choice action\n1 - See all tables\n2 - See dates in table");
            action = AskChoiceAction.next();


            switch (action) {
                case "1" -> //Show all tables
                        MySqlSpringClientApplication.ShowAllTables(databaseName, Tables);
                case "2" -> {
                    MySqlSpringClientApplication.ShowAllTables(databaseName, Tables);
                    choiceTable = AskChoiceTable.next();

                    System.out.println(Tables.get(Integer.parseInt(choiceTable)));
                }

                case "3" ->
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

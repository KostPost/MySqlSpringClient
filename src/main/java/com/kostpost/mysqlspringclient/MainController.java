package com.kostpost.mysqlspringclient;

import com.kostpost.client.Client;
import com.kostpost.client.ClientRepository;
import com.kostpost.showtable.TableLister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class MainController {

    private final ClientRepository clientRepository;

    @Autowired
    public MainController(ClientRepository clientRepository, TableLister mainService) {
        this.clientRepository = clientRepository;
    }

    public void ShowAllClients(Class<>) ////////////////////
    {
        List<Client> clients = clientRepository.findAll();

        for(Client client : clients)
        {
            System.out.println("User ID: " + client.getId());
            System.out.println("FirstName: " + client.getFirstName());
            System.out.println("SecondName: " + client.getSecondName());
        }

    }

    @PostMapping("/add-data")
    public Client addData(@RequestBody Client client) {
        return clientRepository.save(client);
    }



}

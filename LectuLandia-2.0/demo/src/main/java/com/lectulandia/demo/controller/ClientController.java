package com.lectulandia.demo.controller;

import com.lectulandia.demo.entity.ClientData;
import com.lectulandia.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index() {
        return "CONNECTED";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSaveClient(@RequestBody ClientData client) {
        ClientData saveClient = clientService.getClientCreated(client);

        try {
            return ResponseEntity.created(URI.create("")).body(saveClient);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping(value = "/client/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getClient(@PathVariable(name = "id") Long id) {
        Optional<ClientData> client = clientService.getClientDataById(id);

        try {
            return ResponseEntity.ok(client.get());

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all_clients", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllClients() {
        List<ClientData> client = clientService.getAllClientsData();

        try {
            return ResponseEntity.ok(client);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/modified/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClientData update(@PathVariable Long id, @PathVariable ClientData client) {
        try {
            return clientService.getClientUpdated(id, client);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return null;
        }
    }

    @DeleteMapping(value = "/low/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String delete(@PathVariable Long id) {
        try {
            return clientService.getClientDelete(id);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}
package com.g31.jpa.service;

import com.g31.jpa.entity.Client;
import com.g31.jpa.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author desaextremo
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    //Metodo para consultar todos los registros (Capa de servicios)
    public List<Client> getClient() {
        return clientRepository.findAll();
    }

    //Metodo para insertar (Capa de servicios)
    public Client insertClient(Client client) {
        return clientRepository.save(client);
    }
    
    //Metodo para eliminar (Capa de servicios)
    public void deleteClient(Long id){
       clientRepository.deleteById(id);               
    }
    
    //Metodo para actualizar (Capa de servicios)
    public Client updateClient(Client client){
    //la farm existe
    if (client.getIdClient()!=null){
            //validamos si la farm existe
            Optional<Client> opcional =  clientRepository.findById(client.getIdClient());

            //la farm no existe
            if (opcional.isEmpty()) return client;
            //si la farm existe
            else{
               Client clientDB = opcional.get();
               
               clientDB.setAge(client.getAge());
               clientDB.setEmail(client.getEmail());
               clientDB.setName(client.getName());
               clientDB.setPassword(client.getPassword());
               
               return clientRepository.save(clientDB);
            }

    }
    return client;
    }
}

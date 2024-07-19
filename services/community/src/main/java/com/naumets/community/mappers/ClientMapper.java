package com.naumets.community.mappers;


import com.naumets.community.dtos.ClientDTO;
import com.naumets.community.models.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getAddress(),
                client.getCity(),
                client.getPhone(),
                client.getMobile(),
                client.getWebsite(),
                client.getEmail(),
                client.getCountryid(),
                client.getStateid(),
                client.getDetails()
        );
    }

    public static Client toEntity(ClientDTO clientDTO) {
        return new Client(
                clientDTO.getId(),
                clientDTO.getName(),
                clientDTO.getAddress(),
                clientDTO.getCity(),
                clientDTO.getPhone(),
                clientDTO.getMobile(),
                clientDTO.getWebsite(),
                clientDTO.getEmail(),
                clientDTO.getCountryid(),
                clientDTO.getStateid(),
                clientDTO.getDetails()
        );
    }
}

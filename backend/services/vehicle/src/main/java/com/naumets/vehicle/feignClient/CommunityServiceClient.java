package com.naumets.vehicle.feignClient;

import com.naumets.vehicle.dtos.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "community-service")
public interface CommunityServiceClient {

    @GetMapping("api/community/clients/findById/{id}")
    ClientDTO getClientDTOById(@PathVariable("id") String id);
}


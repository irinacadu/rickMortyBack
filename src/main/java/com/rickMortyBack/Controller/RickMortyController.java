package com.rickMortyBack.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rickMortyBack.Entities.RickMortyCharacter;
import com.rickMortyBack.Services.services.RickMortyServiceApi;
import com.rickMortyBack.Services.servicesImpl.RickMortyServiceApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rick-morty-characters")
@CrossOrigin("*")
public class RickMortyController {

    private final RickMortyServiceApiImpl rickMortyServiceApi;

    public RickMortyController(RickMortyServiceApiImpl rickMortyServiceApi) {
        this.rickMortyServiceApi = rickMortyServiceApi;
    }

    @GetMapping
    public List<RickMortyCharacter> getAllCharacters() throws IOException {
        return rickMortyServiceApi.getAllCharacters();
    }


}

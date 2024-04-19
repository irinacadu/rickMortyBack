package com.rickMortyBack.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rickMortyBack.Entities.RickMortyCharacter;
import com.rickMortyBack.Services.servicesImpl.RickMortyServiceApiImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rick-morty-characters")
public class RickMortyController {

    private final RickMortyServiceApiImpl rickMortyServiceApi;

    public RickMortyController(RickMortyServiceApiImpl rickMortyServiceApi) {
        this.rickMortyServiceApi = rickMortyServiceApi;
    }

    @GetMapping
    public List<RickMortyCharacter> getAllCharacters() throws JsonProcessingException {
        return rickMortyServiceApi.getAllCharacters();
    }


}

package com.rickMortyBack.Services.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rickMortyBack.Entities.RickMortyCharacter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RickMortyServiceApi {
    List<RickMortyCharacter> getAllCharacters() throws JsonProcessingException;
    void saveRickMortyCharacter(RickMortyCharacter rickMortyCharacter);
    void deleteRickMortyCharacter (Long characterId);
    RickMortyCharacter updateRickMortyCharacter(Long id);

    RickMortyCharacter getCharacterById(Long characterId) throws JsonProcessingException;
}

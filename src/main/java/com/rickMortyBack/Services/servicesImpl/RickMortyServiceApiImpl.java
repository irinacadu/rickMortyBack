package com.rickMortyBack.Services.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rickMortyBack.Entities.RickMortyCharacter;
import com.rickMortyBack.Services.services.RickMortyServiceApi;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RickMortyServiceApiImpl implements RickMortyServiceApi {
    private final RestTemplate restTemplate;

    public RickMortyServiceApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RickMortyCharacter> getAllCharacters() throws JsonProcessingException {

        String jsonResponse = restTemplate.getForObject("https://rickandmortyapi.com/api/character", String.class);
        List<RickMortyCharacter> productos = parseJson(jsonResponse);
        return productos;
    }

    public void saveRickMortyCharacter(RickMortyCharacter rickMortyCharacter) {
      //  rickMortyRepository.save(rickMortyCharacter);
    }

    @Override
    public void deleteRickMortyCharacter(Long id) {
      //  rickMortyRepository.deleteById(id);
    }


    @Override
    public RickMortyCharacter updateRickMortyCharacter(Long id) {
        return null;
    }


    public List<RickMortyCharacter> parseJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readValue(json, JsonNode.class);

        // Extraer solo los campos deseados
        List<RickMortyCharacter> characters = new ArrayList<>();
        for (JsonNode characterNode : rootNode.path("results")) {
            Long id = characterNode.path("id").asLong();
            String nameCharacter = characterNode.path("name").asText();
            String typeCharacter = characterNode.path("type").asText();
            String dateCreationCharacter = characterNode.path("created").asText();
            int numberEpisode = characterNode.path("episode").asInt();

            // Crear un objeto RickMortyCharacter con los datos extraídos
            RickMortyCharacter character = new RickMortyCharacter(id, nameCharacter, typeCharacter, dateCreationCharacter,numberEpisode);
            characters.add(character);
        }

        return characters;
    }


}

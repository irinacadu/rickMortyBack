package com.rickMortyBack.Services.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rickMortyBack.Entities.RickMortyCharacter;
import com.rickMortyBack.Services.services.RickMortyServiceApi;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RickMortyServiceApiImpl implements RickMortyServiceApi {
    private final RestTemplate restTemplate;

    public RickMortyServiceApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RickMortyCharacter> getAllCharacters() throws JsonProcessingException {
        String rickMortyUrl = "https://rickandmortyapi.com/api/character";
        List<RickMortyCharacter> allCharacters = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        int page = 1;

        while (true) {
            String url = UriComponentsBuilder.fromUriString(rickMortyUrl)
                    .queryParam("page", page)
                    .toUriString();

            String jsonResponse = restTemplate.getForObject(url, String.class);
            JsonNode responseNode = objectMapper.readTree(jsonResponse);

            List<RickMortyCharacter> characters = parseJson(responseNode.toString());
            if (characters != null && !characters.isEmpty()) {
                allCharacters.addAll(characters);
            } else {
                break;
            }

            JsonNode infoNode = responseNode.get("info");
            if (infoNode == null || infoNode.get("next").isNull()) {
                break;
            }

            page++;
        }

        return allCharacters;
    }
    @Override
    public RickMortyCharacter getCharacterById(Long characterId) throws JsonProcessingException {
       List<RickMortyCharacter> characters = this.getAllCharacters();
        return characters.stream()
                .filter(character -> Objects.equals(character.getId(), characterId))
                .findFirst()
                .orElse(null);

    }

    public List<RickMortyCharacter> parseJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readValue(json, JsonNode.class);

        List<RickMortyCharacter> characters = new ArrayList<>();
        for (JsonNode characterNode : rootNode.path("results")) {
            long id = characterNode.path("id").asLong();
            String name = characterNode.path("name").asText();
            String imageUrl = characterNode.path("image").asText();
            String created = characterNode.path("created").asText();

            RickMortyCharacter character = new RickMortyCharacter(id, name, created, imageUrl);
            characters.add(character);
        }

        return characters;
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







}

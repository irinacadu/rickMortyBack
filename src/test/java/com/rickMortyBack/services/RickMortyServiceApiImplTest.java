package com.rickMortyBack.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rickMortyBack.Entities.RickMortyCharacter;
import com.rickMortyBack.Services.servicesImpl.RickMortyServiceApiImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RickMortyServiceApiImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RickMortyServiceApiImpl rickMortyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rickMortyService = new RickMortyServiceApiImpl(restTemplate);
    }
    @Test
    @DisplayName("Test para saber si se reciben los datos correctamente")
    public void testGetAllCharacters() throws JsonProcessingException {
        System.out.println("=========TEST-001 TODOS PERSONAJES==========" );
        String jsonResponse = "{\"results\": [{\"id\": 1, \"name\": \"Rick\", \"image\": \"rick.png\", \"created\": \"2024-04-21\"}]}";
        when(restTemplate.getForObject("https://rickandmortyapi.com/api/character?page=1", String.class)).thenReturn(jsonResponse);

        List<RickMortyCharacter> characters = rickMortyService.getAllCharacters();

        for (RickMortyCharacter character : characters) {
            System.out.println("Nombre del personaje: " + character.getName());
            System.out.println("URL de la imagen: " + character.getImageUrl());
            System.out.println("Fecha de creación: " + character.getCreated());
        }

        assertEquals(1, characters.size());
        assertEquals("Rick", characters.get(0).getName());
        assertEquals("rick.png", characters.get(0).getImageUrl());
        assertEquals("2024-04-21", characters.get(0).getCreated());
    }
    @Test
    @DisplayName("Test que comprueba si se recupera un personaje concreto")
    public void testGetCharacterById() throws JsonProcessingException {
        System.out.println("=========TEST-002 PERSONAJE POR ID==========" );
        String jsonResponse = "{\"id\": 1, \"name\": \"Rick\", \"image\": \"rick.png\", \"created\": \"2024-04-21\"}";
        when(restTemplate.getForObject("https://rickandmortyapi.com/api/character/1", String.class)).thenReturn(jsonResponse);

        RickMortyCharacter character = rickMortyService.getCharacterById(1L);

        System.out.println("Nombre del personaje: " + character.getName());
        System.out.println("URL de la imagen: " + character.getImageUrl());
        System.out.println("Fecha de creación: " + character.getCreated());

        assertEquals("Rick", character.getName());
        assertEquals("rick.png", character.getImageUrl());
        assertEquals("2024-04-21", character.getCreated());
    }

    @Test
    @DisplayName("Test que comprueba si los datos se parsean  correctamente desde una cadena de Json")
    public void testParseJson() throws JsonProcessingException {
        System.out.println("=========TEST-003 PARSE JSON==========" );
        String json = "{\"results\": [{\"id\": 1, \"name\": \"Rick Sanchez\", \"image\": \"https://rickandmortyapi.com/api/character/avatar/1.jpeg\", \"created\": \"2017-11-04T18:48:46.250Z\"}]}";
        List<RickMortyCharacter> characters = rickMortyService.parseJson(json);

        for (RickMortyCharacter character : characters) {
            System.out.println("Nombre del personaje: " + character.getName());
            System.out.println("URL de la imagen: " + character.getImageUrl());
            System.out.println("Fecha de creación: " + character.getCreated());
        }

        assertEquals(1, characters.size());
        assertEquals("Rick Sanchez", characters.get(0).getName());
        assertEquals("https://rickandmortyapi.com/api/character/avatar/1.jpeg", characters.get(0).getImageUrl());
        assertEquals("2017-11-04T18:48:46.250Z", characters.get(0).getCreated());
    }
    @Test
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
}

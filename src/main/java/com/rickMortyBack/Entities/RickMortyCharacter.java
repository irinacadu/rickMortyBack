package com.rickMortyBack.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RickMortyCharacter {


    Long id=0L;
    String nameCharacter;
    String typeCharacter;
    String dateCreationCharacter;
    int numberEpisode;
}

package com.treasure_hunt.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Puzzle {
    int id;
    String riddle;
    String clue;
}

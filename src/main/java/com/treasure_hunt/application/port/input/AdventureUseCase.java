package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.Adventure;

import java.util.List;

public interface AdventureUseCase {
    Adventure save(Adventure adventure);
    Adventure findById(int id);
    List<Adventure> findByAdventurerId(int id);
    Adventure update(int id, Adventure adventure);
}

package com.treasure_hunt.application.port.output;

import com.treasure_hunt.application.domain.Adventure;

import java.util.List;
import java.util.Optional;

public interface AdventurePort {
    Adventure save(Adventure adventure);
    Optional<Adventure> findById(int id);
    List<Adventure> findByAdventurerId(int id);
}

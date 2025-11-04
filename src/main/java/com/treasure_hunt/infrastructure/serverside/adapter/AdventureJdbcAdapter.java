package com.treasure_hunt.infrastructure.serverside.adapter;

import com.treasure_hunt.application.domain.Adventure;
import com.treasure_hunt.application.port.output.AdventurePort;
import com.treasure_hunt.infrastructure.serverside.entity.AdventureEntity;
import com.treasure_hunt.infrastructure.serverside.mapper.AdventureEntityMapper;
import com.treasure_hunt.infrastructure.serverside.repository.AdventureRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AdventureJdbcAdapter implements AdventurePort {
    private final AdventureRepository adventureRepository;
    private final AdventureEntityMapper adventureEntityMapper;

    public AdventureJdbcAdapter(AdventureRepository adventureRepository, AdventureEntityMapper adventureEntityMapper) {
        this.adventureRepository = adventureRepository;
        this.adventureEntityMapper = adventureEntityMapper;
    }

    @Override
    public Adventure save(Adventure adventure) {
        AdventureEntity entity = adventureEntityMapper.toAdventureEntity(adventure);
        AdventureEntity persisted = adventureRepository.save(entity);
        Adventure result = adventureEntityMapper.toAdventure(persisted);

        return result;
    }

    @Override
    public Optional<Adventure> findById(int id) {
        Optional<AdventureEntity> optional = adventureRepository.findById(id);

        return optional.map(adventureEntityMapper::toAdventure);
    }

    @Override
    public Optional<Adventure> findByUuid(UUID uuid) {
        Optional<AdventureEntity> optional = adventureRepository.findByUuid(uuid);

        return optional.map(adventureEntityMapper::toAdventure);
    }

    @Override
    public List<Adventure> findByAdventurerUuid(UUID uuid) {
        return adventureRepository.findByAdventurerUuid(uuid)
                .stream()
                .map(adventureEntityMapper::toAdventure)
                .toList();
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        adventureRepository.deleteByUuid(uuid);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return adventureRepository.existsByUuid(uuid);
    }
}

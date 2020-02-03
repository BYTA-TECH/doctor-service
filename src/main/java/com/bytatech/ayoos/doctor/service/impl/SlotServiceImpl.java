package com.bytatech.ayoos.doctor.service.impl;

import com.bytatech.ayoos.doctor.service.SlotService;
import com.bytatech.ayoos.doctor.domain.Slot;
import com.bytatech.ayoos.doctor.repository.SlotRepository;
import com.bytatech.ayoos.doctor.repository.search.SlotSearchRepository;
import com.bytatech.ayoos.doctor.service.dto.SlotDTO;
import com.bytatech.ayoos.doctor.service.mapper.SlotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Slot}.
 */
@Service
@Transactional
public class SlotServiceImpl implements SlotService {

    private final Logger log = LoggerFactory.getLogger(SlotServiceImpl.class);

    private final SlotRepository slotRepository;

    private final SlotMapper slotMapper;

    private final SlotSearchRepository slotSearchRepository;

    public SlotServiceImpl(SlotRepository slotRepository, SlotMapper slotMapper, SlotSearchRepository slotSearchRepository) {
        this.slotRepository = slotRepository;
        this.slotMapper = slotMapper;
        this.slotSearchRepository = slotSearchRepository;
    }

    /**
     * Save a slot.
     *
     * @param slotDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SlotDTO save(SlotDTO slotDTO) {
        log.debug("Request to save Slot : {}", slotDTO);
        Slot slot = slotMapper.toEntity(slotDTO);
        slot = slotRepository.save(slot);
        SlotDTO result = slotMapper.toDto(slot);
      //  slotSearchRepository.save(slot);
        return result;
    }

    /**
     * Get all the slots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SlotDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Slots");
        return slotRepository.findAll(pageable)
            .map(slotMapper::toDto);
    }


    /**
     * Get one slot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SlotDTO> findOne(Long id) {
        log.debug("Request to get Slot : {}", id);
        return slotRepository.findById(id)
            .map(slotMapper::toDto);
    }

    /**
     * Delete the slot by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Slot : {}", id);
        slotRepository.deleteById(id);
        slotSearchRepository.deleteById(id);
    }

    /**
     * Search for the slot corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
   /* @Override
    @Transactional(readOnly = true)
    public Page<SlotDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Slots for query {}", query);
        return slotSearchRepository.search(queryStringQuery(query), pageable)
            .map(slotMapper::toDto);
    }*/
}

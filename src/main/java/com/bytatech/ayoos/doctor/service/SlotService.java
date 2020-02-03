package com.bytatech.ayoos.doctor.service;

import com.bytatech.ayoos.doctor.service.dto.SlotDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.bytatech.ayoos.doctor.domain.Slot}.
 */
public interface SlotService {

    /**
     * Save a slot.
     *
     * @param slotDTO the entity to save.
     * @return the persisted entity.
     */
    SlotDTO save(SlotDTO slotDTO);

    /**
     * Get all the slots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SlotDTO> findAll(Pageable pageable);


    /**
     * Get the "id" slot.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SlotDTO> findOne(Long id);

    /**
     * Delete the "id" slot.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the slot corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
   // Page<SlotDTO> search(String query, Pageable pageable);
}

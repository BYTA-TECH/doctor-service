package com.bytatech.ayoos.doctor.service;

import com.bytatech.ayoos.doctor.service.dto.WorkPlaceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.bytatech.ayoos.doctor.domain.WorkPlace}.
 */
public interface WorkPlaceService {

    /**
     * Save a workPlace.
     *
     * @param workPlaceDTO the entity to save.
     * @return the persisted entity.
     */
    WorkPlaceDTO save(WorkPlaceDTO workPlaceDTO);

    /**
     * Get all the workPlaces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkPlaceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" workPlace.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkPlaceDTO> findOne(Long id);

    /**
     * Delete the "id" workPlace.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the workPlace corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkPlaceDTO> search(String query, Pageable pageable);
}

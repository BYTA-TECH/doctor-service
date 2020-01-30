package com.bytatech.ayoos.doctor.service;

import com.bytatech.ayoos.doctor.service.dto.ContactInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.bytatech.ayoos.doctor.domain.ContactInfo}.
 */
public interface ContactInfoService {

    /**
     * Save a contactInfo.
     *
     * @param contactInfoDTO the entity to save.
     * @return the persisted entity.
     */
    ContactInfoDTO save(ContactInfoDTO contactInfoDTO);

    /**
     * Get all the contactInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContactInfoDTO> findAll(Pageable pageable);
    /**
     * Get all the ContactInfoDTO where Doctor is {@code null}.
     *
     * @return the list of entities.
     */
    List<ContactInfoDTO> findAllWhereDoctorIsNull();


    /**
     * Get the "id" contactInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContactInfoDTO> findOne(Long id);

    /**
     * Delete the "id" contactInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the contactInfo corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContactInfoDTO> search(String query, Pageable pageable);
}

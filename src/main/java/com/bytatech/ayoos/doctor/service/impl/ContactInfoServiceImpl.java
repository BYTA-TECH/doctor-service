package com.bytatech.ayoos.doctor.service.impl;

import com.bytatech.ayoos.doctor.service.ContactInfoService;
import com.bytatech.ayoos.doctor.domain.ContactInfo;
import com.bytatech.ayoos.doctor.repository.ContactInfoRepository;
import com.bytatech.ayoos.doctor.repository.search.ContactInfoSearchRepository;
import com.bytatech.ayoos.doctor.service.dto.ContactInfoDTO;
import com.bytatech.ayoos.doctor.service.mapper.ContactInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ContactInfo}.
 */
@Service
@Transactional
public class ContactInfoServiceImpl implements ContactInfoService {

    private final Logger log = LoggerFactory.getLogger(ContactInfoServiceImpl.class);

    private final ContactInfoRepository contactInfoRepository;

    private final ContactInfoMapper contactInfoMapper;

    private final ContactInfoSearchRepository contactInfoSearchRepository;

    public ContactInfoServiceImpl(ContactInfoRepository contactInfoRepository, ContactInfoMapper contactInfoMapper, ContactInfoSearchRepository contactInfoSearchRepository) {
        this.contactInfoRepository = contactInfoRepository;
        this.contactInfoMapper = contactInfoMapper;
        this.contactInfoSearchRepository = contactInfoSearchRepository;
    }

    /**
     * Save a contactInfo.
     *
     * @param contactInfoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContactInfoDTO save(ContactInfoDTO contactInfoDTO) {
        log.debug("Request to save ContactInfo : {}", contactInfoDTO);
        ContactInfo contactInfo = contactInfoMapper.toEntity(contactInfoDTO);
        contactInfo = contactInfoRepository.save(contactInfo);
        ContactInfoDTO result = contactInfoMapper.toDto(contactInfo);
        /*ContactInfo elasticResult=  contactInfoSearchRepository.save(contactInfo);
        return updateToEs(elasticResult);*/
        return result;
    }

  /*  private ContactInfoDTO updateToEs(ContactInfo elasticResult) {
        log.debug("Request to updateToEs ContactInfo : {}", elasticResult);
        ContactInfo contactInfo= contactInfoSearchRepository.save(elasticResult);
        ContactInfoDTO result = contactInfoMapper.toDto(contactInfo);
        return result;
    }*/
    
    
    
    
    
    
    /**
     * Get all the contactInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContactInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContactInfos");
        return contactInfoRepository.findAll(pageable)
            .map(contactInfoMapper::toDto);
    }



    /**
    *  Get all the contactInfos where Doctor is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ContactInfoDTO> findAllWhereDoctorIsNull() {
        log.debug("Request to get all contactInfos where Doctor is null");
        return StreamSupport
            .stream(contactInfoRepository.findAll().spliterator(), false)
            .filter(contactInfo -> contactInfo.getDoctor() == null)
            .map(contactInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one contactInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactInfoDTO> findOne(Long id) {
        log.debug("Request to get ContactInfo : {}", id);
        return contactInfoRepository.findById(id)
            .map(contactInfoMapper::toDto);
    }

    /**
     * Delete the contactInfo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContactInfo : {}", id);
        contactInfoRepository.deleteById(id);
        contactInfoSearchRepository.deleteById(id);
    }

    /**
     * Search for the contactInfo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
  /*  @Override
    @Transactional(readOnly = true)
    public Page<ContactInfoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ContactInfos for query {}", query);
        return contactInfoSearchRepository.search(queryStringQuery(query), pageable)
            .map(contactInfoMapper::toDto);
    }*/
}

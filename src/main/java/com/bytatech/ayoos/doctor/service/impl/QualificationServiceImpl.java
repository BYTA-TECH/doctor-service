package com.bytatech.ayoos.doctor.service.impl;

import com.bytatech.ayoos.doctor.service.QualificationService;
import com.bytatech.ayoos.doctor.domain.ContactInfo;
import com.bytatech.ayoos.doctor.domain.Qualification;
import com.bytatech.ayoos.doctor.domain.Qualification;
import com.bytatech.ayoos.doctor.domain.Qualification;
import com.bytatech.ayoos.doctor.repository.QualificationRepository;
import com.bytatech.ayoos.doctor.repository.search.QualificationSearchRepository;
import com.bytatech.ayoos.doctor.service.dto.ContactInfoDTO;
import com.bytatech.ayoos.doctor.service.dto.QualificationDTO;
import com.bytatech.ayoos.doctor.service.dto.QualificationDTO;
import com.bytatech.ayoos.doctor.service.dto.QualificationDTO;
import com.bytatech.ayoos.doctor.service.mapper.QualificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Qualification}.
 */
@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {

    private final Logger log = LoggerFactory.getLogger(QualificationServiceImpl.class);

    private final QualificationRepository qualificationRepository;

    private final QualificationMapper qualificationMapper;

    private final QualificationSearchRepository qualificationSearchRepository;

    public QualificationServiceImpl(QualificationRepository qualificationRepository, QualificationMapper qualificationMapper, QualificationSearchRepository qualificationSearchRepository) {
        this.qualificationRepository = qualificationRepository;
        this.qualificationMapper = qualificationMapper;
        this.qualificationSearchRepository = qualificationSearchRepository;
    }

    /**
     * Save a qualification.
     *
     * @param qualificationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QualificationDTO save(QualificationDTO qualificationDTO) {
        log.debug("Request to save Qualification : {}", qualificationDTO);
        Qualification qualification = qualificationMapper.toEntity(qualificationDTO);
        qualification = qualificationRepository.save(qualification);
        QualificationDTO result = qualificationMapper.toDto(qualification);
         qualificationSearchRepository.save(qualification);
        return updateToEs(result);
    }

   
    
    
    
   private  QualificationDTO updateToEs( QualificationDTO qualificationDTO) {
	   
        
        log.debug("Request to save Qualification : {}", qualificationDTO);
        Qualification qualification = qualificationMapper.toEntity(qualificationDTO);
        qualification = qualificationRepository.save(qualification);
        QualificationDTO result = qualificationMapper.toDto(qualification);
        qualificationSearchRepository.save(qualification);
        return result;  
    }
    
    
    
    
    
    /**
     * Get all the qualifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QualificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Qualifications");
        return qualificationRepository.findAll(pageable)
            .map(qualificationMapper::toDto);
    }


    /**
     * Get one qualification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QualificationDTO> findOne(Long id) {
        log.debug("Request to get Qualification : {}", id);
        return qualificationRepository.findById(id)
            .map(qualificationMapper::toDto);
    }

    /**
     * Delete the qualification by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Qualification : {}", id);
        qualificationRepository.deleteById(id);
        qualificationSearchRepository.deleteById(id);
    }

    /**
     * Search for the qualification corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
   /* @Override
    @Transactional(readOnly = true)
    public Page<QualificationDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Qualifications for query {}", query);
        return qualificationSearchRepository.search(queryStringQuery(query), pageable)
            .map(qualificationMapper::toDto);
    }*/
}

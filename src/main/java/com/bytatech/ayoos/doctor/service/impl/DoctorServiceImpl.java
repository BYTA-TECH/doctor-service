package com.bytatech.ayoos.doctor.service.impl;

import com.bytatech.ayoos.doctor.service.DoctorService;
import com.bytatech.ayoos.doctor.domain.Doctor;
import com.bytatech.ayoos.doctor.repository.DoctorRepository;
import com.bytatech.ayoos.doctor.repository.search.DoctorSearchRepository;
import com.bytatech.ayoos.doctor.service.dto.DoctorDTO;
import com.bytatech.ayoos.doctor.service.mapper.DoctorMapper;
import com.bytatech.ayoos.doctor.client.dms.model.PersonBodyCreate;
import com.bytatech.ayoos.doctor.client.dms.api.PeopleApi;
import com.bytatech.ayoos.doctor.client.dms.api.SitesApi;
import com.bytatech.ayoos.doctor.client.dms.model.PersonBodyCreate;
import com.bytatech.ayoos.doctor.client.dms.model.PersonEntry;
import com.bytatech.ayoos.doctor.client.dms.model.SiteBodyCreate;
import com.bytatech.ayoos.doctor.client.dms.model.SiteEntry;
import com.bytatech.ayoos.doctor.client.dms.model.SiteMemberEntry;
import com.bytatech.ayoos.doctor.client.dms.model.SiteMembershipBodyCreate;
import com.bytatech.ayoos.doctor.client.dms.model.SiteBodyCreate.VisibilityEnum;
import com.bytatech.ayoos.doctor.client.dms.model.SiteMembershipBodyCreate.RoleEnum;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Doctor}.
 */
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private final DoctorRepository doctorRepository;

    private final DoctorMapper doctorMapper;

    private final DoctorSearchRepository doctorSearchRepository;
    
    @Autowired
    PeopleApi peopleApi;
    @Autowired
    SitesApi sitesApi;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper, DoctorSearchRepository doctorSearchRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.doctorSearchRepository = doctorSearchRepository;
    }

    /**
     * Save a doctor.
     *
     * @param doctorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DoctorDTO save(DoctorDTO doctorDTO) {
        log.debug("Request to save Doctor : {}", doctorDTO);
        Doctor doctor = doctorMapper.toEntity(doctorDTO);
        doctor = doctorRepository.save(doctor);
        DoctorDTO result = doctorMapper.toDto(doctor);
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"+doctor);
        doctorSearchRepository.save(doctor);
        return result;
    }

    /**
     * Get all the doctors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DoctorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Doctors");
        return doctorRepository.findAll(pageable)
            .map(doctorMapper::toDto);
    }


    /**
     * Get one doctor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorDTO> findOne(Long id) {
        log.debug("Request to get Doctor : {}", id);
        return doctorRepository.findById(id)
            .map(doctorMapper::toDto);
    }

    /**
     * Delete the doctor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Doctor : {}", id);
        doctorRepository.deleteById(id);
        doctorSearchRepository.deleteById(id);
    }

    /**
     * Search for the doctor corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
   /* @Override
    @Transactional(readOnly = true)
    public Page<DoctorDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Doctors for query {}", query);
        return doctorSearchRepository.search(queryStringQuery(query), pageable)
            .map(doctorMapper::toDto);
    }*/
    
    
    
    
    
     	public void createPersonOnDMS( DoctorDTO doctorDTO) {
		log.debug("=================into the process CreatePeople()===========");
System.out.println("#################################"+doctorDTO.getDoctorIdpCode());
		PersonBodyCreate personBodyCreate = new PersonBodyCreate();
		personBodyCreate.setId(doctorDTO.getDoctorIdpCode());
		personBodyCreate.setFirstName(doctorDTO.getDoctorIdpCode());
		personBodyCreate.setEmail(doctorDTO.getEmail());
		personBodyCreate.setPassword(doctorDTO.getDoctorIdpCode());
		personBodyCreate.setEnabled(true);
		ResponseEntity<PersonEntry> p=peopleApi.createPerson(personBodyCreate, null);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+p.getBody());
	}
 
    
	public String createSite( String siteId) {
		SiteBodyCreate siteBodyCreate = new SiteBodyCreate();
		siteBodyCreate.setTitle(siteId);
		siteBodyCreate.setId(siteId);
		siteBodyCreate.setVisibility(VisibilityEnum.MODERATED);
		List<String> s = new ArrayList();
		s.add("id");
		s.add("title");
	

		ResponseEntity<SiteEntry> entry = sitesApi.createSite(siteBodyCreate, false, false,s);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+entry.getBody().getEntry().getTitle());
		return entry.getBody().getEntry().getId();
	}  
	 
    
	public SiteMemberEntry createSiteMembership(String siteId, String id) {
		SiteMembershipBodyCreate siteMembershipBodyCreate = new SiteMembershipBodyCreate();
		siteMembershipBodyCreate.setRole(RoleEnum.SITEMANAGER);
		siteMembershipBodyCreate.setId(id);
		return sitesApi.createSiteMembership(siteId, siteMembershipBodyCreate, null).getBody();
	}

    
}

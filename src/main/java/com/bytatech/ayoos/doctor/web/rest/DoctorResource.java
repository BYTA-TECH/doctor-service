package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.service.*;
import com.bytatech.ayoos.doctor.web.rest.errors.BadRequestAlertException;
import com.bytatech.ayoos.doctor.service.dto.DoctorDTO;
import com.bytatech.ayoos.doctor.service.dto.PaymentSettingsDTO;
import com.bytatech.ayoos.doctor.service.dto.DoctorSettingsDTO;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.bytatech.ayoos.doctor.domain.Doctor}.
 */
@RestController
@RequestMapping("/api")
public class DoctorResource {

    private final Logger log = LoggerFactory.getLogger(DoctorResource.class);

    private static final String ENTITY_NAME = "doctorDoctor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
	DoctorSettingsService doctorSettingsService;
    
    @Autowired
	PaymentSettingsService paymentSettingsService;
    
    
    private final DoctorService doctorService;

    public DoctorResource(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * {@code POST  /doctors} : Create a new doctor.
     *
     * @param doctorDTO the doctorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doctorDTO, or with status {@code 400 (Bad Request)} if the doctor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/doctors")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) throws URISyntaxException {
        log.debug("REST request to save Doctor : {}", doctorDTO);
      //..................default settings...........................
      		DoctorSettingsDTO doctorSettings=new DoctorSettingsDTO();
      		
      		doctorSettings.setApprovalType("automatic");
      		
      		doctorSettings.setIsMailNotificationsEnabled(true);
      		
      		doctorSettings.setIsSMSNotificationsEnabled(true);
      		
      		DoctorSettingsDTO dto=doctorSettingsService.save(doctorSettings);
      		
      		//..................default paymentsettings...........................
      		
      		PaymentSettingsDTO payment=new PaymentSettingsDTO();
      		
      		payment.setAmount(100.0);
      		
      		payment.setIsPaymentEnabled(false);
      		
      		payment.setCurrency("INR");
      		
      		PaymentSettingsDTO paymentSettings = paymentSettingsService.save(payment);
      		

      		doctorDTO.setDoctorSettingsId(dto.getId());
      		
      		doctorDTO.setPaymentSettingsId(paymentSettings.getId());
      		
      		
        if (doctorDTO.getId() != null) {
            throw new BadRequestAlertException("A new doctor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
         doctorService. createPersonOnDMS(doctorDTO);
        
        String siteId = doctorDTO.getDoctorIdpCode() + "site";
		String dmsId =  doctorService.createSite(siteId);
		doctorDTO.setDmsId(dmsId);
		doctorService.createSiteMembership(dmsId, doctorDTO.getDoctorIdpCode());
       
        DoctorDTO result = doctorService.save(doctorDTO);
        return ResponseEntity.created(new URI("/api/doctors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /doctors} : Updates an existing doctor.
     *
     * @param doctorDTO the doctorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doctorDTO,
     * or with status {@code 400 (Bad Request)} if the doctorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doctorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/doctors")
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO) throws URISyntaxException {
        log.debug("REST request to update Doctor : {}", doctorDTO);
        if (doctorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoctorDTO result = doctorService.save(doctorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, doctorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /doctors} : get all the doctors.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doctors in body.
     */
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors(Pageable pageable) {
        log.debug("REST request to get a page of Doctors");
        Page<DoctorDTO> page = doctorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /doctors/:id} : get the "id" doctor.
     *
     * @param id the id of the doctorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doctorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable Long id) {
        log.debug("REST request to get Doctor : {}", id);
        Optional<DoctorDTO> doctorDTO = doctorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doctorDTO);
    }

    /**
     * {@code DELETE  /doctors/:id} : delete the "id" doctor.
     *
     * @param id the id of the doctorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        log.debug("REST request to delete Doctor : {}", id);
        doctorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/doctors?query=:query} : search for the doctor corresponding
     * to the query.
     *
     * @param query the query of the doctor search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
   /* @GetMapping("/_search/doctors")
    public ResponseEntity<List<DoctorDTO>> searchDoctors(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Doctors for query {}", query);
        Page<DoctorDTO> page = doctorService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }*/
}

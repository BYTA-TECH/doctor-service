package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.service.DoctorSettingsService;
import com.bytatech.ayoos.doctor.web.rest.errors.BadRequestAlertException;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.bytatech.ayoos.doctor.domain.DoctorSettings}.
 */
@RestController
@RequestMapping("/api")
public class DoctorSettingsResource {

    private final Logger log = LoggerFactory.getLogger(DoctorSettingsResource.class);

    private static final String ENTITY_NAME = "doctorDoctorSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DoctorSettingsService doctorSettingsService;

    public DoctorSettingsResource(DoctorSettingsService doctorSettingsService) {
        this.doctorSettingsService = doctorSettingsService;
    }

    /**
     * {@code POST  /doctor-settings} : Create a new doctorSettings.
     *
     * @param doctorSettingsDTO the doctorSettingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doctorSettingsDTO, or with status {@code 400 (Bad Request)} if the doctorSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/doctor-settings")
    public ResponseEntity<DoctorSettingsDTO> createDoctorSettings(@RequestBody DoctorSettingsDTO doctorSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save DoctorSettings : {}", doctorSettingsDTO);
        if (doctorSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new doctorSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoctorSettingsDTO result = doctorSettingsService.save(doctorSettingsDTO);
        return ResponseEntity.created(new URI("/api/doctor-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /doctor-settings} : Updates an existing doctorSettings.
     *
     * @param doctorSettingsDTO the doctorSettingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doctorSettingsDTO,
     * or with status {@code 400 (Bad Request)} if the doctorSettingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doctorSettingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/doctor-settings")
    public ResponseEntity<DoctorSettingsDTO> updateDoctorSettings(@RequestBody DoctorSettingsDTO doctorSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update DoctorSettings : {}", doctorSettingsDTO);
        if (doctorSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoctorSettingsDTO result = doctorSettingsService.save(doctorSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, doctorSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /doctor-settings} : get all the doctorSettings.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doctorSettings in body.
     */
    @GetMapping("/doctor-settings")
    public ResponseEntity<List<DoctorSettingsDTO>> getAllDoctorSettings(Pageable pageable) {
        log.debug("REST request to get a page of DoctorSettings");
        Page<DoctorSettingsDTO> page = doctorSettingsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /doctor-settings/:id} : get the "id" doctorSettings.
     *
     * @param id the id of the doctorSettingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doctorSettingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/doctor-settings/{id}")
    public ResponseEntity<DoctorSettingsDTO> getDoctorSettings(@PathVariable Long id) {
        log.debug("REST request to get DoctorSettings : {}", id);
        Optional<DoctorSettingsDTO> doctorSettingsDTO = doctorSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doctorSettingsDTO);
    }

    /**
     * {@code DELETE  /doctor-settings/:id} : delete the "id" doctorSettings.
     *
     * @param id the id of the doctorSettingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/doctor-settings/{id}")
    public ResponseEntity<Void> deleteDoctorSettings(@PathVariable Long id) {
        log.debug("REST request to delete DoctorSettings : {}", id);
        doctorSettingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/doctor-settings?query=:query} : search for the doctorSettings corresponding
     * to the query.
     *
     * @param query the query of the doctorSettings search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/doctor-settings")
    public ResponseEntity<List<DoctorSettingsDTO>> searchDoctorSettings(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of DoctorSettings for query {}", query);
        Page<DoctorSettingsDTO> page = doctorSettingsService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

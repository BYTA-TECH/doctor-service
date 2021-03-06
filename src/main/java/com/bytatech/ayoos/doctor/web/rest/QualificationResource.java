package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.service.QualificationService;
import com.bytatech.ayoos.doctor.web.rest.errors.BadRequestAlertException;
import com.bytatech.ayoos.doctor.service.dto.QualificationDTO;

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
 * REST controller for managing {@link com.bytatech.ayoos.doctor.domain.Qualification}.
 */
@RestController
@RequestMapping("/api")
public class QualificationResource {

    private final Logger log = LoggerFactory.getLogger(QualificationResource.class);

    private static final String ENTITY_NAME = "doctorQualification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualificationService qualificationService;

    public QualificationResource(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    /**
     * {@code POST  /qualifications} : Create a new qualification.
     *
     * @param qualificationDTO the qualificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualificationDTO, or with status {@code 400 (Bad Request)} if the qualification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qualifications")
    public ResponseEntity<QualificationDTO> createQualification(@RequestBody QualificationDTO qualificationDTO) throws URISyntaxException {
        log.debug("REST request to save Qualification : {}", qualificationDTO);
        if (qualificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new qualification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QualificationDTO result = qualificationService.save(qualificationDTO);
        return ResponseEntity.created(new URI("/api/qualifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qualifications} : Updates an existing qualification.
     *
     * @param qualificationDTO the qualificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualificationDTO,
     * or with status {@code 400 (Bad Request)} if the qualificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qualifications")
    public ResponseEntity<QualificationDTO> updateQualification(@RequestBody QualificationDTO qualificationDTO) throws URISyntaxException {
        log.debug("REST request to update Qualification : {}", qualificationDTO);
        if (qualificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QualificationDTO result = qualificationService.save(qualificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qualifications} : get all the qualifications.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualifications in body.
     */
    @GetMapping("/qualifications")
    public ResponseEntity<List<QualificationDTO>> getAllQualifications(Pageable pageable) {
        log.debug("REST request to get a page of Qualifications");
        Page<QualificationDTO> page = qualificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qualifications/:id} : get the "id" qualification.
     *
     * @param id the id of the qualificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qualifications/{id}")
    public ResponseEntity<QualificationDTO> getQualification(@PathVariable Long id) {
        log.debug("REST request to get Qualification : {}", id);
        Optional<QualificationDTO> qualificationDTO = qualificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualificationDTO);
    }

    /**
     * {@code DELETE  /qualifications/:id} : delete the "id" qualification.
     *
     * @param id the id of the qualificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qualifications/{id}")
    public ResponseEntity<Void> deleteQualification(@PathVariable Long id) {
        log.debug("REST request to delete Qualification : {}", id);
        qualificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/qualifications?query=:query} : search for the qualification corresponding
     * to the query.
     *
     * @param query the query of the qualification search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
   /* @GetMapping("/_search/qualifications")
    public ResponseEntity<List<QualificationDTO>> searchQualifications(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Qualifications for query {}", query);
        Page<QualificationDTO> page = qualificationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }*/
}

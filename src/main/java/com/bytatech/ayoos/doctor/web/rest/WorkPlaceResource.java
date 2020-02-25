package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.service.WorkPlaceService;
import com.bytatech.ayoos.doctor.web.rest.errors.BadRequestAlertException;
import com.bytatech.ayoos.doctor.service.dto.WorkPlaceDTO;

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
 * REST controller for managing {@link com.bytatech.ayoos.doctor.domain.WorkPlace}.
 */
@RestController
@RequestMapping("/api")
public class WorkPlaceResource {

    private final Logger log = LoggerFactory.getLogger(WorkPlaceResource.class);

    private static final String ENTITY_NAME = "doctorWorkPlace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkPlaceService workPlaceService;

    public WorkPlaceResource(WorkPlaceService workPlaceService) {
        this.workPlaceService = workPlaceService;
    }

    /**
     * {@code POST  /work-places} : Create a new workPlace.
     *
     * @param workPlaceDTO the workPlaceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workPlaceDTO, or with status {@code 400 (Bad Request)} if the workPlace has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-places")
    public ResponseEntity<WorkPlaceDTO> createWorkPlace(@RequestBody WorkPlaceDTO workPlaceDTO) throws URISyntaxException {
        log.debug("REST request to save WorkPlace : {}", workPlaceDTO);
        if (workPlaceDTO.getId() != null) {
            throw new BadRequestAlertException("A new workPlace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkPlaceDTO result = workPlaceService.save(workPlaceDTO);
        return ResponseEntity.created(new URI("/api/work-places/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-places} : Updates an existing workPlace.
     *
     * @param workPlaceDTO the workPlaceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workPlaceDTO,
     * or with status {@code 400 (Bad Request)} if the workPlaceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workPlaceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-places")
    public ResponseEntity<WorkPlaceDTO> updateWorkPlace(@RequestBody WorkPlaceDTO workPlaceDTO) throws URISyntaxException {
        log.debug("REST request to update WorkPlace : {}", workPlaceDTO);
        if (workPlaceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkPlaceDTO result = workPlaceService.save(workPlaceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workPlaceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /work-places} : get all the workPlaces.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workPlaces in body.
     */
    @GetMapping("/work-places")
    public ResponseEntity<List<WorkPlaceDTO>> getAllWorkPlaces(Pageable pageable) {
        log.debug("REST request to get a page of WorkPlaces");
        Page<WorkPlaceDTO> page = workPlaceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-places/:id} : get the "id" workPlace.
     *
     * @param id the id of the workPlaceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workPlaceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-places/{id}")
    public ResponseEntity<WorkPlaceDTO> getWorkPlace(@PathVariable Long id) {
        log.debug("REST request to get WorkPlace : {}", id);
        Optional<WorkPlaceDTO> workPlaceDTO = workPlaceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workPlaceDTO);
    }

    /**
     * {@code DELETE  /work-places/:id} : delete the "id" workPlace.
     *
     * @param id the id of the workPlaceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-places/{id}")
    public ResponseEntity<Void> deleteWorkPlace(@PathVariable Long id) {
        log.debug("REST request to delete WorkPlace : {}", id);
        workPlaceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/work-places?query=:query} : search for the workPlace corresponding
     * to the query.
     *
     * @param query the query of the workPlace search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/work-places")
    public ResponseEntity<List<WorkPlaceDTO>> searchWorkPlaces(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of WorkPlaces for query {}", query);
        Page<WorkPlaceDTO> page = workPlaceService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

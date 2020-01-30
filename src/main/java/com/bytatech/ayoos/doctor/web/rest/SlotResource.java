package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.service.SlotService;
import com.bytatech.ayoos.doctor.web.rest.errors.BadRequestAlertException;
import com.bytatech.ayoos.doctor.service.dto.SlotDTO;

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
 * REST controller for managing {@link com.bytatech.ayoos.doctor.domain.Slot}.
 */
@RestController
@RequestMapping("/api")
public class SlotResource {

    private final Logger log = LoggerFactory.getLogger(SlotResource.class);

    private static final String ENTITY_NAME = "doctorSlot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotService slotService;

    public SlotResource(SlotService slotService) {
        this.slotService = slotService;
    }

    /**
     * {@code POST  /slots} : Create a new slot.
     *
     * @param slotDTO the slotDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotDTO, or with status {@code 400 (Bad Request)} if the slot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slots")
    public ResponseEntity<SlotDTO> createSlot(@RequestBody SlotDTO slotDTO) throws URISyntaxException {
        log.debug("REST request to save Slot : {}", slotDTO);
        if (slotDTO.getId() != null) {
            throw new BadRequestAlertException("A new slot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotDTO result = slotService.save(slotDTO);
        return ResponseEntity.created(new URI("/api/slots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slots} : Updates an existing slot.
     *
     * @param slotDTO the slotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotDTO,
     * or with status {@code 400 (Bad Request)} if the slotDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slots")
    public ResponseEntity<SlotDTO> updateSlot(@RequestBody SlotDTO slotDTO) throws URISyntaxException {
        log.debug("REST request to update Slot : {}", slotDTO);
        if (slotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotDTO result = slotService.save(slotDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slots} : get all the slots.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slots in body.
     */
    @GetMapping("/slots")
    public ResponseEntity<List<SlotDTO>> getAllSlots(Pageable pageable) {
        log.debug("REST request to get a page of Slots");
        Page<SlotDTO> page = slotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /slots/:id} : get the "id" slot.
     *
     * @param id the id of the slotDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slots/{id}")
    public ResponseEntity<SlotDTO> getSlot(@PathVariable Long id) {
        log.debug("REST request to get Slot : {}", id);
        Optional<SlotDTO> slotDTO = slotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(slotDTO);
    }

    /**
     * {@code DELETE  /slots/:id} : delete the "id" slot.
     *
     * @param id the id of the slotDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slots/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        log.debug("REST request to delete Slot : {}", id);
        slotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/slots?query=:query} : search for the slot corresponding
     * to the query.
     *
     * @param query the query of the slot search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/slots")
    public ResponseEntity<List<SlotDTO>> searchSlots(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Slots for query {}", query);
        Page<SlotDTO> page = slotService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

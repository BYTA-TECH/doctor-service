package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.service.ContactInfoService;
import com.bytatech.ayoos.doctor.web.rest.errors.BadRequestAlertException;
import com.bytatech.ayoos.doctor.service.dto.ContactInfoDTO;

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
 * REST controller for managing {@link com.bytatech.ayoos.doctor.domain.ContactInfo}.
 */
@RestController
@RequestMapping("/api")
public class ContactInfoResource {

    private final Logger log = LoggerFactory.getLogger(ContactInfoResource.class);

    private static final String ENTITY_NAME = "doctorContactInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactInfoService contactInfoService;

    public ContactInfoResource(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    /**
     * {@code POST  /contact-infos} : Create a new contactInfo.
     *
     * @param contactInfoDTO the contactInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactInfoDTO, or with status {@code 400 (Bad Request)} if the contactInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contact-infos")
    public ResponseEntity<ContactInfoDTO> createContactInfo(@RequestBody ContactInfoDTO contactInfoDTO) throws URISyntaxException {
        log.debug("REST request to save ContactInfo : {}", contactInfoDTO);
        if (contactInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new contactInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactInfoDTO result = contactInfoService.save(contactInfoDTO);
        return ResponseEntity.created(new URI("/api/contact-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contact-infos} : Updates an existing contactInfo.
     *
     * @param contactInfoDTO the contactInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactInfoDTO,
     * or with status {@code 400 (Bad Request)} if the contactInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contact-infos")
    public ResponseEntity<ContactInfoDTO> updateContactInfo(@RequestBody ContactInfoDTO contactInfoDTO) throws URISyntaxException {
        log.debug("REST request to update ContactInfo : {}", contactInfoDTO);
        if (contactInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactInfoDTO result = contactInfoService.save(contactInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contact-infos} : get all the contactInfos.
     *

     * @param pageable the pagination information.

     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactInfos in body.
     */
    @GetMapping("/contact-infos")
    public ResponseEntity<List<ContactInfoDTO>> getAllContactInfos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("doctor-is-null".equals(filter)) {
            log.debug("REST request to get all ContactInfos where doctor is null");
            return new ResponseEntity<>(contactInfoService.findAllWhereDoctorIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ContactInfos");
        Page<ContactInfoDTO> page = contactInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contact-infos/:id} : get the "id" contactInfo.
     *
     * @param id the id of the contactInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contact-infos/{id}")
    public ResponseEntity<ContactInfoDTO> getContactInfo(@PathVariable Long id) {
        log.debug("REST request to get ContactInfo : {}", id);
        Optional<ContactInfoDTO> contactInfoDTO = contactInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactInfoDTO);
    }

    /**
     * {@code DELETE  /contact-infos/:id} : delete the "id" contactInfo.
     *
     * @param id the id of the contactInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contact-infos/{id}")
    public ResponseEntity<Void> deleteContactInfo(@PathVariable Long id) {
        log.debug("REST request to delete ContactInfo : {}", id);
        contactInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/contact-infos?query=:query} : search for the contactInfo corresponding
     * to the query.
     *
     * @param query the query of the contactInfo search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
 /*   @GetMapping("/_search/contact-infos")
    public ResponseEntity<List<ContactInfoDTO>> searchContactInfos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ContactInfos for query {}", query);
        Page<ContactInfoDTO> page = contactInfoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }*/
}

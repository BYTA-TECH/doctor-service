package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.service.SessionInfoService;
import com.bytatech.ayoos.doctor.web.rest.errors.BadRequestAlertException;
import com.bytatech.ayoos.doctor.service.dto.SessionInfoDTO;

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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * REST controller for managing {@link com.bytatech.ayoos.doctor.domain.SessionInfo}.
 */
@RestController
@RequestMapping("/api")
public class SessionInfoResource {

    private final Logger log = LoggerFactory.getLogger(SessionInfoResource.class);

    private static final String ENTITY_NAME = "doctorSessionInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SessionInfoService sessionInfoService;

    public SessionInfoResource(SessionInfoService sessionInfoService) {
        this.sessionInfoService = sessionInfoService;
    }

    /**
     * {@code POST  /session-infos} : Create a new sessionInfo.
     *
     * @param sessionInfoDTO the sessionInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sessionInfoDTO, or with status {@code 400 (Bad Request)} if the sessionInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/session-infos")
    public ResponseEntity<SessionInfoDTO> createSessionInfo(@RequestBody SessionInfoDTO sessionInfoDTO) throws URISyntaxException {
        log.debug("REST request to save SessionInfo : {}", sessionInfoDTO);
        if (sessionInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new sessionInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SessionInfoDTO result = sessionInfoService.save(sessionInfoDTO);
        return ResponseEntity.created(new URI("/api/session-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /session-infos} : Updates an existing sessionInfo.
     *
     * @param sessionInfoDTO the sessionInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sessionInfoDTO,
     * or with status {@code 400 (Bad Request)} if the sessionInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sessionInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/session-infos")
    public ResponseEntity<SessionInfoDTO> updateSessionInfo(@RequestBody SessionInfoDTO sessionInfoDTO) throws URISyntaxException {
        log.debug("REST request to update SessionInfo : {}", sessionInfoDTO);
        if (sessionInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SessionInfoDTO result = sessionInfoService.save(sessionInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sessionInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /session-infos} : get all the sessionInfos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sessionInfos in body.
     */
    @GetMapping("/session-infos")
    public ResponseEntity<List<SessionInfoDTO>> getAllSessionInfos(Pageable pageable) {
        log.debug("REST request to get a page of SessionInfos");
        Page<SessionInfoDTO> page = sessionInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /session-infos/:id} : get the "id" sessionInfo.
     *
     * @param id the id of the sessionInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sessionInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/session-infos/{id}")
    public ResponseEntity<SessionInfoDTO> getSessionInfo(@PathVariable Long id) {
        log.debug("REST request to get SessionInfo : {}", id);
        Optional<SessionInfoDTO> sessionInfoDTO = sessionInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sessionInfoDTO);
    }

    /**
     * {@code DELETE  /session-infos/:id} : delete the "id" sessionInfo.
     *
     * @param id the id of the sessionInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/session-infos/{id}")
    public ResponseEntity<Void> deleteSessionInfo(@PathVariable Long id) {
        log.debug("REST request to delete SessionInfo : {}", id);
        sessionInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/session-infos?query=:query} : search for the sessionInfo corresponding
     * to the query.
     *
     * @param query the query of the sessionInfo search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/session-infos")
    public ResponseEntity<List<SessionInfoDTO>> searchSessionInfos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of SessionInfos for query {}", query);
        Page<SessionInfoDTO> page = sessionInfoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PostMapping("/sessionInfoByDate/{fromDate}/{toDate}")
	public List<SessionInfoDTO> setSessionByDates(@RequestBody SessionInfoDTO sessionList,
			@PathVariable String fromDate, @PathVariable String toDate)/* throws ParseException*/ {

		/*Date from_Date = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
		Date to_Date = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);

		Calendar c = Calendar.getInstance();

		c.setTime(from_Date);

		List<SessionInfoDTO> sessionDTO = new ArrayList<SessionInfoDTO>();

		if (from_Date.before(to_Date)) {
			for (int i = 0; !to_Date.equals(c.getTime()); i++) {

				int weekRef = c.get(Calendar.DAY_OF_WEEK);

				for (SessionInfoDTO sDTO : sessionList) {

					log.info("..........weekRef............" + weekRef + ".......sDTO.getWeekDay()........"
							+ sDTO.getWeekDay());

					if (weekRef == sDTO.getWeekDay()) {

						SessionInfo s = new SessionInfo();

						s.setSessionName(sDTO.getSessionName());

						s.setDate(c.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

						s.setWeekDay(weekRef);

						s.setFromTime(sDTO.getFromTime());

						s.setToTime(sDTO.getToTime());

						s.setInterval(sDTO.getInterval());
						log.info("...............workplaceid.................." + sDTO.getWorkPlaceId());
						WorkPlaceDTO workplaceDTO = workPlaceService.findOne(sDTO.getWorkPlaceId()).get();

						s.setWorkPlace(workPlaceMapper.toEntity(workplaceDTO));

						if (s.getId() != null) {

							throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
						}

						SessionInfoDTO sessionInfoDTO = sessionInfoMapper.toDto(s);

						//SessionInfoDTO dto=null;
						
						//SessionInfoDTO alreadyExits= sessionInfoService.findBysessionNameAndDateAndWeekDayAndFromTimeAndToTimeAndWorkPlaceId(sessionInfoDTO.getSessionName(),sessionInfoDTO.getDate(),sessionInfoDTO.getWeekDay(),sessionInfoDTO.getFromTime(),sessionInfoDTO.getToTime(),sessionInfoDTO.getWorkPlaceId());
					
						
						
						SessionInfoDTO dto = sessionInfoService.save(sessionInfoDTO);
						
						if (dto.getId() == null) {
							throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
						}

						SessionInfoDTO result = sessionInfoService.save(dto);
						
						sessionDTO.add(dto);
					}
				}
				// System.out.println(".........to_Date1........"+to_Date+"..............."+".........c.getTime()........"+c.getTime()+".........."+to_Date.equals(c.getTime()));
				c.add(Calendar.DAY_OF_MONTH, +1);
				// System.out.println(".........to_Date2........"+to_Date+"..............."+".........c.getTime()........"+c.getTime()+".........."+to_Date.equals(c.getTime()));
			}
		}
		return sessionDTO;*/
    	LocalDate startDate = LocalDate.parse(fromDate);
		LocalDate endDate = LocalDate.parse(toDate);
		sessionInfoService.setSessionInfosByDates(sessionList, startDate,endDate);
    	return null;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

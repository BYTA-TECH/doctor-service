package com.bytatech.ayoos.doctor.service.impl;

import com.bytatech.ayoos.doctor.service.SessionInfoService;
import com.bytatech.ayoos.doctor.domain.SessionInfo;
import com.bytatech.ayoos.doctor.domain.SessionInfo;
import com.bytatech.ayoos.doctor.repository.SessionInfoRepository;
import com.bytatech.ayoos.doctor.repository.search.SessionInfoSearchRepository;
import com.bytatech.ayoos.doctor.service.dto.SessionInfoDTO;
import com.bytatech.ayoos.doctor.service.dto.SessionInfoDTO;
import com.bytatech.ayoos.doctor.service.mapper.SessionInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.Optional;
import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link SessionInfo}.
 */
@Service
@Transactional
public class SessionInfoServiceImpl implements SessionInfoService {

    private final Logger log = LoggerFactory.getLogger(SessionInfoServiceImpl.class);

    private final SessionInfoRepository sessionInfoRepository;

    private final SessionInfoMapper sessionInfoMapper;

    private final SessionInfoSearchRepository sessionInfoSearchRepository;

    public SessionInfoServiceImpl(SessionInfoRepository sessionInfoRepository, SessionInfoMapper sessionInfoMapper, SessionInfoSearchRepository sessionInfoSearchRepository) {
        this.sessionInfoRepository = sessionInfoRepository;
        this.sessionInfoMapper = sessionInfoMapper;
        this.sessionInfoSearchRepository = sessionInfoSearchRepository;
    }

    /**
     * Save a sessionInfo.
     *
     * @param sessionInfoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SessionInfoDTO save(SessionInfoDTO sessionInfoDTO) {
        log.debug("Request to save SessionInfo : {}", sessionInfoDTO);
        SessionInfo sessionInfo = sessionInfoMapper.toEntity(sessionInfoDTO);
        sessionInfo = sessionInfoRepository.save(sessionInfo);
        SessionInfoDTO result = sessionInfoMapper.toDto(sessionInfo);
       // SessionInfo  elasticResult=sessionInfoSearchRepository.save(sessionInfo);
  
  //  return updateToEs(elasticResult);
        return result;
}

/*private SessionInfoDTO updateToEs(SessionInfo elasticResult) {
    log.debug("Request to updateToEs SessionInfo : {}", elasticResult);
   SessionInfo sessionInfo= sessionInfoSearchRepository.save(elasticResult);
    SessionInfoDTO result = sessionInfoMapper.toDto(sessionInfo);
    return result;
}
    */

    /**
     * Get all the sessionInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SessionInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SessionInfos");
        return sessionInfoRepository.findAll(pageable)
            .map(sessionInfoMapper::toDto);
    }


    /**
     * Get one sessionInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SessionInfoDTO> findOne(Long id) {
        log.debug("Request to get SessionInfo : {}", id);
        return sessionInfoRepository.findById(id)
            .map(sessionInfoMapper::toDto);
    }

    /**
     * Delete the sessionInfo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SessionInfo : {}", id);
        sessionInfoRepository.deleteById(id);
        sessionInfoSearchRepository.deleteById(id);
    }

    /**
     * Search for the sessionInfo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
   /* @Override
    @Transactional(readOnly = true)
    public Page<SessionInfoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SessionInfos for query {}", query);
        return sessionInfoSearchRepository.search(queryStringQuery(query), pageable)
            .map(sessionInfoMapper::toDto);
    }*/
    
	public List<SessionInfoDTO> setSessionInfosByDates(SessionInfoDTO session,
			 LocalDate  startDate, LocalDate endDate){
		List<SessionInfoDTO> sessionInfoDTOList =new ArrayList<>();
		long noOfDays=ChronoUnit.DAYS.between(startDate, endDate);
		for(int i=1;i<=noOfDays;i++) {
			SessionInfoDTO sessionInfoDTO =new SessionInfoDTO();
			sessionInfoDTO.setFromTime(session.getFromTime());
			sessionInfoDTO.setToTime(session.getToTime());
			sessionInfoDTO.setSessionName(session.getSessionName());
			sessionInfoDTO.setWorkPlaceId(session.getWorkPlaceId());
			sessionInfoDTO.setDate(startDate.plusDays(i));
			SessionInfoDTO sessionInfo=save(sessionInfoDTO);
			sessionInfoDTOList.add(sessionInfo);
			
		}
			return null;
		
	}
    
    
    
    
    
    
    
    
}

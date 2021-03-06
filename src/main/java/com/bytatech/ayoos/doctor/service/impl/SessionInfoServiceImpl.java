package com.bytatech.ayoos.doctor.service.impl;

import com.bytatech.ayoos.doctor.service.SessionInfoService;
import com.bytatech.ayoos.doctor.domain.SessionInfo;
import com.bytatech.ayoos.doctor.domain.enumeration.SessionStatus;
import com.bytatech.ayoos.doctor.repository.SessionInfoRepository;
import com.bytatech.ayoos.doctor.repository.search.SessionInfoSearchRepository;
import com.bytatech.ayoos.doctor.service.dto.SessionInfoDTO;
import com.bytatech.ayoos.doctor.service.dto.TimingDetailDTO;
import com.bytatech.ayoos.doctor.service.mapper.SessionInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        sessionInfoSearchRepository.save(sessionInfo);
        return result;
    }

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
    @Override
    @Transactional(readOnly = true)
    public Page<SessionInfoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SessionInfos for query {}", query);
        return sessionInfoSearchRepository.search(queryStringQuery(query), pageable)
            .map(sessionInfoMapper::toDto);
    }
    
    /*
     * @author:ajay.e.s
     * Method to split the session of doctors in given dates limit
     */
    public List<SessionInfoDTO> setSessionInfosByDates(List<TimingDetailDTO> timingDetailDTO){
		List<SessionInfoDTO> sessionInfoDTOList =new ArrayList<>();
		
		for(TimingDetailDTO timingDetail:timingDetailDTO) {
			//Code to get the int value for Weekday of starting date
			 DayOfWeek dayOfWeekStart = timingDetail.getFromDate().getDayOfWeek();
			 int intValueStart=dayOfWeekStart.getValue();
			 log.debug(intValueStart+"*****\n");
			 //Code to get difference with given weekday and starting date's weekday
			 long dayDifference= timingDetail.getWeekday()-intValueStart ;
			 dayDifference=(dayDifference<0)?(7+dayDifference):dayDifference;
			 //Calculate the 1st date of given weekday after starting date
			 LocalDate startDate=timingDetail.getFromDate().plusDays(dayDifference);
			 //Loop to get all weekday in between
			 while (!startDate.isAfter(timingDetail.getToDate())) {
                  /*
				  * convertion of LocalDate and LocalTime into Instant with using String
				  */ 
				 log.debug("XXXXXXXXXXXXXXX"+startDate+"T"+timingDetail.getFromTime()+":00.00Z");
				 Instant startInstant=Instant.parse(startDate+"T"+timingDetail.getFromTime()+":00"+".00Z");
				 log.debug("FromTime:"+startInstant);
				 log.debug("WWWWWWWWWW"+startDate+"T"+timingDetail.getToTime()+":00.00Z");
				 Instant endInstant=Instant.parse(startDate+"T"+timingDetail.getToTime()+":00"+".00Z");
				 log.debug("ToTime:"+endInstant);
				
				 SessionInfoDTO sessionInfoDTO =new SessionInfoDTO();
				 sessionInfoDTO.setDate(startDate);
				 sessionInfoDTO.setFromTime(startInstant);
				 sessionInfoDTO.setToTime(endInstant);
				 sessionInfoDTO.setWorkPlaceId(timingDetail.getWorkPlaceId());
				 sessionInfoDTO.setInterval(timingDetail.getInterval());
				 sessionInfoDTO.setDoctorIdpCode(timingDetail.getDoctorIdpCode());
				 sessionInfoDTO.setSessionStatus(SessionStatus.AVAILABLE);
				 sessionInfoDTO.setWeekDay(timingDetail.getWeekday());
				  save(sessionInfoDTO);
				 sessionInfoDTOList.add(sessionInfoDTO);
				 //Calculate next weekday by adding 7
				 startDate=startDate.plusDays(7);				  
			 }
		}
 
		 return sessionInfoDTOList;
		}
 
}

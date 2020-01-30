package com.bytatech.ayoos.doctor.repository;

import com.bytatech.ayoos.doctor.domain.WorkPlace;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkPlace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Long> {

}

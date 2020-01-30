package com.bytatech.ayoos.doctor.repository;

import com.bytatech.ayoos.doctor.domain.Slot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Slot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

}

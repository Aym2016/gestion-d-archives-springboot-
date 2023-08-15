package com.sip.ams1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.sip.ams1.entities.DemandeConsultation;

@Repository
public interface DemandeConsultationRepository  extends CrudRepository<DemandeConsultation, Long> {

}

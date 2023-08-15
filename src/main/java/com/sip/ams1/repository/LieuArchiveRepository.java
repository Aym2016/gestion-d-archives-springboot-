package com.sip.ams1.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams1.entities.LieuArchive;


@Repository
public interface LieuArchiveRepository extends CrudRepository<LieuArchive, Long> {
	
	
}

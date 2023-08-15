package com.sip.ams1.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sip.ams1.entities.StructureCentrale;

@Repository
public interface StructureCentraleRepository extends CrudRepository<StructureCentrale, Long> {

}

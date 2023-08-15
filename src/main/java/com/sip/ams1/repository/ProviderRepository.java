package com.sip.ams1.repository;



//import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sip.ams1.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	
}

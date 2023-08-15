package com.sip.ams1.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sip.ams1.entities.SuiviDoc1ereAge;
//import org.springframework.data.jpa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sip.ams1.repository.SuiviDoc1ereAgeRepository;

@Repository
public interface TriRepository extends JpaRepository<SuiviDoc1ereAge, Date> {
	public static final List<SuiviDoc1ereAge> lst=new ArrayList();
	@Query(value = "select e from  SuiviDoc1ereAge e order by date_creation desc ")
	static List<SuiviDoc1ereAge> queryAll() {
		return lst;
	}
	//{
		// TODO Auto-generated method stub
		//return TriRepository.queryAll();
		
	
	
	/*public static List<SuiviDoc1ereAge> xxxMethod() {
	    return TriRepository.findAll(new Sort(ASC, "position"));
	  }*/
	
	

	//List<SuiviDoc1ereAge> findAll(Sort sort) List <SuiviDoc1ereAge> findAll (Sort sort);
	//List<SuiviDoc1ereAge> passengers = S.findAll(Sort.by(Sort.Direction.ASC, "seatNumber"));
	  // select * from entity where name = 'xxx'Équivalent à ordre par position asc
	 /* static List<SuiviDoc1ereAge> findByNameOrderByPosition(Date name) {
		// TODO Auto-generated method stub
		return null;
	} 
    

	  // select *Équivalent à de l'entité ordre par position
	  //findAllOrderByPosition n'est pas bon
	  List<SuiviDoc1ereAge> findAllByOrderByPosition();*/
		
}

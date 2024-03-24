package com.stubizsolutions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stubizsolutions.entities.City;

public interface CityRepo extends JpaRepository<City, Integer> 
{
	public List<City> findByStateId(Integer stateId);

}

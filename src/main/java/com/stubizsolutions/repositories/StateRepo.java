package com.stubizsolutions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stubizsolutions.entities.State;

public interface StateRepo extends JpaRepository<State, Integer> 
{
   public List<State> findByCountryId(Integer countryId);
}

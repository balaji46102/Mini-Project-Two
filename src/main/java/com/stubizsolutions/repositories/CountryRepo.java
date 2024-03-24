package com.stubizsolutions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stubizsolutions.entities.Country;

public interface CountryRepo extends JpaRepository<Country, Integer> {

}

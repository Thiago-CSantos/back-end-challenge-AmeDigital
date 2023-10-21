package com.thiago.desafiobackend.service;

import com.thiago.desafiobackend.model.Planeta;
import com.thiago.desafiobackend.repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicePlaneta {

     @Autowired
     PlanetaRepository repository;

     public Optional<Planeta> findById(int id){

       return repository.findById(id);

    }

}

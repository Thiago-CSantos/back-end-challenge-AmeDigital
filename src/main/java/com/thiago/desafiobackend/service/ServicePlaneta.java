package com.thiago.desafiobackend.service;

import com.thiago.desafiobackend.controller.PlanetaController;
import com.thiago.desafiobackend.dto.PlanetaDto;
import com.thiago.desafiobackend.repository.PlanetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ServicePlaneta {

    @Autowired
    PlanetaRepository repository;

    public PlanetaDto findById(int id) {
        var entity = repository.findById(id).orElseThrow();
        PlanetaDto planetaDto = new PlanetaDto(entity);

        BeanUtils.copyProperties(planetaDto, entity);
        planetaDto.add(linkTo(methodOn(PlanetaController.class).getById(planetaDto.getChave_id())).withSelfRel());

        return planetaDto;
    }

}

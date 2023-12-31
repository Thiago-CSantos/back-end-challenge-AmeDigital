package com.thiago.desafiobackend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiago.desafiobackend.dto.PlanetaDto;
import com.thiago.desafiobackend.model.Planeta;
import com.thiago.desafiobackend.repository.PlanetaRepository;
import com.thiago.desafiobackend.service.ServicePlaneta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/planetas")
public class PlanetaController {

    @Autowired
    PlanetaRepository repository;

    @Autowired
    ServicePlaneta servicePlaneta;

    @PostMapping("/add")
    public ResponseEntity<?> createPlaneta(@RequestBody PlanetaDto dados, @RequestParam int planetId) throws IOException {
        /*
         * consumindo uma API externa
         */
        URL url = new URL("https://swapi.dev/api/planets/" + planetId + "/"); //URL
        URLConnection connection = url.openConnection(); // Abrindo conexão
        InputStream inputStream = connection.getInputStream(); // aqui vem os dados da minha requisição
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String aux = "";
        StringBuilder jsonAux = new StringBuilder();

        while ((aux = br.readLine()) != null) {
            jsonAux.append(aux);
        }

        /*
         * Convertendo para JSON
         */
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonAux.toString());

        JsonNode nameNode = jsonNode.get("name");
        List<String> films = new ArrayList<>();

        // Se o 'name' recebido do nameNode for igual
        // então true
        if (Objects.equals(nameNode.asText(), dados.getNome())) {
            // colocando o campo 'films' numa lista, o retorno de jsonNode
            JsonNode filmsNode = jsonNode.get("films");
            if (filmsNode != null && filmsNode.isArray()) {
                for (JsonNode filmNode : filmsNode) {
                    films.add(filmNode.asText());
                }
            }
            System.out.println("URLs dos filmes: " + films.size() + "\n " + jsonNode.toString());
            var planeta = new Planeta(dados, films.size());
            return ResponseEntity.ok().body(repository.save(planeta));
        } else {
            System.out.println("Nome invalido");
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Nome invalido, o 'planetId' deve coincidir" +
                    " com o da API de terceiros, exemblo: https://swapi.dev/api/planets/8/ ");
        }
    }

    @GetMapping
    public ResponseEntity<List<PlanetaDto>> getAll() {
        /*
         * convertendo de Planeta para PlanetaDto
         */
        List<PlanetaDto> dtoList = repository.findAll().stream().map((p) -> new PlanetaDto(
                p.getId(), p.getNome(), p.getClima(), p.getTerreno()
        )).toList();

        for (PlanetaDto p : dtoList) {
            p.add(linkTo(methodOn(PlanetaController.class).getById(p.getChave_id())).withSelfRel());
        }

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PlanetaDto> getById(@PathVariable(value = "id") int id) {

        return ResponseEntity.ok(servicePlaneta.findById(id));
    }

    @DeleteMapping("/deletar/id/{id}")
    public ResponseEntity<?> deletePlaneta(@PathVariable(value = "id") int id) {

        var entity = repository.findById(id).orElseThrow();
        repository.delete(entity);
        return ResponseEntity.ok("Planeta deletado com sucesso");
    }
}

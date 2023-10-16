package com.thiago.desafiobackend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiago.desafiobackend.dto.PlanetaDto;
import com.thiago.desafiobackend.model.Planeta;
import com.thiago.desafiobackend.repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/planetas")
public class PlanetaController {

    @Autowired
    PlanetaRepository repository;

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
        if (Objects.equals(nameNode.asText(), dados.nome())) {
            // colocando o campo 'films' em uma lista, o retorno de jsonNode
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
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<PlanetaDto> getAll() {
        /*
         * convertendo de Planeta para PlanetaDto
         */
        List<PlanetaDto> dtoList = repository.findAll().stream().map((p) -> new PlanetaDto(
                p.getNome(), p.getClima(), p.getTerreno()
        )).toList();
        return dtoList;
    }

}

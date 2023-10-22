package com.thiago.desafiobackend.model;

import com.thiago.desafiobackend.dto.PlanetaDto;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Entity
public class Planeta extends RepresentationModel<Planeta> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String clima;
    private String terreno;
    @Column(name = "aparicoesFilmes")
    private int qtdAparicoesFilme;

    public Planeta() {
    }

    public Planeta(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

    public Planeta(PlanetaDto dados, int qtdAparicoesFilme) {
        this.id = dados.getChave_id();
        this.nome = dados.getNome();
        this.clima = dados.getClima();
        this.terreno = dados.getTerreno();
        this.qtdAparicoesFilme = qtdAparicoesFilme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planeta planeta)) return false;
        return Objects.equals(getNome(), planeta.getNome()) && Objects.equals(getClima(), planeta.getClima()) && Objects.equals(getTerreno(), planeta.getTerreno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getClima(), getTerreno());
    }
}

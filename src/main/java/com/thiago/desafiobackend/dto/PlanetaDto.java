package com.thiago.desafiobackend.dto;

import com.thiago.desafiobackend.model.Planeta;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class PlanetaDto extends RepresentationModel<Planeta> {


    private Integer chave_id;
    private String nome;
    private String clima;
    private String terreno;

    public PlanetaDto(Integer chave_id, String nome, String clima, String terreno) {
        this.chave_id = chave_id;
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

    public PlanetaDto(Planeta entity) {
        this.chave_id = entity.getId();
        this.nome = entity.getNome();
        this.clima = entity.getClima();
        this.terreno = entity.getTerreno();
    }

    public Integer getChave_id() {
        return chave_id;
    }

    public void setChave_id(Integer chave_id) {
        this.chave_id = chave_id;
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
        if (!(o instanceof PlanetaDto that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getChave_id(), that.getChave_id()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getClima(), that.getClima()) && Objects.equals(getTerreno(), that.getTerreno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getChave_id(), getNome(), getClima(), getTerreno());
    }

}

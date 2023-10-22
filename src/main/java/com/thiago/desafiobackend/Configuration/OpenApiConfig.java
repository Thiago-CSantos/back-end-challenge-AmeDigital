package com.thiago.desafiobackend.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("back-end-challenge-AmeDigital")
                        .version("v1")
                        .description("API que contém os dados dos planetas, como: nome, clima e terreno. Ao cadastrar," +
                                " é feito uma consulta (REST) a API pública do Star Wars (https://swapi.dev/) " +
                                "para saber a quantidade de aparições em filmes desse planeta.")
                        .termsOfService("https://github.com/Thiago-CSantos")
                        .license(new License()
                                .name("GPL-3.0")
                                .url("https://www.gnu.org/licenses/gpl-3.0")
                        )
                );
    }
}

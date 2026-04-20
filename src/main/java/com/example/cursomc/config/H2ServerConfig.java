package com.example.cursomc.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * Classe que força o Spring a criar a Interface Web do H2, pois com algumas atualizações que o Spring teve
 * As bibliotecas que eu estava usando no import, estavam "Desatualizadas" e esta classe ignora o Bloqueio que estava ocorrendo
 * Forçando o Spring a rodar o serviço Web corretamente
 */
@Configuration
public class H2ServerConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        // Inicia o servidor web nativo do H2 em uma porta separada (8082)
        // Isso ignora totalmente o bloqueio do jakarta/javax
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
    }
}
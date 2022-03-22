package com.example.demo.medic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MedicConfig {
    @Bean
    CommandLineRunner commandLineRunner(MedicRepository repository){
        return args -> {
    //        Medic au = new Medic(1l);
    //        repository.saveAll(repository.saveAll(List.of(au)));
        };
    }
}

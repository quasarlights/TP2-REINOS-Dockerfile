package com.neoris.turnosrotativos.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(LocalDateTime.class, LocalDate.class)
                .setConverter(context -> context.getSource() == null ? null : context.getSource().toLocalDate());

        return modelMapper;
    }
}

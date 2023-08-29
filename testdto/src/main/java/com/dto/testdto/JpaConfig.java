package com.dto.testdto;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//this file is created because i want to use the createdat and lastmodified in my model
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}

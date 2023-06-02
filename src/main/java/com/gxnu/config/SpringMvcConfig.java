package com.gxnu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.gxnu.controller","com.gxnu.config"})
@EnableWebMvc
public class SpringMvcConfig {
}

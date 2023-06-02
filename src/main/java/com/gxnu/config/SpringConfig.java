package com.gxnu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.gxnu.service","com.gxnu.mapper"})
public class SpringConfig {
}
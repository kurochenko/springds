package net.kurochenko.springds.ui.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of UI module (do not interchange for Spring MVC config)
 *
 * @author kurochenko
 */
@Configuration
@ComponentScan(basePackages = { "net.kurochenko.springds.ui" })
public class UiConfig {



}

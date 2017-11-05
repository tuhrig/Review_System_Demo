package de.tuhrig.rsd.statistic.system.infrastructure.database;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring configuration to define persistence beans like a data
 * source or an entity manager using Spring Boot.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan("de.tuhrig")
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
public class PersistenceConfig {

}

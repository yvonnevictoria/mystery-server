package com.mystery.backend

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class FlywayConfig {

//    @Bean
//    @Profile("!local")
//    fun migrationStrategy(): FlywayMigrationStrategy {
//        return FlywayMigrationStrategy { }
//    }

//    @Bean
//    @Profile("local")
//    fun localMigrationStrategy(@Value("\${flyway.enable-migrations-on-startup}") enableMigrationsOnStartup: Boolean): FlywayMigrationStrategy {
//        return FlywayMigrationStrategy {
//            if (enableMigrationsOnStartup) {
//                it.migrate()
//            }
//        }
//    }
}

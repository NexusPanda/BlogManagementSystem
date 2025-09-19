package com.Blog.BlogManagementSystem.Config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseKeepAlive {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseKeepAlive(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Run every 5 minutes (300000 ms)
    @Scheduled(fixedRate = 300000)
    public void keepAlive() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("✅ Database keep-alive query executed.");
        } catch (Exception e) {
            System.err.println("⚠️ Keep-alive query failed: " + e.getMessage());
        }
    }
}

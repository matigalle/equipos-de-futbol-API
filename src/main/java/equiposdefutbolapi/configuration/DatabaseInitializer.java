package equiposdefutbolapi.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.file.Files;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_USERNAME = "test";
    private static final String DEFAULT_PASSWORD = "12345";

    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("init.sql");
        String sql = Files.readString(resource.getFile().toPath());
        jdbcTemplate.execute(sql);

        sql = "INSERT INTO usuario (username, password) VALUES (?,?)";
        String encodedPassword = passwordEncoder.encode(DEFAULT_PASSWORD);
        jdbcTemplate.update(sql, DEFAULT_USERNAME, encodedPassword);
    }

}

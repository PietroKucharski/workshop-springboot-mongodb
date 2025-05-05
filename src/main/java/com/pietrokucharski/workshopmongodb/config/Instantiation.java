package com.pietrokucharski.workshopmongodb.config;

import com.pietrokucharski.workshopmongodb.domain.Post;
import com.pietrokucharski.workshopmongodb.domain.User;
import com.pietrokucharski.workshopmongodb.dto.AuthorDTO;
import com.pietrokucharski.workshopmongodb.repository.PostRepository;
import com.pietrokucharski.workshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post pos1 = new Post(null, LocalDate.parse("21/03/2018", dtf)
                .atStartOfDay(ZoneId.of("GMT")).toInstant(),
                "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));

        Post pos2 = new Post(null, LocalDate.parse("23/03/2018", dtf)
                .atStartOfDay(ZoneId.of("GMT"))
                .toInstant(), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(pos1, pos2));

        maria.getPosts().addAll(Arrays.asList(pos1, pos2));
        userRepository.save(maria);
    }
}

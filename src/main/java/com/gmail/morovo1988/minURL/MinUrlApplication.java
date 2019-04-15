package com.gmail.morovo1988.minURL;

import com.gmail.morovo1988.minURL.model.MiniURL;
import com.gmail.morovo1988.minURL.repository.MiniURLReposetory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MinUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinUrlApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(final MiniURLReposetory miniURLReposetory) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
for(int i=0;i<30;i++) {
    MiniURL miniURL = new MiniURL("http://google.com.ua");
    miniURLReposetory.save(miniURL);

}
            }
        };
    }

}

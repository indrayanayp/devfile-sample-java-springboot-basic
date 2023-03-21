package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@RestController
@SpringBootApplication
public class DemoApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/test1234")
    String home1() {
        return "Test1234!";
    }

    @RequestMapping("/test456")
    String home2() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("sh", "-c", "curl -v  https://www.google.com");
        try {
            Process process = processBuilder.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed!";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Failed!"
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

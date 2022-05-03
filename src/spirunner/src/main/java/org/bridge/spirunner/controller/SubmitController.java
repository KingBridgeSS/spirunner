package org.bridge.spirunner.controller;

import org.bridge.spirunner.domain.Submission;
import org.bridge.spirunner.runner.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class SubmitController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    @PostMapping("/submit")
    Results submit(@RequestBody Submission submission) throws Exception {
        return new DockerRunner(submission).run();
    }


}
package com.sairaj.jobinfra.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class LandingPageController {

    @Value("classpath:static/index.html")
    private Resource indexHtml;

    @Value("${jobinfra.app.name:JobInfra Cloud}")
    private String appName;

    @Value("${jobinfra.app.version:v1.0.0}")
    private String appVersion;

    @Value("${jobinfra.github.url:https://github.com/Sairaj182/JobInfra-Cloud}")
    private String githubUrl;

    @Value("${jobinfra.docs.url:/swagger-ui/index.html}")
    private String docsUrl;

    @Value("${jobinfra.base-url:https://api.jobinfra.dev}")
    private String baseUrl;

    private String cachedContent;

    @jakarta.annotation.PostConstruct
    public void init() {
        try {
            String content = StreamUtils.copyToString(indexHtml.getInputStream(), StandardCharsets.UTF_8);
            cachedContent = content.replace("{{jobinfra.app.name}}", appName)
                                   .replace("{{jobinfra.app.version}}", appVersion)
                                   .replace("{{jobinfra.github.url}}", githubUrl)
                                   .replace("{{jobinfra.docs.url}}", docsUrl)
                                   .replace("{{jobinfra.base-url}}", baseUrl);
        } catch (IOException e) {
            cachedContent = "Error loading landing page";
        }
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity<String> getLandingPage() {
        if ("Error loading landing page".equals(cachedContent)) {
            return ResponseEntity.internalServerError().body(cachedContent);
        }
        return ResponseEntity.ok(cachedContent);
    }
}

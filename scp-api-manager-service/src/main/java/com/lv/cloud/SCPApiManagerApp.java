package com.lv.cloud;

import com.jcabi.manifests.Manifests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@Slf4j
@SpringCloudApplication
public class SCPApiManagerApp {

    public static void main(String[] args) {

        try {
            System.out.println(String.format("BUILD VERSION: %s", Manifests.read("build-time")));
        } catch (Exception e) {
            log.debug("Not Found 'BuildVersion'.");
        }

        SpringApplication.run(SCPApiManagerApp.class, args);

    }

}


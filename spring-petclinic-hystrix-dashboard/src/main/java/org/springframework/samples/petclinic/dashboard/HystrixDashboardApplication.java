package io.quarkus.samples.petclinic.dashboard;


import io.quarkus.boot.QuarkusApplication;
import io.quarkus.boot.autoconfigure.QuarkusBootApplication;
import io.quarkus.cloud.client.discovery.EnableDiscoveryClient;
import io.quarkus.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import io.quarkus.stereotype.Controller;
import io.quarkus.web.bind.annotation.RequestMapping;

@QuarkusBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@Controller
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        QuarkusApplication.run(HystrixDashboardApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }
}

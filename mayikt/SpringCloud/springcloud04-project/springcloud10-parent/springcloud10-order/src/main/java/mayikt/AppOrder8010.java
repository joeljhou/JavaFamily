package mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//开启eurekaClient客户端 将服务注册到eureak服务端
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class AppOrder8010 {

    public static void main(String[] args) {
        SpringApplication.run(AppOrder8010.class, args);
    }

}

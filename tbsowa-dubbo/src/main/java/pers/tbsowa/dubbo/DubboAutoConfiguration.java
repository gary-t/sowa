package pers.tbsowa.dubbo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = "classpath:dubbo/dubbo-config.xml")
public class DubboAutoConfiguration {

}

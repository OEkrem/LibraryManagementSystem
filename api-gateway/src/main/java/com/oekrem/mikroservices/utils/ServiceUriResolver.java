package com.oekrem.mikroservices.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Component
public class ServiceUriResolver {

    private final Environment environment;

    public String getServiceUri(String serviceId, Class<?> serviceClass) {
        String uzanti = serviceClass.getAnnotation(RequestMapping.class).value()[0];

        for(int i = 0; i < 10; i++){
            String idKey = "spring.cloud.gateway.routes[" + i + "].id";
            String uriKey = "spring.cloud.gateway.routes[" + i + "].uri";

            String idValue = environment.getProperty(idKey);
            if (serviceId.equals(idValue)) {
                return environment.getProperty(uriKey) + uzanti;
            }
        }
        return "Service Not Found";
    }

}

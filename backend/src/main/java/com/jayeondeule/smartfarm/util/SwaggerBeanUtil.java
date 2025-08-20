package com.jayeondeule.smartfarm.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Data
@Component
public class SwaggerBeanUtil {
    @Value("${swagger-ui.ip}")
    List<String> ipList;

    public String ip() {
        StringBuilder ipAddress = new StringBuilder();

        for(Iterator<String> ip = ipList.iterator(); ip.hasNext();) {
            ipAddress.append("hasIpAddress('").append(ip.next()).append("')");
            if (ip.hasNext()) ipAddress.append(" or ");
        }

        return ipAddress.toString();
    }
}

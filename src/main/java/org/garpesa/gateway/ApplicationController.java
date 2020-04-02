package org.garpesa.gateway;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * https://github.com/dumindarw/springboot-microservices-starter/tree/master/api-service
 */
@Slf4j
@RestController
@RequestMapping("/")
public class ApplicationController {

    //private static final log log = logFactory.getlog(ApplicationController.class);

    /*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleResourceRoot() {
        log.info("==== resource_root ====");
        return createResponse();
    }
    */

    @RequestMapping(value = "/v1/resourcea", method = RequestMethod.GET)
    public String handleResourceA() {
        log.info("==== resourcea ====");
        return createResponse();
    }

    @RequestMapping(value = "/v1/resourceb", method = RequestMethod.GET)
    public String handleResourceB() {
        log.info("==== resourceb ====");
        return createResponse();
    }

    @RequestMapping(value = "/premium", method = RequestMethod.GET)
    public String handlePremiumResource() {
        return createResponse();
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String handleAdminResource() {
        return createResponse();
    }

    @RequestMapping(value = "/free", method = RequestMethod.GET)
    public String handleFreeResource() {
        return createResponse();
    }

    private String createResponse() {
        return "Access Granted";
    }
}

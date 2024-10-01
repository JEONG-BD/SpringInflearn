package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello-go"})
    public String hellobasic(){
        log.info("basic");
        return "OK";
    }

    @RequestMapping(value = "/mappingGetV1", method = RequestMethod.GET)
    public String mappingV1(){
        log.info("mapping-get v1");
        return "OK";
    }

    @GetMapping(value = "/mappingGetV2")
    public String mappingV2(){
        log.info("mapping-get v2");
        return "OK";
    }

    @GetMapping(value = "/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "OK";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath  userId={} orderId={}", userId, orderId);
        return "OK";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "OK";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "OK";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "OK";
    }


    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "OK";
    }

}

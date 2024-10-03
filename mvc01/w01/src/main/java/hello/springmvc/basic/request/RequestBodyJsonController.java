package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("requestBodyJson V1");
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("helloData = {}",helloData);

        response.getWriter().write("OK");

    }

    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("requestBodyJson V2");
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("helloData = {}",helloData);

        return "OK";

    }

    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData messageBody) throws IOException {
        log.info("requestBodyJson V3");
        log.info("messageBody = {}", messageBody);

        return "OK";

    }

    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> messageBody) throws IOException {
        HelloData body = messageBody.getBody();
        log.info("requestBodyJson V3");
        log.info("messageBody = {} {}", body.getUsername(), body.getAge());

        return "OK";

    }

    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException {
        log.info("requestBodyJson V5");
        log.info("messageBody = {} {}", data.getUsername(), data.getAge());

        return data;

    }



}

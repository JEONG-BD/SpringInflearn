package com.example.w00.web;

import com.example.w00.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    public void login(String testId) {
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("test");
        System.out.println(testId);
    }
}

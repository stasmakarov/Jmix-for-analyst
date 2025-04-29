package com.company.jmixforanalyst.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static java.time.LocalTime.now;

@Component
public class VariableService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
    private static final int LENGTH = 255;
    private static final SecureRandom random = new SecureRandom();

    public void createVariable(DelegateExecution execution) {

        Long counter = (Long) execution.getVariable("counter");
        String varName = "var" + counter;

        StringBuilder sb = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        try {
            LocalTime start = now();
            execution.setVariable(varName, sb.toString());
            LocalTime finish = now();
            long millis = ChronoUnit.NANOS.between(start, finish);
            System.out.println("Var #" + counter + " created in " + millis + " nanoseconds");
        } catch (Exception e) {
            throw new BpmnError("LIMIT");
        }
    }
}

package de.joergi.umlspringstatemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@Slf4j
@SpringBootApplication
public class UmlSpringStateMachineApplication implements CommandLineRunner {

//    @Autowired
//    private StateMachine<String, String> stateMachineOne;

    @Autowired
    private StateMachine<String, String> stateMachineTwo;

    public static void main(String[] args) {
        SpringApplication.run(UmlSpringStateMachineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//    	synchronized (stateMachineOne) {
//    		 stateMachineOne.getExtendedState().getVariables().put("foo", "machine1");
//    	     stateMachineOne.start();
//		}
       
        synchronized (stateMachineTwo) {
//            if(stateMachineOne.isComplete()) {
//                stateMachineTwo.getExtendedState().getVariables().put("foo", (String)stateMachineOne.getExtendedState().getVariables().get("foo"));
            log.info("start");
            boolean step = false;
            stateMachineTwo.start();

            log.info("Transitions: ");
            stateMachineTwo.getTransitions().stream().forEach( stringStringTransition -> log.info("Transition: " + stringStringTransition.getSource()));

            step = stateMachineTwo.sendEvent("SI2M");
            log.info("I2M " + step);

            step = stateMachineTwo.sendEvent("SM2M");
            log.info("M2M " + step);

            step = stateMachineTwo.sendEvent("SM2M");
            log.info("M2M " + step);

            step = stateMachineTwo.sendEvent("SM2M");
            log.info("M2M " + step);

            Thread.sleep(30000);

            step = stateMachineTwo.sendEvent("SE2M");
            log.info("E2M " + step);

            Thread.sleep(10000);
		}
        
    }
}

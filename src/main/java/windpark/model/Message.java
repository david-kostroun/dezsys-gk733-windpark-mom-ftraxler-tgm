package windpark.model;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import windpark.Gk73Application;
import windpark.mom.WindengineSimulation;

public class Message {

    public void run(){

        while (true) {
            try {
                WindengineData data = new WindengineSimulation().getData("1");
                ConfigurableApplicationContext context = SpringApplication.run(Gk73Application.class);

                JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

                // Send a message with a POJO - the template reuse the message converter
                System.out.println("Sending Windengine Data");
                jmsTemplate.convertAndSend("windengine", data);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

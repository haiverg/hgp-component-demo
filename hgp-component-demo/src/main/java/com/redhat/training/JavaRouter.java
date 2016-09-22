package com.redhat.training;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class JavaRouter {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.start();
		context.addRoutes(new JavaRouteBuilder());
		ProducerTemplate template = context.createProducerTemplate();
		Object body = template.requestBody("direct:input", "10 N2PENCIL 0.15 1.50");
		Thread.sleep(5000);
		context.stop();
		System.out.println(body);
				
	}

}

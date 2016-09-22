package com.redhat.training;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

public class JavaRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:input").to(ExchangePattern.InOut, "jms:validate");
		from("jms:validate").to("file:out");
	}

}

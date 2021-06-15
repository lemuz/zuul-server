package com.example.proxyapigateway;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//@EnableCircuitBreaker
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ProxyApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApiGatewayApplication.class, args);
	}
	
	@Bean
	  public SimpleFilter simpleFilter() {
	    return new SimpleFilter();
	  }

	public class SimpleFilter extends ZuulFilter {

		  @Override
		  public String filterType() {
		    return "pre";
		  }

		  @Override
		  public int filterOrder() {
		    return 1;
		  }

		  @Override
		  public boolean shouldFilter() {
		    return true;
		  }

		  @Override
		  public Object run() {
		    RequestContext ctx = RequestContext.getCurrentContext();
		    HttpServletRequest request = ctx.getRequest();
//		    HttpServletResponse response = ctx.getResponse();
//		    String token = response.getHeader("Authorization");
//		    token.replaceAll("Bearer ", "");
		    System.out.println(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
//		    response.getWriter().print("");
		    return null;
		  }

		}
}

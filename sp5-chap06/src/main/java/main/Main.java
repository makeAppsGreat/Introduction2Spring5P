package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import config.AppCtx;
import spring.Client;
import spring.Client2;

public class Main {

  public static void main(String[] args) {
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
    System.out.println(">> 1");
    
    Client client = ctx.getBean(Client.class);
    client.send();
    
    Client client2 = ctx.getBean(Client.class);
    client2.send();
    
    Client2 c2 = ctx.getBean(Client2.class);
    c2.send();

    System.out.println(">> 2");
    ctx.close();

    System.out.println(">> 3");
  }

}

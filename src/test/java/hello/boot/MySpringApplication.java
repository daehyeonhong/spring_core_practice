package hello.boot;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import java.util.List;

public class MySpringApplication {
    public static void run(Class configClass, String[] args) {
        System.out.println("MySpringApplication.main" + List.of(args));

        // tomcat 설정
        final Tomcat tomcat = new Tomcat();
        final Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        // Spring Container 생성
        final AnnotationConfigWebApplicationContext annotationConfigApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigApplicationContext.register(configClass);

        // Spring Mvc Dispatcher Servlet 생성, Spring Container 연결
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigApplicationContext);

        // Dispatcher Servlet 등록
        final Context context = tomcat.addContext("", "/");
        tomcat.addServlet("", "dispatcher", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcher");
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}

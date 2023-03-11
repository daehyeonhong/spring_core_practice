package hello.embed;

import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbedTomcatSpringMain {
    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatSpringMain.main");

        // tomcat 설정
        final Tomcat tomcat = new Tomcat();
        final Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        // Spring Container 생성
        final AnnotationConfigWebApplicationContext annotationConfigApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigApplicationContext.register(HelloConfig.class);

        // Spring Mvc Dispatcher Servlet 생성, Spring Container 연결
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigApplicationContext);

        // Dispatcher Servlet 등록
        final Context context = tomcat.addContext("", "/");
        tomcat.addServlet("", "dispatcher", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcher");
        tomcat.start();
    }
}

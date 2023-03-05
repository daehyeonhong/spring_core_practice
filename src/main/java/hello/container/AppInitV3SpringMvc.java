package hello.container;

import hello.spring.config.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV3SpringMvc implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        System.out.println("AppInitV3SpringMvc.onStartup");
        // Spring Container 생성
        final AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(HelloConfig.class);

        // Spring MVC Dispatcher Servlet 생성, Spring Container 연결
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);

        // DispatcherServlet Servlet Container 등록 (이름 주의! dispatcherV3)
        final ServletRegistration.Dynamic dispatcherV3 = servletContext.addServlet("dispatcherV3", dispatcherServlet);

        // /* 요청이 DispatcherServlet을 통하도록 설정
        dispatcherV3.addMapping("/");
    }
}

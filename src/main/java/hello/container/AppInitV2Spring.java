package hello.container;

import hello.spring.config.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV2Spring implements AppInit {
    @Override
    public void onStartup(final ServletContext servletContext) {
        System.out.println("AppInitV2Spring.onStartup");

        // Spring Container 생성
        final AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(HelloConfig.class);

        // Spring MVC Dispatcher Servlet 생성, Spring Container 연결
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);

        // DispatcherServlet Servlet Container 등록 (이름 주의! dispatcherV2)
        final ServletRegistration.Dynamic dispatcherV2 = servletContext.addServlet("dispatcherV2", dispatcherServlet);

        // /spring/* 요청이 DispatcherServlet을 통하도록 설정
        dispatcherV2.addMapping("/spring/*");
    }
}

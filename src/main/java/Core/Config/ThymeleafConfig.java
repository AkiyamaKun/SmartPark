package Core.Config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("Core")
public class ThymeleafConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    @Bean
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        Map<String, Object> map = new LinkedHashMap<>();
        ResourceHttpRequestHandler resourceHttpRequestHandler = new ResourceHttpRequestHandler();
        List<Resource> locations = new ArrayList<>();
//        locations.add(new ServletContextResource(getServletContext(), "/"));
        locations.add(new ClassPathResource("META-INF/resources"));
        locations.add(new ClassPathResource("resources/"));
        locations.add(new ClassPathResource("static/"));
        locations.add(new ClassPathResource("public/"));
        resourceHttpRequestHandler.setLocations(locations);
        resourceHttpRequestHandler.setApplicationContext(applicationContext);

        List<ResourceResolver> resourceResolvers = new ArrayList<>();
        PathResourceResolver resourceResolver = new PathResourceResolver();
//        resourceResolver.setAllowedLocations(new ServletContextResource(getServletContext(), "/"), new ClassPathResource("META-INF/resources"), new ClassPathResource("resources/"), new ClassPathResource("static/"), new ClassPathResource("public/"));
        resourceResolvers.add(resourceResolver);

        resourceHttpRequestHandler.setResourceResolvers(resourceResolvers);
        map.put("/**", resourceHttpRequestHandler);
        simpleUrlHandlerMapping.setUrlMap(map);
        ResourceUrlProvider resourceUrlProvider = new ResourceUrlProvider();
        Map<String, ResourceHttpRequestHandler> handlerMap = new LinkedHashMap<>();
        handlerMap.put("/**", resourceHttpRequestHandler);
        resourceUrlProvider.setHandlerMap(handlerMap);
        ResourceUrlProviderExposingInterceptor interceptor = new ResourceUrlProviderExposingInterceptor(resourceUrlProvider);
        simpleUrlHandlerMapping.setInterceptors(new Object[]{interceptor});
        return simpleUrlHandlerMapping;
    }

}
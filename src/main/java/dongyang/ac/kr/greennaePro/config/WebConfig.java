package dongyang.ac.kr.greennaePro.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // C:/study/upload/
        registry
                .addResourceHandler("/upload/** ")
                .addResourceLocations("file:///"+uploadPath)
                .setCachePeriod(60*10*3) // 30분 캐시
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

        System.out.println("리소스 핸들러 호출");
    }
}


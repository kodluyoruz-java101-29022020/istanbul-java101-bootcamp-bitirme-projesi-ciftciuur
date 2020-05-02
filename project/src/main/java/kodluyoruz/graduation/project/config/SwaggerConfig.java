package kodluyoruz.graduation.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("kodluyoruz.graduation.project"))
				.paths(PathSelectors.regex("/api.*")).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Merhabalar otomasyonda varolan api'ler ",
				"Bu güzel bilgileri aktaran sayın Batuhan Düzgün'e sonsuz teşekkürler <3", "v1", "localhost:8080",
				new Contact("Uğur Çiftçi", "https://github.com/ciftciuur", "ugurprog@gmail.com"), "", "");

		return apiInfo;
	}

}

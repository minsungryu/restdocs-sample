package me.minsungryu.restdocs.configuration;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;

@Configuration
public class RestDocsConfiguration implements RestDocsMockMvcConfigurationCustomizer {

	@Override
	public void customize(MockMvcRestDocumentationConfigurer configurer) {
		configurer.operationPreprocessors()
			.withRequestDefaults(prettyPrint())
			.withResponseDefaults(prettyPrint());
	}

}

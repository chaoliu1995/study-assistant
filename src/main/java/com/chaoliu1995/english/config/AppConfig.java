package com.chaoliu1995.english.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * 总的配置类
 * @author Administrator
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.chaoliu1995.english" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = { "com.chaoliu1995.english.config.*" })
		})
@Import({ DataConfig.class })
public class AppConfig {
	
	
}

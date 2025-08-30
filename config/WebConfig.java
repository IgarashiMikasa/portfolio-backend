package com.example.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// CORS設定
// フロントエンド(localhost:5173)からアクセス許可
@Configuration
public class WebConfig implements WebMvcConfigurer{
		// CORS設定の追加
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			// 全てのパスに適用
			registry.addMapping("/**")
			// 許可するオリジン
			.allowedOrigins("http://localhost:5173")
			// 許可するHTTPメソッド
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			// 全てのヘッダーを許可
			.allowedHeaders("*")
			// Cookieなどの認証情報の送信を許可
			.allowCredentials(true);
	}
}

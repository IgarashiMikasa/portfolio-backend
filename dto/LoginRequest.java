package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;
// ログイン時のリクエストデータを表すDTO
@Data
// paswordはtoString出力から除外
@ToString(exclude = "password")
public class LoginRequest {
	// ログインID
	@NotBlank(message="ログインIDを入力してください")
	private String loginId;
	// パスワード
	@NotBlank(message="パスワードを入力してください")
    private String password;
}

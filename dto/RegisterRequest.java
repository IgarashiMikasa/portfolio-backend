package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;
// ユーザー登録時にクライアントから受け取るデータのDTO
// バリデーションで空文字の防止
@Data
// passwordはログに出さない
@ToString(exclude = "password")
public class RegisterRequest {
	// ユーザーの名前
	@NotBlank(message = "名前を入力してください")
	private String name;
	// ユーザーのログインID
	@NotBlank(message = "ログインIDを入力してください")
    private String loginId;
	// ユーザーのパスワード
	@NotBlank(message = "パスワードを入力してください")
    private String password;
}

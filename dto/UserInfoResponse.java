package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
// クライアントに返却するユーザー情報のレスポンスDTO
// パスワードなど機密情報は含まない
@Data
@AllArgsConstructor
public class UserInfoResponse {
	// ユーザー名
	private String name;
	// ログインID
    private String loginId;
}

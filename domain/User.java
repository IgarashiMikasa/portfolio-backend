package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;
// ユーザー情報
@Data
public class User {
	// ユーザーID
	private Integer id;
	// ユーザー名
    private String name;
    // ログインID
    private String loginId;
    // パスワード
    private String loginPass;
    // 最終ログイン日時
    private LocalDateTime lastLoginAt;
    // 最終ログアウト日時
    private LocalDateTime lastLogoutAt;

}

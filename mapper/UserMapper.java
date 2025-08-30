package com.example.app.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.User;
// ユーザー情報を操作
@Mapper
public interface UserMapper {
	// ログインIDからユーザー情報を取得
	User findByLoginId(String loginId);
	// 新規ユーザーを登録
    void insertUser(User user);
    // 最終ログイン日時を更新
    void updateLastLoginAt(@Param("loginId") String loginId, @Param("loginTime") LocalDateTime loginTime);
    // 最終ログアウト日時を更新
    void updateLastLogoutAt(@Param("loginId") String loginId, @Param("lastLogoutAt") LocalDateTime lastLogoutAt);
}

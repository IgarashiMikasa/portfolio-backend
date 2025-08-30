package com.example.app.service;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.app.domain.User;
import com.example.app.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper usermapper;

	public boolean register(String name, String loginId, String rawPassword) {
        if (usermapper.findByLoginId(loginId) != null) {
            // 既に同じloginIdのユーザーが存在する場合は登録不可
        	return false;
        }
        // パスワードをハッシュ化
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        User user = new User();
        user.setName(name);
        user.setLoginId(loginId);
        user.setLoginPass(hashed);
        // DBにユーザーを挿入
        usermapper.insertUser(user);
        return true;
    }
	// ログイン認証
    public boolean authenticate(String loginId, String rawPassword) {
        User user = usermapper.findByLoginId(loginId);
        return user != null && BCrypt.checkpw(rawPassword, user.getLoginPass());
    
    }
    // LoginIdからユーザー情報を取得
    public User findByLoginId(String loginId) {
        return usermapper.findByLoginId(loginId);
    }
    // ユーザーの最終ログイン日時を更新
    public void updateLastLoginAt(String loginId, LocalDateTime loginTime) {
        usermapper.updateLastLoginAt(loginId, loginTime);
    }
    // ユーザーの最終ログアウト日時を現在時刻で更新
    public void updateLogoutTime(String loginId) {
        usermapper.updateLastLogoutAt(loginId, LocalDateTime.now());
    }
}
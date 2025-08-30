package com.example.app.controller;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.User;
import com.example.app.dto.LoginRequest;
import com.example.app.dto.RegisterRequest;
import com.example.app.dto.UserInfoResponse;
import com.example.app.service.UserService;

import lombok.RequiredArgsConstructor;
// 認証・ユーザー情報管理用のRESTコントローラ
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class AuthController {
	
	
	private final UserService userservice;
	// ログイン中のユーザー情報を取得するAPI
	@GetMapping("/me")
	public ResponseEntity<Object> me(HttpSession session) {
	    String loginId = (String) session.getAttribute("loginId");
	    if (loginId != null) {
	    	// DBからユーザー情報を取得
	        User user = userservice.findByLoginId(loginId); // User情報取得
	        if (user != null) {
	            // パスワードなどの機密情報は返さず、必要な情報だけDTOで返す
	            return ResponseEntity.ok(new UserInfoResponse(user.getName(), user.getLoginId()));
	        }
	    }
	    // ログインしていなければ401 Unauthorizedを返す
	    return ResponseEntity.status(401).body("未ログイン");
	}
	// 新規ユーザー登録API
	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest req,BindingResult bindingresult){
		
		if (bindingresult.hasErrors()) {
	        // 最初のエラーメッセージを取得して返す例
	        String errorMessage = bindingresult.getFieldError().getDefaultMessage();
	        return ResponseEntity.badRequest().body(errorMessage);
	    }
		
		boolean success=userservice.register(req.getName(), req.getLoginId(), req.getPassword());
		if (success) {
            return ResponseEntity.ok("登録成功");
        } else {
            return ResponseEntity.badRequest().body("ログインIDが既に存在します");
        }
	}
	// ログインAPI
	// 認証成功時にセッションにloginIdを保持し、最終ログイン日時をDBに保存
	@PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest req,BindingResult bindingresult,HttpSession session,HttpServletRequest request) {
		if (bindingresult.hasErrors()) {
	        // 最初のエラーメッセージを取得して返す例
	        String errorMessage = bindingresult.getFieldError().getDefaultMessage();
	        return ResponseEntity.badRequest().body(errorMessage);
	    }
		
		boolean auth = userservice.authenticate(req.getLoginId(), req.getPassword());
        if (auth) {
        	request.changeSessionId();
        	session.setAttribute("loginId", req.getLoginId());
        	// 最終ログイン日時更新
        	userservice.updateLastLoginAt(req.getLoginId(), LocalDateTime.now());
            return ResponseEntity.ok("ログイン成功");
        } else {
            return ResponseEntity.status(401).body("認証失敗");
        }
	}
	// ログアウトAPI
	//セッション破棄前に最終ログアウト日時をDBに保存
	@PostMapping("/logout")
	public ResponseEntity<Object> logout(HttpSession session){
	String loginId = (String) session.getAttribute("loginId");
	if (loginId != null) {
        // 最終ログアウト日時更新
		userservice.updateLogoutTime(loginId);
    }
	// セッション破棄でログアウト   
	session.invalidate();
	return ResponseEntity.ok("ログアウト成功");
	}
}

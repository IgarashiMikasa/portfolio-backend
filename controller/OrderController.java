package com.example.app.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.OrderHistoryResponse;
import com.example.app.dto.OrderRequest;
import com.example.app.service.OrderService;

import lombok.RequiredArgsConstructor;
// 注文処理用のRESTコントローラ
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	 private final OrderService orderservice;
	// 注文を受け付けるAPI
	// ログイン状態をセッションから確認し、未ログインなら401を返す
	// 注文内容をサービス層に渡して登録
	@PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest,HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		
		if (loginId == null) {
			logger.warn("未ログインのユーザーが注文を試みました");
            return ResponseEntity.status(401).body("未ログインです");
        }
		
		// 注文内容の妥当性チェック（簡易例）
	     if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
			logger.warn("注文内容が空です: loginId={}", loginId);
			return ResponseEntity.badRequest().body("注文内容が空です");
	     }
		
		// 注文登録処理
		orderservice.createOrder(loginId, orderRequest);
		// ログ出力(注文受付)
		logger.info("注文受付: loginId={}", loginId);
        for (OrderRequest.OrderItem item : orderRequest.getItems()) {
        	logger.info("商品ID: {}, 数量: {}", item.productId, item.quantity);
        }

        return ResponseEntity.ok("注文完了");
    }
	
	// ログインユーザーの注文履歴取得API
	@GetMapping("/history")
	public ResponseEntity<?> getOrderHistory(HttpSession session) {
	    String loginId = (String) session.getAttribute("loginId");
	    if (loginId == null) {
	        return ResponseEntity.status(401).body("未ログインです");
	    }
	    List<OrderHistoryResponse> history = orderservice.getOrderHistory(loginId);
	    return ResponseEntity.ok(history);
	}
}

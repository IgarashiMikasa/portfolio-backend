package com.example.app.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.example.app.dto.OrderRequest.OrderItem;

import lombok.Data;
// 注文情報
@Data
public class Order {
	// 注文ID
	private Integer id;
	// ログイン中のユーザーID
	private String loginId; 
	// 注文日時
    private LocalDateTime orderDate;
    // 注文アイテムのリスト
    private List<OrderItem> items;

}

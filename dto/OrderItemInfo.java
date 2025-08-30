package com.example.app.dto;

import lombok.Data;

@Data
public class OrderItemInfo {
	// 商品ID
	private Integer productId;
	// 商品名
    private String productName;
    // 注文数量
    private int quantity;
    // 商品価格（単価）
    private int price; 

}

package com.example.app.domain;

import lombok.Data;
// 注文内の商品1件分の情報
@Data
public class OrderItem {
	//商品ID
	private Integer productId;
	// 注文数量
    private Integer quantity;

}

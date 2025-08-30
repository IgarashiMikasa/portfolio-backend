package com.example.app.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
//注文履歴の注文詳細DTO
@Data
public class OrderHistoryResponse {
	 // 注文の識別ID
	 private Integer orderId;
	 // 注文日時
	 private LocalDateTime orderDate;
	 // 注文された商品のリスト
	 private List<OrderItemInfo> items;
}


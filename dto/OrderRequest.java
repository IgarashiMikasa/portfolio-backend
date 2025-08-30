package com.example.app.dto;

import java.util.List;

import lombok.Data;
// クライアントからの注文リクエストを表すDTO
@Data
public class OrderRequest {
	// 注文内の個々の商品(明細)を表すクラス
	@Data
	public static class OrderItem {
        // 注文する商品のID
		public Integer productId;
        // 注文する商品の数量
        public int quantity;
    }
	// 注文商品リスト
	public List<OrderItem> items;

}

package com.example.app.domain;

import lombok.Data;
// 商品情報
@Data
public class Product {
	// 商品ID
	private Integer id;
	// 商品名
	private String name;
	// 商品価格
	private Integer price;
	// カテゴリID
	private Integer categoryId;
	// カテゴリ名(表示用)
	private String categoryName;

}

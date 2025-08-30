package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Product;
import com.example.app.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductMapper productmapper;
	// 全ての商品を取得
	public List<Product> getAllProducts(){
		return productmapper.findAll();
	}
	// 指定IDの商品情報を取得
	public Product getProductById(Integer id) {
		return productmapper.findById(id);
	}
	
	// キーワード・カテゴリIDによる商品検索
		public List<Product> findProducts(String keyword, Integer categoryId) {
			// ここでproductmapperの検索用メソッドを呼び出す
			return productmapper.findByKeywordAndCategory(keyword, categoryId);
		}
		public List<Product> findSuggestions(String keyword) {
		    return productmapper.findSuggestions(keyword);
		}	

}

package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Product;
// 商品情報を操作
@Mapper
public interface ProductMapper {
	// 全ての商品を取得
	List<Product> findAll();
	// IDで商品を取得
	Product findById(Integer id);
	
	// キーワードとカテゴリIDで商品を検索
		List<Product> findByKeywordAndCategory(
			@Param("keyword") String keyword,
			@Param("categoryId") Integer categoryId
		);
		
		List<Product> findSuggestions(@Param("keyword") String keyword);

}

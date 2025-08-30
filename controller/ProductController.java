package com.example.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.Product;
import com.example.app.service.ProductService;

import lombok.RequiredArgsConstructor;
// 商品に関するAPIコントローラ
@RestController
// フロントエンドのlocalhostポートを許可
@CrossOrigin(origins = "http://localhost:5173")
// productsに対するリクエストを受け付ける
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productservice;
	
	// 指定されたIDの商品情報を取得するAPI
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Integer id) {
	    return productservice.getProductById(id);
	}
	
	@GetMapping
	public List<Product> getProducts(
	    @RequestParam(required = false) String keyword,
	    @RequestParam(required = false) Integer categoryId) {
		if (keyword == null && categoryId == null) {
		return productservice.getAllProducts();
		}
	    return productservice.findProducts(keyword, categoryId);
	}
	@GetMapping("/suggestions")
	public List<Product> suggestProducts(@RequestParam String keyword) {
	    return productservice.findSuggestions(keyword);
	}

}

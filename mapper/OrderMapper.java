package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Order;
import com.example.app.dto.OrderHistoryResponse;
import com.example.app.dto.OrderItemInfo;
// 注文情報・注文商品の登録
@Mapper
public interface OrderMapper {
	// 注文情報をordersテーブルに挿入
	// 挿入後、orderオブジェクトのIDが設定
	int insertOrder(Order order);
	
	// 注文商品の明細(どの商品を何個注文したか)を
	// order_itemsテーブルに挿入する
	int insertOrderItem(@Param("orderId") 
	        Integer orderId,
			@Param("productId") Integer productId, 
            @Param("quantity") int quantity);
	
	// ユーザーの注文履歴一覧を取得（注文ID・注文日時のみ）
    List<OrderHistoryResponse> 
    findOrdersByLoginId(@Param("loginId") 
    String loginId);

    // 注文IDに紐づく注文商品の詳細を取得
    List<OrderItemInfo> 
    findOrderItemsByOrderId(@Param("orderId") 
    Integer orderId);

}

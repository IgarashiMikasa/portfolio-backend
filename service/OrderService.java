package com.example.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Order;
import com.example.app.dto.OrderHistoryResponse;
import com.example.app.dto.OrderItemInfo;
import com.example.app.dto.OrderRequest;
import com.example.app.mapper.OrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderMapper ordermapper;
	// 新規注文を登録
	// トランザクション管理され、
	// 注文と注文商品の登録をまとめて行う
	
	//複数のDB操作をひとつの処理としてまとめて管理
	@Transactional 
	public Integer createOrder(String loginId,OrderRequest orderrequest) {
		Order order= new Order();
	    order.setLoginId(loginId);           // loginIdをセット
	    order.setOrderDate(LocalDateTime.now());
	    
	    // ordersテーブルへ注文登録
		ordermapper.insertOrder(order);
		
		// order_itemsテーブルへ複数の注文商品を登録
		for(OrderRequest.OrderItem item: orderrequest.getItems()) {
			ordermapper.insertOrderItem(order.getId(), item.getProductId(), item.getQuantity());
		}
		
		// ここまでエラーがなければDBへ確定(commit)
	    // もし途中で例外発生すればロールバックされてDBに何も登録されない
		return order.getId();
	}
	
	// 注文履歴取得
    public List<OrderHistoryResponse> getOrderHistory(String loginId) {
        List<OrderHistoryResponse> orders = ordermapper.findOrdersByLoginId(loginId);
        for(OrderHistoryResponse order : orders) {
            List<OrderItemInfo> items = ordermapper.findOrderItemsByOrderId(order.getOrderId());
            order.setItems(items);
        }
        return orders;
    }

}

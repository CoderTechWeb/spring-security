package com.techweb.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	private int productId;
	private String name;
	private int qty;
	private int price;
	
	public ProductBuilder productId(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.techweb.spring.security.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techweb.spring.security.entity.UserInfo;
import com.techweb.spring.security.model.Product;
import com.techweb.spring.security.repository.UserInfoRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {
	
	@Autowired
	private UserInfoRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	List<Product> productList = null;
	
	@PostConstruct
	public void loadProductsFromDB() {
		productList = IntStream.range(1, 100)
		.mapToObj(i -> Product.builder()
			.productId(i)
			.name("product " + i)
			.qty(new Random().nextInt(10))
			.price(new Random().nextInt(5000)).build()
			).collect(Collectors.toList());
	}

	public List<Product> getAllProducts() {
		return productList;
	}

	public Product getProductbyId(long id) {
		 return productList.stream()
	                .filter(product -> product.getProductId() == id)
	                .findAny()
	                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
	}
	
	public String adduser(UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "user added to system";
	}

}

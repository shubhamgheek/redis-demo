package com.shubham.redisDemo.controller;

import com.shubham.redisDemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final String PREFIX = "product_";

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody Product product){
        String key = PREFIX + product.getId();
        redisTemplate.opsForValue().set(key, product);
        return ResponseEntity.ok(product.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        String key = PREFIX + id;
        Product product = (Product) redisTemplate.opsForValue().get(key);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/addToList")
    public ResponseEntity<Long> addProducts(@RequestBody Product product){
        String key = "product_list";
        redisTemplate.opsForList().leftPush(key, product);
        return ResponseEntity.ok(product.getId());
    }

    @GetMapping("/getFromList")
    public ResponseEntity<Product> getProducts(){
        String key = "product_list";
        Product result = (Product) redisTemplate.opsForList().leftPop(key);
        return ResponseEntity.ok(result);
    }
}

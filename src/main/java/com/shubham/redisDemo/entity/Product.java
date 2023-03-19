package com.shubham.redisDemo.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Product implements Serializable {
    private Long id;
    private String name;
    private Double price;
}

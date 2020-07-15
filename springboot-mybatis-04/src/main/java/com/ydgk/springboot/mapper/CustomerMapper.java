package com.ydgk.springboot.mapper;

import com.ydgk.springboot.entities.Customer;

import java.util.List;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-15 11:51
 */
public interface CustomerMapper {

    List<Customer> getAll();

}

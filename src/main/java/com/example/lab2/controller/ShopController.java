package com.example.lab2.controller;

import com.example.lab2.domain.Customer;
import com.example.lab2.services.ShopService;
import com.example.lab2.services.springData.ShopServiceSD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/shop")
public class ShopController {

//    @Autowired
//    ShopService shopService;

    @Autowired
    ShopServiceSD shopService;

    @PostMapping("/register")
    public ResponseEntity registerCustomer(@RequestBody Customer customer) {
        String result = this.shopService.registrate(
                customer.getName(),
                customer.getLogin(),
                customer.getPassword(),
                customer.getBirth());

        return new ResponseEntity(result, HttpStatus.CREATED);
    };

    @PostMapping("/{customerName}/{goodName}")
    public ResponseEntity<String> makeOrder(
            @PathVariable(name="customerName") String customerName,
            @PathVariable(name="goodName")String goodName) {
        return ResponseEntity.ok(this.shopService.makeOrder(customerName, goodName));
    }

}

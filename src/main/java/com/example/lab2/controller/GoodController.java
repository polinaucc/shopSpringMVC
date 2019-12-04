package com.example.lab2.controller;

import com.example.lab2.domain.Good;
import com.example.lab2.services.GoodService;
import com.example.lab2.services.springData.GoodServiceSD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/goods")
public class GoodController {
//    @Autowired
//    GoodService goodService;

    @Autowired
    GoodServiceSD goodService;

    @GetMapping("/")
    public List<Good> getAllGoods() {
        return this.goodService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getGoodById(@PathVariable(name="id") int id) {
        return this.goodService.findById(id)
                .map(good -> ResponseEntity.ok(good))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Good> createGood(@RequestBody Good good) {
        Good createdGood = this.goodService.createGood(good);

        return new ResponseEntity<Good>(createdGood, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGood(@PathVariable(name="id") int id) {
        boolean removed = this.goodService.deleteGooDById(id);

        if(removed) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateGood(@RequestBody Good good, @PathVariable(name="id") int id) {
        boolean updated = this.goodService.updateGoodById(good,id);

        if(updated) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

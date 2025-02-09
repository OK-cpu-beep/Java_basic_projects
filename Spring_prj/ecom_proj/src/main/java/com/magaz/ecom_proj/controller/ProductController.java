package com.magaz.ecom_proj.controller;

import com.magaz.ecom_proj.model.Product;
import com.magaz.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService PS;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProdiucts(){
        return new ResponseEntity<>(PS.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> GetProdiuctById(@PathVariable int prodId){
        Product p = PS.getProductById(prodId);
        if(p != null){
            return  new ResponseEntity<>(p,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                              @RequestPart MultipartFile imageFile
                                              ){
        try{
            Product product1 = PS.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{ProductId}/image")
    public ResponseEntity<?> getImageByProductId(@PathVariable int ProductId){
        try{
            Product product = PS.getProductById(ProductId);
            byte[] image = PS.getProductById(ProductId).getImageData();
            return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(image);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/product/{prodId}")
    public ResponseEntity<?> updateProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile
    ){
        try{
            Product product1 = PS.updateProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<?> DeleteProductById(@PathVariable int prodId){
        Product p = PS.getProductById(prodId);
        if(p != null){
            PS.deleteProductById(prodId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = PS.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



}

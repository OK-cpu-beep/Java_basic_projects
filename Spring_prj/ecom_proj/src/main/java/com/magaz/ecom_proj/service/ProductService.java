package com.magaz.ecom_proj.service;

import com.magaz.ecom_proj.model.Product;
import com.magaz.ecom_proj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@Service
public class ProductService {

    public ProductRepository getRepo() {
        return repo;
    }

    @Autowired
    ProductRepository repo;


    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProductById(int prodId){
        return repo.findById(prodId).orElse(null);
    }

    public Product addProduct(Product prod, MultipartFile image) throws IOException {
        prod.setImageData(image.getBytes());
        prod.setImageName(image.getOriginalFilename());
        prod.setImageType(image.getContentType());
        return repo.save(prod);
    }

    public Product updateProduct(Product product, MultipartFile image) throws IOException{
        product.setImageData(image.getBytes());
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        return repo.save(product);
    }

    public void deleteProductById(int prodId){
        repo.deleteById(prodId);
    }


    public List<Product> searchProducts(String keyword){
        return repo.searchProducts(keyword);
    }
}

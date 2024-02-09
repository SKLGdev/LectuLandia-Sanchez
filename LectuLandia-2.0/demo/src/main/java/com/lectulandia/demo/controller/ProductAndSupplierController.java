package com.lectulandia.demo.controller;

import com.lectulandia.demo.entity.Product;
import com.lectulandia.demo.entity.SupplierData;
import com.lectulandia.demo.service.ProductAndSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products_suppliers")
public class ProductAndSupplierController {

    @Autowired
    private ProductAndSupplierService productAndSupplierService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index() {
        return "CONNECTED";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSaveProduct(@RequestBody Product product) {
        Product saveProduct = productAndSupplierService.getProductCreated(product);

        try {
            return ResponseEntity.created(URI.create("")).body(saveProduct);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping(value = "/product/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") Long id) {
        Optional<Product> product = productAndSupplierService.getProductById(id);

        try {
            return ResponseEntity.ok(product.get());

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all_products", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllProducts() {
        List<Product> product = productAndSupplierService.getAllProductsData();

        try {
            return ResponseEntity.ok(product);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/modified/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@PathVariable Long id, @PathVariable Product product) {
        try {
            return productAndSupplierService.getProductUpdatedById(id, product);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return null;
        }
    }

    @DeleteMapping(value = "/low/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteProduct(@PathVariable Long id) {
        try {
            return productAndSupplierService.getProductDeletedById(id);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}

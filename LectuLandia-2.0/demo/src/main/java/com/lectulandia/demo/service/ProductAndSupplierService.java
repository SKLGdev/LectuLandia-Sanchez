package com.lectulandia.demo.service;

import com.lectulandia.demo.entity.Product;
import com.lectulandia.demo.entity.SupplierData;
import com.lectulandia.demo.repository.ProductRepository;
import com.lectulandia.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductAndSupplierService {

    @Autowired
    private ProductRepository productsRepository;
    @Autowired
    private SupplierRepository suppliersRepository;

    public Product getProductCreated(Product product) {
        return productsRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    public List<Product> getAllProductsData() {
        return productsRepository.findAll();
    }

    public Product getProductUpdatedById(Long id, Product product) {
        Product updateProduct = productsRepository.findById(id).get();

        updateProduct.setProductName(product.getProductName());
        updateProduct.setAuthorName(product.getAuthorName());
        updateProduct.setProductDescription(product.getProductDescription());
        updateProduct.setStock(product.getStock());
        updateProduct.setProductPrice(product.getProductPrice());

        return productsRepository.save(updateProduct);
    }

    public String getProductDeletedById(Long id) {
        productsRepository.deleteById(id);
        return "SUCCESSFUL PRODUCT DELETED";
    }

    public SupplierData getSupplierCreated(SupplierData supplier) {
        return suppliersRepository.save(supplier);
    }

    public Optional<SupplierData> getSupplierById(Long id) {
        return suppliersRepository.findById(id);
    }

    public List<SupplierData> getAllSuppliersData() {
        return suppliersRepository.findAll();
    }

    public SupplierData getSupplierUpdatedById(Long id, SupplierData supplier) {
        SupplierData updateSupplier = suppliersRepository.findById(id).get();

        updateSupplier.setSupplierName(supplier.getSupplierName());
        updateSupplier.setTotal(supplier.getTotal());

        return suppliersRepository.save(updateSupplier);
    }

    public String getSupplierDeletedById(Long id) {
        suppliersRepository.deleteById(id);
        return "SUCCESSFUL SUPPLIER DELETED";
    }

}

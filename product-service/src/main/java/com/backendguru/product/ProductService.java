package com.backendguru.product;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service // Bunu bir Spring servis bileşeni olarak işaretler
@RequiredArgsConstructor // Lombok: final alanlar için bir constructor oluşturur (DI)
public class ProductService {

    // Spring, ProductRepository bean'ini constructor aracılığıyla enjekte eder
    private final ProductRepository productRepository;

    // Yeni bir ürün oluşturma metodu
    // @Transactional // Bunun bir DB işlemi içinde çalışmasını sağlar (şimdilik opsiyonel)
    public ProductResponse createProduct(NewProductRequest newProductRequest) {
        // Kaydetmeden önce burada doğrulama veya iş mantığı eklenebilir
        var productEntity = new ProductEntity();
        productEntity.setDescription(newProductRequest.description());
        productEntity.setName(newProductRequest.name());
        productEntity.setPrice(newProductRequest.price());
        return toResponse(productRepository.save(productEntity)); // JpaRepository'nin save metodunu kullanır
    }

    // Tüm ürünleri getirme metodu
    public List<ProductResponse> getAllProducts() {
        var productEntities = productRepository.findAll();
        return productEntities.stream().map(this::toResponse).toList(); // JpaRepository'nin findAll metodunu kullanır
    }

    //mapper function
    private ProductResponse toResponse(ProductEntity e) {
        return new ProductResponse(
            e.getId(),
            e.getDescription(),
            e.getName(),
            e.getPrice()
        );
    }
   
    // ID'ye göre tek bir ürün getirme metodu
    public Optional<ProductResponse> getProductById(Long id) {
        // Optional, ürünün mevcut olmayabileceği durumları ele alır
        return productRepository.findById(id).map(this::toResponse); // JpaRepository'nin findById metodunu kullanır
    }

    // Bir ürünü silme metodu 
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id); // JpaRepository'nin deleteById metodunu kullanır
            return true; // Başarıyı belirtir
        }
        return false; // Ürünün bulunamadığını belirtir
    }

    
    // public Optional<Product> updateProduct(Long id, Product productDetails) { ... }

}

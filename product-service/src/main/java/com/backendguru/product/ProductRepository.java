package com.backendguru.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long>{
     // <Product, Long> -> Varlık tipi Product, ID tipi Long

     // Spring Data JPA, aşağıdaki gibi yaygın metodlar için implementasyonları sağlar:
     // save(), findById(), findAll(), deleteById(), count(), existsById()...
     // OTOMATİK OLARAK! Temel CRUD için burada koda gerek yoktur.

     // Daha sonra isimlendirme kurallarını takip ederek özel sorgu metodları ekleyebilirsiniz:
     // Örnek: List<Product> findByNameContainingIgnoreCase(String name); 
}

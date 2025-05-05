*** Testing ***
* curl http://localhost:8080/v1/products (GET Tümünü Al)
* curl http://localhost:8080/v1/products/1 (GET ID 1)
* curl -X POST -H "Content-Type: application/json" -d '{"name":"Test Kitabı","description":"Harika bir kitap","price":29.99}' http://localhost:8080/v1/products (POST yeni ürün)
* curl -X DELETE http://localhost:8080/v1/products/1 (DELETE ürün ID 1)
* curl -i http://localhost:8080/v1/products/1 (-i durum kodu gibi yanıt başlıklarını gösterir)

*** Testing ***
* curl http://localhost:8090/v1/orders (GET Tümünü Al)
* curl http://localhost:8090/v1/orders/1 (GET ID 1)
* curl -X POST -H "Content-Type: application/json" -d '{"productId":101,"userId":11,"quantity":3}' http://localhost:8090/v1/orders (POST yeni ürün)
* curl -X DELETE http://localhost:8090/v1/orders/1 (DELETE ürün ID 1)
* curl -i http://localhost:8090/v1/orders/1 (-i durum kodu gibi yanıt başlıklarını gösterir)
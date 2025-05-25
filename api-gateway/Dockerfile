# 1. Temel İmajı Belirtin
# Resmi bir Java Runtime Environment (JRE) imajı kullanın.
# Projenizin kullandığı Java sürümünü eşleştirin (burada Java 21).
# '-alpine' veya '-slim' gibi varyantlar daha küçük imaj boyutları sunar.
FROM eclipse-temurin:21-jre-alpine

# 2. Çalışma Dizinini Ayarlayın
# Konteyner içinde komutların çalışacağı dizini tanımlar.
WORKDIR /app

# 3. Uygulama Yapıtını (Artifact) Kopyalayın
# Derlenmiş JAR dosyasını yapılandırma bağlamınızdan (örn. target/ veya build/libs/)
# konteynerin /app dizinine kopyalar ve adını app.jar olarak değiştirir.
# Kaynak yolunu derleme aracınıza (Maven/Gradle) göre ayarlayın.
COPY target/*.jar app.jar

# --- add curl for health-checks ---
RUN apk add --no-cache curl

# 4. Uygulama Portunu Dışa Açın (Expose)
# Konteyner içindeki uygulamanın 8080 portunu dinlediğini belgeler.
EXPOSE 8080

# 5. Başlangıç Komutunu Tanımlayın
# Konteyner başladığında yürütülecek komutu belirtir.
# Bu, Spring Boot uygulamasını 'java -jar' kullanarak çalıştırır.
CMD ["java", "-jar", "app.jar"]
# Domain-Driven Design: Stratejik ve Taktiksel Uygulama için Kapsamlı Bir Genel Bakış

Domain-Driven Design (DDD), karmaşık iş alanları ile teknik uygulama arasındaki boşluğu kapatmayı amaçlayan bir yazılım geliştirme felsefesidir. Eric Evans tarafından 2003 yılında tanıtılan DDD, paydaşlar arasında birlik sağlamak ve yazılım modelinin işin temel mantığını yansıtmasını sağlamak için **ubiquitous language** (herkesin ortak kullandığı dil) oluşturmayı vurgular.

## Stratejik Domain-Driven Design

Stratejik DDD, yazılım mimarisini iş hedefleriyle uyumlu hale getirmeye odaklanır; karmaşık domain’i yönetilebilir parçalara bölerek bilişsel yükü azaltır ve modülerliği artırır.

### Domain ve Subdomain

Bir **domain** (alan), tüm iş problem alanını temsil eder, örneğin "e-ticaret" veya "sağlık yönetimi". Bu alan içinde, **subdomain** (alt alan) domain’i daha küçük, bütünlüklü sorumluluk alanlarına böler. Subdomain’ler üç kategoriye ayrılır:
- **Core subdomain**: İşin rekabetçi avantajı (ör. perakendede gerçek zamanlı stok takibi).
- **Supportive subdomain**: Temel ama farklılaştırıcı olmayan işlevler (ör. sağlıkta faturalama).
- **Generic subdomain**: Hazır çözümlerle karşılanan yaygın problemler (ör. kimlik doğrulama).

Örneğin, bir e-ticaret platformunun core subdomain’i öneri algoritmaları olabilirken, ödeme işlemleri generic subdomain olarak üçüncü parti API’lerle çözülür.

### Bounded Context ve Boundaries

**Bounded context** (sınırlı bağlam), bir domain modelinin kapsamını tanımlar ve bu sınırlar içinde tutarlılık sağlar. Her bounded context’in kendi ubiquitous language’ı, entity’leri ve kuralları vardır; bu, alt sistemler arasında kavramsal çakışmaları önler. Örneğin, bir "müşteri" satış bağlamında satın alma geçmişini içerirken, destek bağlamında hizmet taleplerini ifade edebilir.

**Context boundaries** (bağlam sınırları) şu yollarla sağlanır:
- **Integration patterns** (entegrasyon desenleri): Shared kernel, customer-supplier ilişkileri veya anticorruption layer ile context’ler arası iletişimi yönetmek.
- **Team autonomy** (takım özerkliği): Bounded context’lere sahip bağımsız takımlar, koordinasyon maliyetini azaltır.

### Context Mapping

**Context mapping** (bağlam haritalama), bounded context’ler arasındaki ilişkileri görselleştirir. Yaygın desenler:
- **Partnership**: İki context’in birlikte geliştirilmesi (ör. sipariş yönetimi ve stok).
- **Open Host Service (OHS)**: Dış tüketiciler için standart bir API (ör. ürün kataloğu için REST endpoint).
- **Published Language (PL)**: Paylaşılan veri formatı (ör. sipariş olayları için JSON şeması).

Bir sağlık sisteminde, hasta yönetimi context’i faturalama ile shared kernel paylaşabilir ve randevu planlaması için OHS kullanabilir.

## Taktiksel Domain-Driven Design

Taktiksel DDD, stratejik sınırları uygulanabilir bileşenlere dönüştürür ve bounded context içinde ayrıntılı domain modellemesine odaklanır.

### Entity ve Value Object

**Entity** (varlık), özniteliklerinden ziyade benzersiz kimliğiyle tanımlanır. Örneğin, bir `User` entity’si, e-posta veya adı değişse de kimliğini korur. Entity’ler yaşam döngüsü durumlarını (ör. `Pending`, `Active`) yönetir ve durum geçişlerinde iş kurallarını uygular.

**Value object (VO)**, kimliği olmayan, öznitelik temelli, değişmez kavramları kapsar. Bir `Address` VO’su (sokak, şehir, posta kodu) herhangi bir öznitelik değişirse tamamen yenisiyle değiştirilir. VO’lar eşitlik kontrollerini basitleştirir ve eşzamanlılık sorunlarını azaltır.

### Aggregate ve Aggregate Root

**Aggregate** (küme), entity ve VO’ların tutarlılık için tek bir birim olarak gruplanmasıdır. **Aggregate root** (küme kökü), dış etkileşimler için tek giriş noktasıdır ve kuralların korunmasını sağlar. Örneğin, bir `Order` aggregate root’u, `OrderItem` entity’leri ve `ShippingAddress` VO’larını yönetir, toplam fiyatın ürün miktarlarıyla uyumlu olmasını denetler.

Aggregate’ler, tutarlılık sınırlarını belirler; aggregate içindeki tüm değişiklikler ya tamamen başarılı olur ya da hiçbiri uygulanmaz. Bu, örneğin bir siparişin "ödendi" olarak işaretlenip stoktan düşülmemesi gibi kısmi güncellemeleri engeller.

### Repository ve Service

**Repository** (depo), kalıcılık mekanizmalarını soyutlar; aggregate’leri almak ve kaydetmek için `save()`, `findById()` gibi metotlar sunar. Domain mantığını altyapıdan ayırır, testlerde bellek içi uygulamalarla kullanılabilir.

**Domain service** (alan servisi), tek bir aggregate’e uymayan mantığı kapsar; örneğin, birden fazla `Order` aggregate’inde kargo ücretini hesaplamak. Application service’ler ise domain nesnelerini, repository’leri ve dış sistemleri koordine eder.

## Uygulamada Dikkat Edilecekler

### Katmanlı Mimari

Taktiksel DDD, sorumlulukları ayırmak için katmanlı mimari kullanır:
- **Presentation layer**: Kullanıcı girdisi ve çıktı (ör. REST controller).
- **Application layer**: İş akışlarını koordine eder (ör. sipariş verme).
- **Domain layer**: Entity, aggregate ve domain service’leri içerir.
- **Infrastructure layer**: Kalıcılık, mesajlaşma ve dış entegrasyonları uygular.

Bu yapı, domain mantığının teknik detaylardan izole kalmasını sağlar, test ve evrim kolaylaşır.

### Microservice ve Bounded Context

Bounded context’ler, microservice’lerle doğal olarak uyumludur; her servis bir context’in model ve verisine sahip olur. Örneğin, bir perakende sistemi `Product Catalog`, `Inventory` ve `Order` microservice’lerine ayrılabilir; her biri bağımsız takımlar ve veritabanlarıyla yönetilir. Anticorruption layer (ACL), üst akış değişikliklerinin bağımlı servisleri bozmasını engeller; örneğin eski ERP verisinin modern bir `Order` şemasına çevrilmesi.

### Event Storming ve İteratif İyileştirme

**Event storming** atölyeleri, domain event’lerini (ör. `OrderPlaced`, `PaymentProcessed`) ve aggregate sınırlarını belirlemek için domain uzmanlarıyla birlikte yapılır. İteratif modelleme, gerçek senaryolara göre aggregate ve VO’ları geliştirir, ubiquitous language’ın doğruluğunu sağlar.

## Zorluklar ve En İyi Uygulamalar

### Karmaşıklık Yönetimi

- **Context boundaries**: Çakışan context’ler belirsiz modellere yol açar. Düzenli context mapping oturumları ilişkileri netleştirir.
- **Legacy integration**: ACL ve stratejik strangler pattern ile eski sistemler, core domain’ler bozulmadan kademeli olarak yenilenir.

### Tutarlılık ve Performans

- **Event sourcing**: Durum değişikliklerini değişmez event’ler olarak saklar, denetim izi ve zamansal sorgular sağlar.
- **CQRS (Command Query Responsibility Segregation)**: Okuma ve yazma modellerini ayırır, yüksek hacimli sistemlerde performansı optimize eder.

### Takım İşbirliği

- **Ubiquitous language sözlükleri**: Ortak wiki’lerde tutulur, takımlar arası terim tutarlılığını sağlar.
- **Çapraz fonksiyonlu takımlar**: Domain uzmanları sprint planlamasına dahil edilir, model değişiklikleri doğrulanır.

## Sonuç

Domain-Driven Design, teknik uygulamayı iş gerçekleriyle uyumlu hale getirerek yazılım sistemlerinde karmaşıklıkla baş etmek için sağlam bir çerçeve sunar. Stratejik DDD’nin bounded context ve context mapping’i modüler mimariler oluştururken, taktiksel desenler olan aggregate ve value object, domain mantığının tutarlı ve sürdürülebilir kalmasını sağlar. İteratif iyileştirme, katmanlı mimari ve microservice’lerle birleştiğinde, ekipler iş ihtiyaçlarıyla birlikte evrilen, yenilikçi ve hızlı teslim edilen sistemler geliştirebilir.

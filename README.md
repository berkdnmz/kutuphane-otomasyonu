# Kütüphane Otomasyonu

Basit bir Java tabanlı masaüstü kütüphane otomasyonu uygulamasıdır. Kullanıcılar kitap ekleyebilir, silebilir, kiralayabilir ve mevcut kitapları listeleyebilir. Proje, Swing ile oluşturulmuş kullanıcı dostu grafiksel arayüze ve JDBC ile MySQL veri tabanı bağlantısına sahiptir.

**Not:** Bu proje, geçen sene geliştirilmiş olup, temel seviyede kütüphane otomasyonu ihtiyacını karşılamak için hazırlanmıştır.

---

## İçindekiler

- [Genel Bakış](#genel-bakış)  
- [Özellikler](#özellikler)  
- [Kurulum](#kurulum)  
- [Kullanım](#kullanım)  
- [Dosya Yapısı](#dosya-yapısı)  
- [Proje Sahibi](#proje-sahibi)

---

## Genel Bakış

Bu Java projesi, kullanıcıların kitap yönetimi yapmasına olanak sağlayan basit bir masaüstü uygulamasıdır. Kitap ekleme, silme, kiralama ve listeleme işlemleri desteklenmektedir. Veri tabanı bağlantısı için `config.properties` dosyası kullanılır. Ayrıca kullanıcı yetkilendirmesi mevcuttur.

---

## Özellikler

- Kitap ekleme, silme ve kiralama işlemleri  
- Kitapların listelenmesi ve detaylı görüntülenmesi  
- Kullanıcı giriş sistemi ve yetki kontrolü (örneğin sadece admin kitap ekleyebilir/silebilir)  
- MySQL veri tabanı bağlantısı için dinamik yapılandırma  
- Kullanıcı dostu Swing tabanlı grafik arayüz  
- Tarih formatı kontrolü ve hata yönetimi  

---

## Kurulum

1. Java 8 veya üstü yüklü olduğundan emin olun.  
2. MySQL veri tabanını kurun ve aşağıdaki tablo yapısını oluşturun:

    
    CREATE TABLE kitaplar (
        id INT AUTO_INCREMENT PRIMARY KEY,
        kitap_adi VARCHAR(255),
        yazar VARCHAR(255),
        yayin_yili VARCHAR(10)
    );
    
    CREATE TABLE kullanicilar (
        id INT AUTO_INCREMENT PRIMARY KEY,
        ad_soyad VARCHAR(255),
        kullanici_adi VARCHAR(255),
        sifre VARCHAR(255),
        yetki INT
    );
    
    CREATE TABLE kitapkiralama (
        id INT AUTO_INCREMENT PRIMARY KEY,
        kullanici_id INT,
        kitap_id INT,
        alma_tarihi DATE,
        teslim_tarihi DATE
    );

3. `config.properties` dosyanızda veri tabanı bilgilerinizi girin:

    
    DB_URL=jdbc:mysql://localhost:3306/kutuphaneotomasyonu
    DB_USER=root
    DB_PASSWORD=yourpassword

4. Projeyi tercih ettiğiniz IDE'ye (IntelliJ, Eclipse vb.) import edin.  
5. Gerekli kütüphaneleri (JDBC sürücüsü vb.) projeye ekleyin.

---

## Kullanım

1. Projeyi başlatmak için `KullaniciEkrani` sınıfını çalıştırın.  
2. Kullanıcı adı ve şifre ile giriş yapın.  
3. Yetkinize bağlı olarak kitap ekleyebilir, silebilir, kiralayabilir veya kitapları listeleyebilirsiniz.  
4. Menü üzerinden yapmak istediğiniz işlemi seçin.

---

## Dosya Yapısı

    
    kutuphaneOtomasyonu/
    ├── AnaMenuEkrani.java        # Ana menü arayüzü  
    ├── KitapEkleEkrani.java      # Kitap ekleme ekranı  
    ├── KitapKiralaEkrani.java    # Kitap kiralama ekranı  
    ├── KitaplarEkrani.java       # Kitap listeleme ekranı  
    ├── KitapSilEkrani.java       # Kitap silme ekranı  
    ├── KullaniciEkrani.java      # Kullanıcı giriş ekranı  
    ├── VeriTabaniBaglantisi.java # Veri tabanı bağlantı ve sorgu sınıfı  
    ├── config.properties         # Veri tabanı bağlantı ayarları  
    ├── .gitignore                # Git ignore dosyası  
    └── README.md                 # Proje açıklaması (bu dosya)

---

## Proje Sahibi

**Berk DÖNMEZ**  
GitHub: [github.com/berkdnmz](https://github.com/berkdnmz)  
LinkedIn: [linkedin.com/in/berkdnmz](https://linkedin.com/in/berkdnmz)  

Projeyle ilgili sorularınız için iletişime geçebilirsiniz.

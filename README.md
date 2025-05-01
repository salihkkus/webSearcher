# Proje Görev Dağılımı

## 👤 Kişi 1: Arayüz Geliştirici (UI/UX + Material Design)
- Android'de ekranları oluşturur (Main, Detay, Yeni Link Ekle vs.)
- Material Design bileşenleri (FloatingActionButton, TextInputLayout, vb.) kullanır
- Açık/Koyu tema desteğini entegre eder
- Türkçe + 1 ek dil desteği sağlar
- Launcher ikonunu özelleştirir
- Sabit tanımlamaları `strings.xml`, `colors.xml`, `dimens.xml` gibi kaynak dosyalarda tutar

## 👤 Kişi 2: Veri Alma ve İşleme (Web İçeriği + Modeller)
- Linkten web içeriği alma (title, image, okuma süresi)
- Bu veriler için veri modelleri (`data class`) oluşturur
- Modelleri tablo halinde raporda açıklar
- Geriye uyumlu kütüphaneler (AppCompat) ile çalışmayı garanti eder
- Gerekirse parsing için harici bağımlılıklar (örneğin Jsoup) entegre eder

## 👤 Kişi 3: Firebase ve Veri Yönetimi
- Firebase Firestore veya Realtime Database bağlantısını yapar
- Kullanıcının eklediği linkleri veritabanında tutar
- Giriş/Çıkış sistemi gerekiyorsa ekler
- Okuma süresi gibi istatistikleri hesaplayıp kullanıcıya özel gösterir

## 👤 Kişi 4: Proje Raporu ve Entegrasyon/Test
- Raporu Ödev Şablonuna uygun yazar (örnek kapak, açıklamalar, tablolar)
- Tüm geliştirilen `activity`, `intent`, `servis` vs. bileşenleri açıklar
- Harici bağımlılıkları ve izinleri nedenleriyle listeler
- `Gradle` ve `Manifest` dosyasını rapora ekler
- Projeyi test eder, hataları raporlar
- Proje sunum tarihinden 2 gün önce PDF haline getirip yükler

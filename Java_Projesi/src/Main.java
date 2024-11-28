import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SinemaSistemi sistem = new SinemaSistemi();
        Scanner scanner = new Scanner(System.in);

        // Güncel Filmler
        sistem.yeniFilmEkle(new Film("Avatar", 162, "Bilim Kurgu"));
        sistem.yeniFilmEkle(new Film("Parasite", 132, "Dram"));
        sistem.yeniFilmEkle(new Film("Joker", 122, "Psikolojik Gerilim"));

        // Varsayılan Salonlar
        sistem.yeniSalonEkle(new Salon(1, "1. Salon (VIP)", sistem.getFilmler().get(0)));
        sistem.yeniSalonEkle(new Salon(2, "2. Salon (Standart)", sistem.getFilmler().get(1)));
        sistem.yeniSalonEkle(new Salon(3, "3. Salon (Aile)", sistem.getFilmler().get(2)));

        while (true) {
            System.out.println("\n*********************************************");
            System.out.println("*              Piri Reis Sinema             *");
            System.out.println("*********************************************");
            System.out.println("*  1. Filmleri ve Salonları Görüntüle       *");
            System.out.println("*  2. Salonlara Kayıtlı Müşterileri Listele *");
            System.out.println("*  3. Yeni Salon ve Film Ekle               *");
            System.out.println("*  4. Programdan Çık                        *");
            System.out.println("*********************************************");
            System.out.print("\nLütfen bir işlem seçin (1-4): ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    sistem.filmVeSalonlariListele();
                    System.out.print("\nHangi filme gitmek istersiniz? Film ID'sini girin: ");
                    int filmSecim = scanner.nextInt() - 1;

                    if (filmSecim < 0 || filmSecim >= sistem.getFilmler().size()) {
                        System.out.println("Geçersiz seçim. Lütfen doğru bir film ID'si girin.");
                        break;
                    }

                    Salon secilenSalon = sistem.getSalonlar().get(filmSecim);
                    System.out.println("\nSeçilen Salon: " + secilenSalon.getName());
                    System.out.println("Salon Durumu:");
                    secilenSalon.koltukDuzeniniYazdir();

                    System.out.print("Satır numarası seçin (1-5): ");
                    int satir = scanner.nextInt() - 1;
                    System.out.print("Sütun numarası seçin (1-5): ");
                    int sutun = scanner.nextInt() - 1;

                    if (satir >= 0 && satir < 5 && sutun >= 0 && sutun < 5 && secilenSalon.koltukBosMu(satir, sutun)) {
                        secilenSalon.koltukRezerveEt(satir, sutun);
                        System.out.print("Adınızı girin: ");
                        scanner.nextLine();
                        String musteriAdi = scanner.nextLine();
                        Musteri musteri = new Musteri(SinemaSistemi.yeniMusteriIdAl(), musteriAdi);
                        sistem.yeniMusteriEkle(musteri, secilenSalon.getId() - 1);
                        System.out.println("Koltuk başarıyla rezerve edildi! İyi seyirler, " + musteriAdi + "!");
                    } else {
                        System.out.println("Geçersiz seçim veya dolu koltuk. Lütfen başka bir koltuk seçin.");
                    }
                    break;

                case 2:
                    System.out.println("\nSalon Müşteri Listesi:");
                    sistem.salonMusterileriniListele();
                    System.out.println("Devam etmek için Enter tuşuna basın...");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("\nYeni Salon ve Film Ekle:");
                    System.out.print("Eklemek istediğiniz film adı: ");
                    String filmAdi = scanner.nextLine();
                    System.out.print("Film süresi (dakika): ");
                    int filmSure = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Film türü (örneğin, Dram, Aksiyon): ");
                    String filmTuru = scanner.nextLine();
                    Film yeniFilm = new Film(filmAdi, filmSure, filmTuru);
                    sistem.yeniFilmEkle(yeniFilm);

                    System.out.print("Yeni salon adı: ");
                    String salonAdi = scanner.nextLine();
                    Salon yeniSalon = new Salon(sistem.getSalonlar().size() + 1, salonAdi, yeniFilm);
                    sistem.yeniSalonEkle(yeniSalon);
                    System.out.println("Yeni salon ve film başarıyla eklendi: " + salonAdi + " - " + filmAdi);
                    break;

                case 4:
                    System.out.println("Programdan çıkılıyor. İyi günler dileriz!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Geçersiz seçim. Lütfen 1-4 arasında bir seçim yapın.");
            }
        }
    }
}

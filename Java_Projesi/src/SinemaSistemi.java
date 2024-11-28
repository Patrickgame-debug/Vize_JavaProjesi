import java.util.ArrayList;
import java.util.List;

public class SinemaSistemi {
    private List<Musteri> musteriler;
    private List<Film> filmler;
    private List<Salon> salonlar;

    private static int musteriIdCounter = 1;

    public SinemaSistemi() {
        this.musteriler = new ArrayList<>();
        this.filmler = new ArrayList<>();
        this.salonlar = new ArrayList<>();
    }

    public void yeniMusteriEkle(Musteri musteri, int salonIndex) {
        musteriler.add(musteri);
        salonlar.get(salonIndex).musteriEkle(musteri);
    }

    public void yeniFilmEkle(Film film) {
        filmler.add(film);
    }

    public void yeniSalonEkle(Salon salon) {
        salonlar.add(salon);
    }

    public void filmVeSalonlariListele() {
        System.out.println("=== Filmler ve Salonlar ===");
        int index = 1;
        for (Film film : filmler) {
            System.out.println(index++ + ". Film Adı: " + film.getAd());
            System.out.println("   Süresi: " + film.getSure() + " dakika");
            System.out.println("   Türü: " + film.getTur());
        }
    }

    public void salonMusterileriniListele() {
        for (Salon salon : salonlar) {
            System.out.println("Salon: " + salon.getName() + " (Film: " + salon.getFilm().getAd() + ")");
            if (!salon.getMusteriler().isEmpty()) {
                for (Musteri musteri : salon.getMusteriler()) {
                    musteri.bilgiGoster();
                }
            } else {
                System.out.println("   Henüz kayıtlı müşteri yok.");
            }
            System.out.println();
        }
    }

    public List<Film> getFilmler() {
        return filmler;
    }

    public List<Salon> getSalonlar() {
        return salonlar;
    }

    public static int yeniMusteriIdAl() {
        return musteriIdCounter++;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Salon extends BaseEntity implements IReservable {
    private Film film;
    private boolean[][] koltuklar;
    private List<Musteri> musteriler;

    public Salon(int id, String name, Film film) {
        super(id, name);
        this.film = film;
        this.koltuklar = new boolean[5][5];
        this.musteriler = new ArrayList<>();
    }

    public Film getFilm() {
        return film;
    }

    public boolean[][] getKoltuklar() {
        return koltuklar;
    }

    public List<Musteri> getMusteriler() {
        return musteriler;
    }

    public void musteriEkle(Musteri musteri) {
        musteriler.add(musteri);
    }

    @Override
    public boolean koltukBosMu(int satir, int sutun) {
        return !koltuklar[satir][sutun];
    }

    @Override
    public void koltukRezerveEt(int satir, int sutun) {
        koltuklar[satir][sutun] = true;
    }

    @Override
    public void koltukDuzeniniYazdir() {
        System.out.println("Koltuk Düzeni (D = Dolu, B = Boş):");
        for (int i = 0; i < koltuklar.length; i++) {
            for (int j = 0; j < koltuklar[i].length; j++) {
                System.out.print(koltuklar[i][j] ? " D " : " B ");
            }
            System.out.println();
        }
    }

    @Override
    public void bilgiGoster() {
        System.out.println("Salon: " + getName() + ", Film: " + film.getAd());
        koltukDuzeniniYazdir();
    }
}

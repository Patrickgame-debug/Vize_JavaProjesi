public interface IReservable {
    boolean koltukBosMu(int satir, int sutun); // Koltuk boş mu kontrolü
    void koltukRezerveEt(int satir, int sutun); // Koltuğu rezerve et
    void koltukDuzeniniYazdir(); // Koltuk düzenini yazdır
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PENGUJIAN SISTEM ===");

        System.out.println("\n--- 1. Objek Pod-A ---");
        Pod podA = new Pod("Titan");
        podA.cetakManifest();

        System.out.println("\n--- 2. Pemuatan Reguler ---");
        podA.tambahMuatan("Sembako", 150.0);
        podA.tambahMuatan("Peralatan", 200.0);
        podA.cetakManifest();

        System.out.println("\n--- 3. Validasi Fallback ID ---");
        Pod podB = new Pod("BOX-999", "Mars", 1000.0, 3);
        podB.cetakManifest();

        System.out.println("\n--- 4. Uji Transaksi Batch Atomik ---");
        String[] batchBarang = {"Medis", "Pakaian", "Bahan Bakar", "Suku Cadang"};
        double totalBeratBatch = 380.0;
        boolean batchStatus = podB.tambahMuatan(batchBarang, totalBeratBatch);
        System.out.println("Status Batch: " + (batchStatus ? "Sukses" : "Gagal"));
        podB.cetakManifest();

        System.out.println("\n--- 5. Uji Kebocoran Enkapsulasi ---");
        String[] daftar = podA.getDaftarBarang();
        if (daftar != null && daftar.length > 0) {
            daftar[0] = "BARANG_PALSU";
            System.out.println("Array eksternal diubah: daftar[0] = \"BARANG_PALSU\"");
        }
        System.out.println("Manifest Pod-A (harus tetap utuh):");
        podA.cetakManifest();

        System.out.println("\n--- 6. Uji Integritas Segel ---");
        podA.segelModul();
        boolean tambah = podA.tambahMuatan("Bahan Kimia", 50.0);
        System.out.println("Hasil tambah muatan setelah disegel: " + (tambah ? "Berhasil" : "Ditolak"));
        podA.cetakManifest();
    }
}

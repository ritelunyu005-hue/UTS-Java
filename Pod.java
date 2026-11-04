public class Pod {
    private final String podId;
    private final String tujuanPlanet;
    private final double kapasitasMaks;
    private final String[] daftarBarang;
    private double bebanSaatIni;
    private int jumlahItem;
    private boolean statusTersegel;

    public Pod(String podId, String tujuanPlanet, double kapasitasMaks, int slotArray) {
        if (podId == null || !podId.startsWith("POD-")) {
            this.podId = "POD-UNKNOWN";
        } else {
            this.podId = podId;
        }
        this.tujuanPlanet = tujuanPlanet;
        this.kapasitasMaks = kapasitasMaks;
        this.daftarBarang = new String[slotArray];
        this.bebanSaatIni = 0.0;
        this.jumlahItem = 0;
        this.statusTersegel = false;
    }

    public Pod(String podId, String tujuanPlanet) {
        this(podId, tujuanPlanet, 1000.0, 5);
    }

    public Pod(String tujuanPlanet) {
        this("POD-" + System.currentTimeMillis(), tujuanPlanet);
    }

    public boolean tambahMuatan(String nama, double berat) {
        if (statusTersegel) return false;
        if (bebanSaatIni + berat > kapasitasMaks) return false;
        if (jumlahItem >= daftarBarang.length) return false;

        daftarBarang[jumlahItem] = nama;
        jumlahItem++;
        bebanSaatIni += berat;
        return true;
    }

    public boolean tambahMuatan(String[] batch, double totalBerat) {
        if (statusTersegel) return false;

        int sisaSlot = daftarBarang.length - jumlahItem;
        if (batch.length > sisaSlot || (bebanSaatIni + totalBerat) > kapasitasMaks) {
            return false;
        }

        for (String item : batch) {
            daftarBarang[jumlahItem] = item;
            jumlahItem++;
        }
        bebanSaatIni += totalBerat;
        return true;
    }

    public boolean segelModul() {
        if (jumlahItem >= 1) {
            this.statusTersegel = true;
            return true;
        }
        return false;
    }

    public String[] getDaftarBarang() {
        String[] copy = new String[jumlahItem];
        System.arraycopy(daftarBarang, 0, copy, 0, jumlahItem);
        return copy;
    }

    public void cetakManifest() {
        double persentase = (bebanSaatIni / kapasitasMaks) * 100;
        System.out.println("----------------------------------------");
        System.out.println("ID Pod       : " + podId);
        System.out.println("Destinasi    : " + tujuanPlanet);
        System.out.println("Beban        : " + bebanSaatIni + " / " + kapasitasMaks + " kg (" + String.format("%.1f", persentase) + "%)");
        System.out.println("Status Segel : " + (statusTersegel ? "Tersegel" : "Terbuka"));
        System.out.print("Inventaris   : ");
        if (jumlahItem == 0) {
            System.out.println("[Kosong]");
        } else {
            System.out.print("[");
            for (int i = 0; i < jumlahItem; i++) {
                System.out.print(daftarBarang[i] + (i < jumlahItem - 1 ? ", " : ""));
            }
            System.out.println("]");
        }
        System.out.println("----------------------------------------");
    }
}

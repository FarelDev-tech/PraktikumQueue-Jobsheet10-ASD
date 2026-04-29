public class QueueKRS08 {
    Mahasiswa08 data[];
    int front;
    int rear;
    int size;
    int max;
    int kuotaDPA = 30; // DPA menangani 30 Mahasiswa
    int jumlahSudahKRS = 0;

    public QueueKRS08(int max) {
        this.max = max;
        this.data = new Mahasiswa08[max];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == max;
    }

    public void clear() {
        if (!isEmpty()) {
            front = 0;
            rear = -1;
            size = 0;
            System.out.println("Antrian berhasil dikosongkan.");
        } else {
            System.out.println("Antrian masih kosong.");
        }
    }

    public void enqueue(Mahasiswa08 mhs) {
        if (isFull()) {
            System.out.println("Mohon maaf, antrian sudah mencapai maksimal (10 Mahasiswa).");
        } else if (jumlahSudahKRS + size >= kuotaDPA) { 
            System.out.println("Kuota DPA sudah habis, tidak bisa menerima antrian lagi.");
        } else {
            rear = (rear + 1) % max;
            data[rear] = mhs;
            size++;
            System.out.println(mhs.nama + " berhasil masuk ke antrian.");
        }
    }

    // Method untuk memanggil 2 antrian sekaligus (no 1 dan 2)
    public void prosesKRS() {
        if (isEmpty()) {
            System.out.println("Antrian kosong. Tidak ada yang bisa diproses.");
            return;
        }

        System.out.println("Memanggil Mahasiswa untuk proses KRS:");
        // Perulangan disini maksimal 2 kali untuk melayani 2 orang sekaligus
        for (int i = 0; i < 2; i++) {
            if (!isEmpty() && jumlahSudahKRS < kuotaDPA) {
                Mahasiswa08 mhs = data[front];
                front = (front + 1) % max;
                size--;
                jumlahSudahKRS++;
                System.out.print("Melayani: ");
                mhs.tampilkanData();
            }
        }
    }

    public void tampilkanSemua() {
        if (isEmpty()) {
            System.out.println("Antrian Kosong.");
        } else {
            System.out.println("Daftar Antrian KRS:");
            for (int i = 0; i < size; i++) {
                int index = (front + i) % max;
                System.out.print((i + 1) + ". ");
                data[index].tampilkanData();
            }
        }
    }

    public void lihatDuaTerdepan() {
        if (isEmpty()) {
            System.out.println("Antrian Kosong.");
        } else {
            System.out.println("2 Mahasiswa Terdepan:");

            // Perulangan disini maksimal 2 kali untuk memanggil 2 orang sekaligus
            int batas;
            if (size == 1) {
                batas = 1;
            } else {
                batas = 2;
            }
            
            for (int i = 0; i < batas; i++) {
                int index = (front + i) % max;
                System.out.print((i + 1) + ". ");
                data[index].tampilkanData();
            }
        }
    }

    public void lihatAkhir() {
        if (isEmpty()) {
            System.out.println("Antrian Kosong.");
        } else {
            System.out.println("Mahasiswa Paling Akhir: ");
            data[rear].tampilkanData();
        }
    }

    public void cetakInfo() {
        System.out.println("=== Statistik Antrian KRS ===");
        System.out.println("Jumlah Antrian Saat Ini: " + size + " Mahasiswa");
        System.out.println("Jumlah Sudah Proses KRS: " + jumlahSudahKRS + " Mahasiswa");
        // Asumsi disini jika "belum melakukan proses" adalah sisa kuota yang belum ditangani
        System.out.println("Sisa Kuota DPA (Belum KRS): " + (kuotaDPA - jumlahSudahKRS) + " Mahasiswa");
    }
}
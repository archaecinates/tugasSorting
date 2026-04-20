package tugasSorting.sorting;

import java.util.Arrays;

public class quickS { //deklarasi class quickS

    static int jumlahPerbandingan = 0; //untuk nyimpen jumlah perbandingan data
    static boolean ascending = true; //true = ascending, false = descending
    static boolean debug = false; //true kalau mau lihat proses, false biar ga rame

    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1); //memanggil quickSort dari index awal sampai akhir
    }

    private static void quickSort(int[] list, int first, int last){ //method quickSort utama
        if(last > first){ //kalau masih ada lebih dari 1 elemen
            int pivotIndex = partition(list, first, last); //cari posisi pivot

            if(debug) System.out.println("setelah pivot di posisi "+pivotIndex + " "+Arrays.toString(list));
            //cetak kalau debug aktif

            quickSort(list, first, pivotIndex - 1); //lanjut ke kiri
            quickSort(list, pivotIndex + 1, last); //lanjut ke kanan
        }
    }

    private static int partition(int[] list, int first, int last){ //method untuk membagi array
        int pivot = list[first]; //pivot diambil dari elemen pertama
        int low = first + 1; //pointer kiri
        int high = last; //pointer kanan

        //cetak kondisi awal kalau debug aktif
        if(debug){
            System.out.println("\npartisi dari index "+first+" sampai index "+ last);
            System.out.println("pivot: "+pivot);
            System.out.println("awal: "+ Arrays.toString(list));
        }

        while(high > low){ //selama pointer belum ketemu

            while(low <= high){
                jumlahPerbandingan++; //hitung perbandingan

                if(ascending){
                    if(list[low] <= pivot) low++; //geser kanan kalau lebih kecil
                    else break;
                } else {
                    if(list[low] >= pivot) low++; //kebalik kalau descending
                    else break;
                }
            }

            while(low <= high){
                jumlahPerbandingan++; //hitung lagi

                if(ascending){
                    if(list[high] > pivot) high--; //geser kiri kalau lebih besar
                    else break;
                } else {
                    if(list[high] < pivot) high--; //kebalik kalau descending
                    else break;
                }
            }

            if(high > low){ //kalau belum ketemu, tuker
                swap(list, low, high);
                if(debug) System.out.println("swap "+Arrays.toString(list));
            }
        }

        while (high > first){
            jumlahPerbandingan++; //hitung lagi

            if(ascending){
                if(list[high] >= pivot) high--;
                else break;
            } else {
                if(list[high] <= pivot) high--;
                else break;
            }
        }

        //tentuin posisi pivot yang benar
        if ((ascending && pivot > list[high]) ||
                (!ascending && pivot < list[high])) {

            list[first] = list[high]; //tukar pivot
            list[high] = pivot;

            if(debug) System.out.println("swap pivot "+Arrays.toString(list));

            return high; //balikin posisi pivot
        } else {
            return first; //kalau ga berubah
        }
    }

    private static void swap(int[] list, int i, int j){ //method untuk menukar 2 elemen
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    //membuat array random
    public static int[] randomArray(int n){
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = (int)(Math.random() * 100000);
        }
        return arr;
    }

    public static void main(String[] args) {

        int[] data = {25, 60, 45, 77, 7, 86, 42, 38}; //data awal

        ascending = true; //mode ascending
        jumlahPerbandingan = 0; //reset counter

        int[] asc = Arrays.copyOf(data, data.length); //biar data asli ga berubah

        long start = System.nanoTime(); //mulai hitung waktu
        quickSort(asc);
        long end = System.nanoTime(); //selesai

        //cetak hasil ascending
        System.out.println("ASCENDING: " + Arrays.toString(asc));
        System.out.println("Perbandingan: " + jumlahPerbandingan);
        System.out.println("Waktu: " + (end - start) + " ns\n");

        ascending = false; //mode descending
        jumlahPerbandingan = 0; //reset lagi

        int[] desc = Arrays.copyOf(data, data.length); //biar data asli ga berubah

        start = System.nanoTime(); //mulai hitung waktu
        quickSort(desc);
        end = System.nanoTime(); //selesai

        //cetak hasil descending
        System.out.println("DESCENDING: " + Arrays.toString(desc));
        System.out.println("Perbandingan: " + jumlahPerbandingan);
        System.out.println("Waktu: " + (end - start) + " ns\n");


        // ===== TABEL WAKTU =====
        int[] ukuran = {1000, 10000, 100000, 1000000}; //ukuran array yang diuji

        System.out.println("N\t\tWaktu Asc (ns)\tWaktu Desc (ns)");

        for(int n : ukuran){ //loop tiap ukuran

            int[] arr1 = randomArray(n); //array random untuk asc
            int[] arr2 = Arrays.copyOf(arr1, arr1.length); //copy biar sama

            ascending = true; //mode ascending
            long t1 = System.nanoTime(); //mulai
            quickSort(arr1);
            long t2 = System.nanoTime(); //selesai

            ascending = false; //mode descending
            long t3 = System.nanoTime(); //mulai
            quickSort(arr2);
            long t4 = System.nanoTime(); //selesai

            //cetak hasil waktu
            System.out.println(n + "\t\t" + (t2 - t1) + "\t\t" + (t4 - t3));
        }
    }
}
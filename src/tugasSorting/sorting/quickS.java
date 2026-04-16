package tugasSorting.sorting;

import java.util.Arrays;

public class quickS {
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last){
        if(last > first){
            int pivotIndex = partition(list, first, last);

            System.out.println("setelah pivot di posisi "+pivotIndex + " "+Arrays.toString(list));

            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    private static int partition(int[] list, int first, int last){
        int pivot = list[first];
        int low = first + 1;
        int high = last;

        System.out.println("\npartisi dari index "+first+" sampai index "+ last);
        System.out.println("pivot: "+pivot);
        System.out.println("awal: "+ Arrays.toString(list));

        while(high > low){
            while(low <= high && list[low] <= pivot){
                low++;
            }
            while(low <= high && list[high] > pivot){
                high--;
            }

            if(high > low){
                swap(list, low, high);
                System.out.println("swap "+Arrays.toString(list));
            }
        }

        while (high > first && list[high] >= pivot){
            high--;
        }

        if (pivot > list[high]){
            list[first] = list[high];
            list[high] = pivot;

            System.out.println("swap pivot "+Arrays.toString(list));
            return high;
        }else{
            return first;
        }
    }

    private static void swap(int[] list, int i, int j){
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public static void main(String[] args) {
        int[] list= {6, 4, 8, 5, 7, 1, 9, 3};

        System.out.println("array awal: "+Arrays.toString(list));
        quickSort(list);

        System.out.println("\nhasil akhir: "+Arrays.toString(list));
    }
}
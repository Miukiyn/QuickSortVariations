import java.util.Random;

public class QuickSortVariations {

    // QuickSort com o primeiro elemento como pivô
    public void QuickSortFirstPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionFirstPivot(array, left, right);
            QuickSortFirstPivot(array, left, pivotIndex - 1);
            QuickSortFirstPivot(array, pivotIndex + 1, right);
        }
    }


    // QuickSort com o último elemento como pivô
    public void QuickSortLastPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionLastPivot(array, left, right);
            QuickSortLastPivot(array, left, pivotIndex - 1);
            QuickSortLastPivot(array, pivotIndex + 1, right);
        }
    }

    private int partitionFirstPivot(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left + 1;
        for (int j = left + 1; j <= right; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, left, i - 1);
        return i - 1;
    }

    // QuickSort com pivô aleatório
    public void QuickSortRandomPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionRandomPivot(array, left, right);
            QuickSortRandomPivot(array, left, pivotIndex - 1);
            QuickSortRandomPivot(array, pivotIndex + 1, right);
        }
    }

    private int partitionLastPivot(int[] array, int left, int right) {
        swap(array, left, right); // Coloca o último elemento como pivô
        return partitionFirstPivot(array, left, right);
    }


    private int partitionRandomPivot(int[] array, int left, int right) {
        int randomPivotIndex = new Random().nextInt(right - left + 1) + left;
        swap(array, left, randomPivotIndex); // Coloca o pivô aleatório no início
        return partitionFirstPivot(array, left, right);
    }

    // QuickSort com mediana de três elementos
    public void QuickSortMedianOfThree(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionMedianOfThree(array, left, right);
            QuickSortMedianOfThree(array, left, pivotIndex - 1);
            QuickSortMedianOfThree(array, pivotIndex + 1, right);
        }
    }

    private int partitionMedianOfThree(int[] array, int left, int right) {
        int mid = (left + right) / 2;
        int medianIndex = medianOfThree(array, left, mid, right);
        swap(array, left, medianIndex); // Coloca a mediana no início
        return partitionFirstPivot(array, left, right);
    }

    private int medianOfThree(int[] array, int a, int b, int c) {
        if ((array[a] > array[b]) != (array[a] > array[c])) return a;
        if ((array[b] > array[a]) != (array[b] > array[c])) return b;
        return c;
    }

    // Função auxiliar para troca de elementos
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Função principal para medir o tempo de execução
    public static void main(String[] args) {
        QuickSortVariations qsv = new QuickSortVariations();
        Random random = new Random();
        
        int[] sizes = {100, 1000, 10000};
        for (int size : sizes) {
            System.out.println("Array size: " + size);
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(size);
            }

            // Cópias do array original para testar cada variação
            int[] arrayFirst = array.clone();
            int[] arrayLast = array.clone();
            int[] arrayRandom = array.clone();
            int[] arrayMedian = array.clone();

            long startTime, endTime;

            // Teste com o primeiro elemento como pivô
            startTime = System.nanoTime();
            qsv.QuickSortFirstPivot(arrayFirst, 0, arrayFirst.length - 1);
            endTime = System.nanoTime();
            System.out.println("QuickSortFirstPivot: " + (endTime - startTime) + " ns");

            // Teste com o último elemento como pivô
            startTime = System.nanoTime();
            qsv.QuickSortLastPivot(arrayLast, 0, arrayLast.length - 1);
            endTime = System.nanoTime();
            System.out.println("QuickSortLastPivot: " + (endTime - startTime) + " ns");

            // Teste com pivô aleatório
            startTime = System.nanoTime();
            qsv.QuickSortRandomPivot(arrayRandom, 0, arrayRandom.length - 1);
            endTime = System.nanoTime();
            System.out.println("QuickSortRandomPivot: " + (endTime - startTime) + " ns");

            // Teste com mediana de três elementos
            startTime = System.nanoTime();
            qsv.QuickSortMedianOfThree(arrayMedian, 0, arrayMedian.length - 1);
            endTime = System.nanoTime();
            System.out.println("QuickSortMedianOfThree: " + (endTime - startTime) + " ns");
            
            System.out.println();
        }
    }
}

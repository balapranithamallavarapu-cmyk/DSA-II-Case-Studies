import java.util.*;

public class MediFlow {

    // Quick Sort
    static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] > pivot) { // Higher priority first
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {

        // Patient severity scores
        int patients[] = {50, 90, 70, 30, 80};

        System.out.println("Patient Severity Scores Before Sorting:");
        System.out.println(Arrays.toString(patients));

        quickSort(patients, 0, patients.length - 1);

        System.out.println("\nPatient Priority Order:");
        System.out.println(Arrays.toString(patients));

        // Greedy Doctor Allocation
        int doctors = 3;

        System.out.println("\nPatients Assigned to Available Doctors:");

        for (int i = 0; i < doctors; i++) {
            System.out.println("Doctor " + (i + 1)
                    + " treats Patient with Severity "
                    + patients[i]);
        }
    }
}
import java.util.Random;
import java.util.Scanner;

/**
* The SortSearch program implements an application that
* sorts a random 250 value array and searches for a user
* inputted value using binary search.
*
* @author  Marcus A. Mosley
* @version 1.0
* @since   2021-01-28
*/
class SortSearch {
  /**
  * The partition method places the pivot inside the
  * sorted array and places the smaller and larger values
  * to the right and left of the pivot respectively.
  */
  public static int partition(int[] arr, int low, int high) { 
    int pivot = arr[high];  
    int index = (low - 1); // Index of smaller element 
    for (int counter = low; counter < high; counter++) { 
      // If current element is smaller than the pivot 
      if (arr[counter] < pivot) {
        index++;
        // Swap the Index and the Current Element
        int temp = arr[index]; 
        arr[index] = arr[counter]; 
        arr[counter] = temp; 
      } 
    }
    // Swap Index + 1 and Pivot 
    int temp = arr[index + 1]; 
    arr[index + 1] = arr[high]; 
    arr[high] = temp; 
  
    return index + 1; 
  } 
  
  /**
  * The sort method is the driver of the sorting algorithm.
  */
  public static void sort(int[] arr, int start, int length) { 
    if (start < length) {
      int partInd = partition(arr, start, length);
      // Sort elements before and after partition
      sort(arr, start, partInd - 1); 
      sort(arr, partInd + 1, length); 
    } 
  } 
  
  /**
  * The printArr method outputs every value in the
  * given array.
  */
  static void printArr(int[] arr) {
    for (int counter = 0; counter < arr.length; ++counter) {
      System.out.print(arr[counter] + ", ");
    }
    System.out.println(); 
  } 
  
  /**
  * The binarySearch method searches for a given value in an array.
  */
  public static int binarySearch(int[] arr, int start, int length, int search) { 
    if (length >= start) { 
      int mid = start + (length - start) / 2; 
      if (arr[mid] == search) {
        // If the element is present at the middle itself 
        return mid;
      } else if (arr[mid] > search) {
        // If element is smaller than mid, then it can only be present in left subarray 
        return binarySearch(arr, start, mid - 1, search);
      } 
      // Else the element can only be present in right subarray 
      return binarySearch(arr, mid + 1, length, search); 
    } 
    return -1; // Element is not present
  }

  /**
  * The Main method receives input and checks viability.
  */
  public static void main(String[] args) {
    int[] arr = new int[250];
    int search;

    Random rand = new Random();
    System.out.printf("The Random Array: ");
    for (int counter = 0; counter < arr.length - 1; counter++) {
      int randomNumber = rand.nextInt(1000);
      arr[counter] = randomNumber;
    }

    printArr(arr); 

    sort(arr, 0, arr.length - 1); 
  
    System.out.printf("\nSorted array: "); 
    printArr(arr);
        
    while (true) {
      try {
        // Input
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter a value: ");
        search = input.nextInt();
        if (search > 0) {
          break;
        } else {
          System.out.printf("That is not a valid input! \n");
        }
      } catch (Exception e) {
        System.out.printf("That is not a number, please input a number! \n");
      }
    }

    int result = binarySearch(arr, 0, arr.length - 1, search); 
    if (result == -1) {
      System.out.println("Value not present");
    } else {
      System.out.println("Value found at index " + result);
    }
  } 
} 
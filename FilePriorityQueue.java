import java.util.*;

public class FilePriorityQueue {
    
    private PriorityQueue<Integer> jobQueue;

    public FilePriorityQueue() {
        jobQueue = new PriorityQueue<>();
    }
    public void insertJob(int fileSize) {
        jobQueue.offer(fileSize);
        System.out.println("Inserted job with file size: " + fileSize);
    }
    public void deleteJob(int fileSize) {
        if (jobQueue.remove(fileSize)) {
            System.out.println("Deleted job with file size: " + fileSize);
        } else {
            System.out.println("Job with file size " + fileSize + " not found in the queue.");
        }
    }
    public void printJob() {
        if (!jobQueue.isEmpty()) {
            int jobSize = jobQueue.poll();
            System.out.println("Printing job with file size: " + jobSize);
        } else {
            System.out.println("No jobs left to print.");
        }
    }
    public void printAllJobs() {
        System.out.println("\nPrinting all jobs based on file size (smallest first):");
        while (!jobQueue.isEmpty()) {
            printJob();
        }
    }
     public static void main(String[] args) {
        FilePriorityQueue printerQueue = new FilePriorityQueue();
        Scanner inp = new Scanner(System.in);
        System.out.println("\nMenu:\n1. Insert job into queue\n2. Delete job from queue\n3. Print next job (minimum size)\n4. Print all jobs and clear queue\n5. Exit");
        while(true){
            System.out.print("Enter your choice: ");
            int choice=inp.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter file size to insert: ");
                    int fileSize = inp.nextInt();
                    printerQueue.insertJob(fileSize);
                    break;
                case 2:
                    System.out.print("Enter file size to delete: ");
                    int fileSizeToDelete = inp.nextInt();
                    printerQueue.deleteJob(fileSizeToDelete);
                    break;
                case 3:
                    System.out.println("Printing next job with minimum size...");
                    printerQueue.printJob();
                    break;
                case 4:
                    printerQueue.printAllJobs();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return ;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

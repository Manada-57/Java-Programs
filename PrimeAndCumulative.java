import java.util.*;
import java.lang.Math;

public class PrimeAndCumulative {

    private static List<Integer> primes = new ArrayList<>();
    private static List<Integer> cumulativeSeries = new ArrayList<>();
    private static List<Integer> commonElements = new ArrayList<>();
    public static void generatePrimes() {
        try {
            int limit = 50000;
            for(int i=0;i<=limit;i++){
                if(isPrime(i))
                {
                    primes.add(i);
                }
            } 
        
        Thread.sleep(1000); 
        System.out.println("Prime generation complete!");
        }
        catch (InterruptedException e) {
        System.err.println("Prime generation thread was interrupted.");
    }
}
        private static boolean isPrime(int num){
            if(num<2)
            return false;
            for(int i=2;i<=Math.sqrt(num);i++){
                if(num%i==0){
                    return false;
                }
            
            }
            return true;
    }
    public static void generateCumulativeSeries() {
        try {
            int total = 0;
            int i = 1;
            while (total < 50000) {
                total += i;
                cumulativeSeries.add(total);
                i++;
            }
            Thread.sleep(1000); 
            System.out.println("Cumulative series generation complete!");

        } catch (InterruptedException e) {
            System.err.println("Cumulative series thread was interrupted.");
        }
    }

    public static void findCommonElements() {
        try {
            Set<Integer> primesSet = new HashSet<>(primes);
            Set<Integer> cumulativeSet = new HashSet<>(cumulativeSeries);
            primesSet.retainAll(cumulativeSet);
            commonElements.addAll(primesSet);

            // Simulate processing time with a delay
            Thread.sleep(1000); // Add a delay of 1 second
            System.out.println("Common elements calculation complete!");

        } catch (InterruptedException e) {
            System.err.println("Common elements thread was interrupted.");
        }
    }
    static class PrimeThread extends Thread {
        @Override
        public void run() {
            generatePrimes();
        }
    }

    static class CumulativeThread extends Thread {
        @Override
        public void run() {
            generateCumulativeSeries();
        }
    }

    static class CommonThread extends Thread {
        @Override
        public void run() {
            findCommonElements();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        PrimeThread primeThread = new PrimeThread();
        CumulativeThread cumulativeThread = new CumulativeThread();
        CommonThread commonThread = new CommonThread();
        primeThread.start();
        cumulativeThread.start();
        Thread.sleep(500); 
        primeThread.join();
        cumulativeThread.join();
        commonThread.start();
        commonThread.join();
        System.out.println("\nAll Prime Numbers below 50,000:");
        System.out.println(primes);
        System.out.println("\nAll Cumulative Series numbers up to 50,000:");
        System.out.println(cumulativeSeries);
        System.out.println("\nAll Common Elements between Primes and Cumulative Series:");
        System.out.println(commonElements);
    }
}

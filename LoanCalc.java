/*
 * // Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
        double periodRate = rate / 100 / 12; // ריבית חודשית
        for (int i = 0; i < n; i++) {
            balance = (balance * (1 + periodRate)) - payment; // ריבית ותשלום
        }
        return balance;
	
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
        double payment = 0; // תשלום התחלתי

        while (endBalance(loan, rate, n, payment) > epsilon) {
            payment += epsilon; 
            iterationCounter++; 
        }

        return payment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
        double periodRate = rate / 100 / 12; // ריבית חודשית
        double min = 0;
        double max = loan * Math.pow(1 + periodRate, n);
        double mid = 0;

        while ((max - min) > epsilon) { 
            mid = (max + min) / 2; 
            double balance = endBalance(loan, rate, n, mid);
            if (Math.abs(balance) <= epsilon) {
                return mid;
            }

            if (balance > 0) {
                min = mid; 
            } else {
                max = mid; 
            }
            iterationCounter++; 
        }
        return mid;
	}

    

}
 */

 public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    public static void main(String[] args) {		
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    private static double endBalance(double loan, double rate, int n, double payment) {	

        for (int i = 0; i < n; i++) {
            loan = (loan - payment)*(1+rate/100);
        }
        return loan;
    }

    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double payment = loan / n; 

        while (endBalance(loan, rate, n, payment) > 0) {
            payment += epsilon; 
            iterationCounter++; 
        }

        return payment;
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
        double min = loan / n; // תשלום מינימלי
        double max = loan * (1+rate/100);
        double mid = 0;

        while ((max - min) > epsilon) { 
            mid = (max + min) / 2; 
            double balance = endBalance(loan, rate, n, mid);
            if (Math.abs(balance) < epsilon/ 10) {
                break;
            }

            if (balance > 0) {
                min = mid; 
            } else {
                max = mid; 
            }
            iterationCounter++; 
        }
		mid=(max+min)/2;
        return mid;
    }
}

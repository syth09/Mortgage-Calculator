package mortgage;

public class MortgageCalculator {
    // Static fields on the top
    private final static byte MONTH_IN_YEARS = 12;
    private final static byte PERCENT = 100;
    // Bellow we got our instance fields
    private int principal;
    private float annualInterest;
    private byte years;

    // Constructor
    public MortgageCalculator( int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    // Mortgage calculate methods
    public double calculateMortgage() {

        // Calculate the mortgage payment
        float monthlyInterest = getMonthlyInterest();
        float numberOfPayment = getNumberOfPayment();

        return principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayment))
                / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1);
    }

    // calculate balance methods
    public double calculateBalance(short numberOfPaymentsMade) {
        float monthlyInterest = getMonthlyInterest();
        short numberOfPayment = (short) getNumberOfPayment();

        return principal
                * (Math.pow(1 + monthlyInterest, numberOfPayment)
                - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1);
    }

    // Separating calculate logic from presentation logic:
    public double[] getRemainingBalances() {
        var balances = new double[getNumberOfPayment()];
        for (short month = 1; month <= balances.length; month++)
            balances[month - 1] = calculateBalance(month);
        return balances;
    }

    // Getters and Setters at the bottom
    private int getNumberOfPayment() {
        return years * MONTH_IN_YEARS;
    }

    private float getMonthlyInterest() {
        return annualInterest / MONTH_IN_YEARS / PERCENT;
    }
}

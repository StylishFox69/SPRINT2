public class YearReport {
    int monthNumber;
    int amount;
    boolean isExpense;

    public YearReport(int monthNumber, int amount, boolean isExpense) {
        this.monthNumber = monthNumber;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    @Override
    public String toString() {
        return "YearReport{" +
                "monthNumber=" + monthNumber +
                ", amount=" + amount +
                ", isExpense=" + isExpense +
                '}';
    }
}

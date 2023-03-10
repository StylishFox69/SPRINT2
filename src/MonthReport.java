public class MonthReport {
    String name;
    boolean isExpense;
    int quantity;
    int sumOfOne;
    int month;
    public MonthReport(String name, boolean isExpense, int quantity, int sumOfOne, int month) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.month = month;
    }
    public int getTotal(){
        return quantity*sumOfOne;
    }

    @Override
    public String toString() {
        return "MonthReport{" +
                "name='" + name + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", sumOfOne=" + sumOfOne +
                ", month=" + month +
                '}';
    }
}

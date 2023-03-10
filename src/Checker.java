import java.util.HashMap;

public class Checker {
    public ReportService service;

    public Checker(ReportService service) {
        this.service = service;
    }

    public void check() {
        boolean check = true;
        HashMap<Integer, HashMap<Boolean, Integer>> monthToAmount = new HashMap<>();
        for (MonthReport monthItem : service.monthItems) {
            if (!monthToAmount.containsKey(monthItem.month)) {
                monthToAmount.put(monthItem.month, new HashMap<>());
            }
            HashMap<Boolean, Integer> isExpenseToAmount = monthToAmount.get(monthItem.month);
            isExpenseToAmount.put(monthItem.isExpense, isExpenseToAmount.getOrDefault(monthItem.isExpense, 0) + (monthItem.quantity * monthItem.sumOfOne));

        }
// FOR...
        HashMap<Integer, HashMap<Boolean, Integer>> yearlyMonthToAmount = new HashMap<>();
        for (YearReport yearToAmount : service.yearItems) {
            if (!yearlyMonthToAmount.containsKey(yearToAmount.monthNumber)) {
                yearlyMonthToAmount.put(yearToAmount.monthNumber, new HashMap<>());
            }
            HashMap<Boolean, Integer> isExpenseToAmountY = yearlyMonthToAmount.get(yearToAmount.monthNumber);
            isExpenseToAmountY.put(yearToAmount.isExpense, isExpenseToAmountY.getOrDefault(yearToAmount.isExpense, 0) + yearToAmount.amount);
        }
        for (Integer month : monthToAmount.keySet()) {
            HashMap<Boolean, Integer> isExpenseToAmountByMonth = monthToAmount.get(month);
            HashMap<Boolean, Integer> isExpenseToAmountByYear = yearlyMonthToAmount.get(month);

            if(isExpenseToAmountByYear == null){
               System.out.println("Статья отсутствует");
               check = false;
               continue;
            }

            for (Boolean isExpense : isExpenseToAmountByMonth.keySet()) {
                int amountByMonth = isExpenseToAmountByMonth.get(isExpense);
                int amountByYear = isExpenseToAmountByYear.get(isExpense);
if(amountByMonth != amountByYear){
    System.out.println("В месяце " + month + " Не сходятся отчёты.");
    check = false;
}
            }
        }
        if(check == true){
            System.out.println("Сверка отчетов проведена успешно. Несовпадений не выявлено!");
        }
    }
}

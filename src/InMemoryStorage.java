import java.util.ArrayList;

import java.util.HashMap;
public class InMemoryStorage {
    HashMap<Integer, ArrayList<MonthReport>> monthStorage = new HashMap<>();
    HashMap<Integer, ArrayList<YearReport>> yearStorage = new HashMap<>();

    public void saveMonthReport(int month, ArrayList<MonthReport> monthReports) {
        monthStorage.put(month, monthReports);
    }

    public void saveYearReport(int year, ArrayList<YearReport> yearReports) {
        yearStorage.put(year, yearReports);
    }

    public int getAverageExpense() { // средняя трата за месяц
        ArrayList<YearReport> reports = yearStorage.get(2021);
        int sum = 0;
        int average;
        for (YearReport report : reports) {
            if (!report.isExpense) {
                continue;
            }
            sum += report.amount;
        }
        average = sum / reports.size();
        return average;
    }

    public int getAverageEarning() {// средний заработок за месяц
        ArrayList<YearReport> reports = yearStorage.get(2021);
        int sum = 0;
        int average;
        for (YearReport report : reports) {
            if (report.isExpense) {
                continue;
            }
            sum += report.amount;
        }
        average = sum / reports.size();
        return average;
    }

    public int getProfit(int i) { // поиск помесячной прибыли
        ArrayList<YearReport> reports = yearStorage.get(2021);
        int amount = 0;
        for (YearReport report : reports) {
            if (report.monthNumber == i) {
                if (report.isExpense) {
                    amount -= report.amount;
                } else {
                    amount += report.amount;
                }
            }
        }
        return amount;
    }

    public MonthReport getMaxEarning(int month) { // поиск максимального поступления за месяц
        ArrayList<MonthReport> items = monthStorage.get(month);
        MonthReport max = null;
        int total = 0;
        for (MonthReport item : items) {
            if (item.isExpense) {
                continue;
            }
            if (item.getTotal() > total) {
                total = item.getTotal();
                max = item;
            }
        }
        return max;
    }

    public MonthReport getMaxExpense(int month) {// поиск максимальной траты за месяц
        ArrayList<MonthReport> items = monthStorage.get(month);
        MonthReport max = null;
        int total = 0;
        for (MonthReport item : items) {
            if (!item.isExpense) {
                continue;
            }
            if (item.getTotal() > total) {
                total = item.getTotal();
                max = item;
            }
        }
        return max;
    }
}

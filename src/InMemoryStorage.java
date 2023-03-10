import java.util.ArrayList;

import java.util.LinkedHashMap;

public class InMemoryStorage {
    LinkedHashMap<Integer, ArrayList<MonthReport>> monthStorage = new LinkedHashMap<>();
    LinkedHashMap<Integer, ArrayList<YearReport>> yearStorage = new LinkedHashMap<>();




    public void saveMonthReport(int month, ArrayList<MonthReport> monthReports) {
        monthStorage.put(month, monthReports);
    }

    public void saveYearReport(int year, ArrayList<YearReport> yearReports) {
        yearStorage.put(year, yearReports);
    }

    public int getAverageExpense() {
        ArrayList<YearReport> reports = yearStorage.get(2021);
        int sum = 0;
        int average = 0;
        for (YearReport report : reports) {
            if (!report.isExpense) {
                continue;
            }
            sum += report.amount;

        }
        average = sum / reports.size();
        return average;
    }

    public int getAverageEarning() {
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

    public int getProfit(int i) {
        ArrayList<YearReport> reports = yearStorage.get(2021);
        int amount = 0;
        for (YearReport report : reports) {
            if(report.monthNumber == i){
                if(report.isExpense){
                    amount -= report.amount;
                }else {
                    amount += report.amount;
                }
            }
        }
        return  amount;
    }

    public MonthReport getMaxEarning(int month) {
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

    public MonthReport getMaxExpense(int month) {
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

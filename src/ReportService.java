import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportService {
    InMemoryStorage storage = new InMemoryStorage();

ArrayList<MonthReport> monthItems = new ArrayList<>();
ArrayList<YearReport> yearItems = new ArrayList<>();

    public void loadYearReports() {
        String path = "./resources/y.2021.csv";
        ArrayList<YearReport> yearReports = loadYearReport(path);
        storage.saveYearReport(2021, yearReports);
    }

    ArrayList<YearReport> loadYearReport(String path) {
        List<String> lines = readFileContents(path);
        ArrayList<YearReport> yearReports = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] rows = line.split(",");
            YearReport yearReport = new YearReport(
                    Integer.parseInt(rows[0]),
                    Integer.parseInt(rows[1]),
                    Boolean.parseBoolean(rows[2]));
            yearReports.add(yearReport);
            yearItems.add(yearReport);
        }


        return yearReports;
    }


    public void loadMonthReports() {
        for (int i = 1; i <= 3; i++) {
            String path = "./resources/m.20210" + i + ".csv";
            ArrayList<MonthReport> monthReports = loadMonthReport(path, i);
            storage.saveMonthReport(i, monthReports);
        }
    }

    ArrayList<MonthReport> loadMonthReport(String path, int month) {
        List<String> lines = readFileContents(path);
        ArrayList<MonthReport> monthReports = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] rows = line.split(",");
            MonthReport monthReport = new MonthReport(rows[0],
                    Boolean.parseBoolean(rows[1]),
                    Integer.parseInt(rows[2]),
                    Integer.parseInt(rows[3]), (month));
            monthReports.add(monthReport);
            monthItems.add(monthReport);
        }


        return monthReports;
    }


    public String getMonthName(int month) {
        String monthName = null;
        if (month == 1) {
            monthName = "Январь";
        } else if (month == 2) {
            monthName = "Февраль";
        } else if (month == 3) {
            monthName = "Март";
        }
        return monthName;
    }


    public void printMonthReports() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Месяц: " + getMonthName(i));
            MonthReport maxEarning = storage.getMaxEarning(i);
            System.out.println("Максимальный доход. Товар: " + maxEarning.name + ". Сумма: " + maxEarning.getTotal());
            MonthReport maxExpense = storage.getMaxExpense(i);
            System.out.println("Максимальный расход. Товар: " + maxExpense.name + ". Сумма: " + maxExpense.getTotal());


        }
    }

    public void printYearReport() {
        System.out.println("2021 год");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Прибыль за " + getMonthName(i) +
                    ": " + storage.getProfit(i));
        }
        System.out.println("Средний расход за год: " + storage.getAverageExpense());
        System.out.println("Средний доход за год: " + storage.getAverageEarning());
    }


    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}


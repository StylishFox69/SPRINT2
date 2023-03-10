import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
            if (rows.length < 3) {
                continue;
            }
            YearReport yearReport = new YearReport(
                    Integer.parseInt(rows[0]),
                    Integer.parseInt(rows[1]),
                    Boolean.parseBoolean(rows[2]));
            yearReports.add(yearReport);
            yearItems.add(yearReport);
        }
        return yearReports;
    }

    public void loadMonthReports() { //Загрузка месячных отчетов в мапу по месяцу (ключ) и строке отчета (значение)
        for (int i = 1; i <= 3; i++) {
            String path = "./resources/m.20210" + i + ".csv";
            ArrayList<MonthReport> monthReports = loadMonthReport(path, i);
            storage.saveMonthReport(i, monthReports);// вложение в мапу
        }
    }

    ArrayList<MonthReport> loadMonthReport(String path, int month) { // создание временного списка месячного отчета
        List<String> lines = readFileContents(path);
        ArrayList<MonthReport> monthReports = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] rows = line.split(",");
            if (rows.length < 4) {
                continue;
            }
            MonthReport monthReport = new MonthReport(rows[0],
                    Boolean.parseBoolean(rows[1]),
                    Integer.parseInt(rows[2]),
                    Integer.parseInt(rows[3]), (month));
            monthReports.add(monthReport); // временный список
            monthItems.add(monthReport); // постоянный список за все месяцы
        }
        return monthReports;
    }

    public String getMonthName(int month) { //узнать имя месяца
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

    public void printMonthReports() {//печать запрошенной по ТЗ информации о месячном отчёте
        if (monthItems.size() == 0) {
            System.out.println("Похоже, вы не выполнили загрузку данных, для начала введите 1");
        } else {
            for (int i = 1; i <= 3; i++) {

                System.out.println("Месяц: " + getMonthName(i));
                MonthReport maxEarning = storage.getMaxEarning(i);
                System.out.println("Максимальный доход. Товар: " + maxEarning.name + ". Сумма: " + maxEarning.getTotal());
                MonthReport maxExpense = storage.getMaxExpense(i);
                System.out.println("Максимальный расход. Товар: " + maxExpense.name + ". Сумма: " + maxExpense.getTotal());
            }
        }
    }

    public void printYearReport() {//печать запрошенной по ТЗ информации о годовом отчёте
        if (yearItems.size() == 0) {
            System.out.println("Похоже, вы не выполнили загрузку данных, для начала введите 2");
        } else {
            System.out.println("2021 год");
            for (int i = 1; i <= 3; i++) {
                System.out.println("Прибыль за " + getMonthName(i) +
                        ": " + storage.getProfit(i));
            }
            System.out.println("Средний расход за год: " + storage.getAverageExpense());
            System.out.println("Средний доход за год: " + storage.getAverageEarning());
        }
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


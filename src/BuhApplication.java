import java.util.Scanner;

public class BuhApplication {

    public void run() {
        ReportService service = new ReportService();
        Checker checker = new Checker(service);
        System.out.println("Enter command ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                return;
            } else if (line.equals("1")) {
                System.out.println("starting");
                service.loadMonthReports();
                System.out.println("done");
            } else if (line.equals("2")) {
                System.out.println("starting");
                service.loadYearReports();
                System.out.println("done");
            } else if (line.equals("3")) {
                checker.check();
            } else if (line.equals("4")) {
                service.printMonthReports();
            } else if (line.equals("5")) {
                service.printYearReport();
            } else {
                System.out.println("Error");
            }
        }
    }

    private void printMenu() { // Меню
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
    }
}

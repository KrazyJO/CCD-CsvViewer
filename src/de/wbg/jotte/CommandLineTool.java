package de.wbg.jotte;

import java.util.Scanner;

public class CommandLineTool {

    private final String menuLine = "F)irst page, P)revious page, N)ext page, L)ast page, E)xit";
    private CsvOutput outputter;

    void printMenu() {
        var page = this.outputter.currentPage + 1;
        var lastPage = this.outputter.getLastPageCount();
        var pageString = "Page " + page + " of " + lastPage;

        System.out.println();
        System.out.println(pageString);
        System.out.println(menuLine);
    }

    void expectInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String chars = scanner.next().toLowerCase();

            if (chars.equals("e"))
                break;

            if (chars.equals("f"))
                printFirstPage();

            if (chars.equals("p"))
                printPreviousPage();

            if (chars.equals("n"))
                printNextPage();

            if (chars.equals("l"))
                printLastPage();
        }
    }

    private void printFirstPage() {
        printPage(0);
    }

    private void printLastPage() {
        int lastPageIndex = this.outputter.getLastPageCount() - 1;
        if (lastPageIndex < 0) {
            lastPageIndex = 0;
        }
        printPage(lastPageIndex);
    }

    private void printNextPage() {
        var page = this.outputter.currentPage;
        if (isLastPage(page)) {
            printPage(page);
            return;
        }

        page++;
        printPage(page);
    }

    private void printPreviousPage() {
        var page = this.outputter.currentPage;
        if (page > 0) {
            page--;
        }

        printPage(page);
    }

    private void printPage(int page) {
        clearCommandLine();
        this.outputter.outputPage(page);
        printMenu();
    }

    private boolean isLastPage(int page) {
        var lastPage = this.outputter.getLastPageCount() - 1;
        return page >= lastPage;
    }

    private void clearCommandLine() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void setOutputter(CsvOutput outputter) {
        this.outputter = outputter;
    }
}

package de.wbg.jotte;

import java.util.Scanner;

public class CommandLineTool {

    enum InputMode {
        normal,
        page
    }

    private final String menuLine = "F)irst page, P)revious page, N)ext page, L)ast page, J)ump to page, E)xit";
    private final String enterPageNumberLine = "Please enter the page number of you're wishes:";

    private CsvOutput outputter;
    private InputMode inputMode = InputMode.normal;

    void expectInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String chars = scanner.next().toLowerCase();

            if (inputMode == InputMode.normal) {
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

                if (chars.equals("j")) {
                    printEnterPageNumber();
                    switchToPagesInputMode();
                }

            } else {
                try {
                    int newPageIndexNumber = Integer.parseInt(chars) - 1;
                    if (isPagePossible(newPageIndexNumber)) {
                        printPage(newPageIndexNumber);
                        switchToNormalInputMode();
                    } else {
                        printRepeatNumberInput(chars);
                    }
                } catch (NumberFormatException e) {
                    printRepeatNumberInput(chars);
                }
            }
        }
    }
    
    private void switchToNormalInputMode() {
        inputMode = InputMode.normal;
    }
    
    private void switchToPagesInputMode() {
        inputMode = InputMode.page;
    }

    void printMenu() {
        var page = this.outputter.currentPage + 1;
        var lastPage = this.outputter.getLastPageCount();
        var pageString = "Page " + page + " of " + lastPage;

        System.out.println();
        System.out.println(pageString);
        System.out.println(menuLine);
    }

    private void printRepeatNumberInput(String chars) {
        System.out.println("Your input " + chars + " was no number. Please enter a number.");
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
        if (isLastPageOrMore(page)) {
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

    private void printEnterPageNumber() {
        System.out.println(enterPageNumberLine);
    }

    private boolean isLastPageOrMore(int page) {
        var lastPage = this.outputter.getLastPageCount() - 1;
        return page >= lastPage;
    }

    private boolean isPagePossible(int page) {
        return page >= 0 && page < this.outputter.getLastPageCount();
    }

    private void clearCommandLine() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void setOutputter(CsvOutput outputter) {
        this.outputter = outputter;
    }
}

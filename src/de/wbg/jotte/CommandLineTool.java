package de.wbg.jotte;

import java.util.Scanner;

public class CommandLineTool {

    enum InputMode {
        normal,
        enterPageNumber,
        enterColumnNameForSort
    }

    private final String menuLine = "F)irst page, P)revious page, N)ext page, L)ast page, J)ump to page, S)ort, E)xit";
    private final String enterPageNumberLine = "Please enter the page number of you're wishes:";
    private final String enterColumnNameForSort = "Please enter column name to sort on:";
    private final String errorColumnNotFound = "The columnName you entered was not found. Please try again!";

    private CsvOutput outputter;
    private InputMode inputMode = InputMode.normal;

    private Scanner scanner;

    private String readNextCharFromScanner() {
        return scanner.next().toLowerCase();
    }

    private void setupScanner() {
        scanner = new Scanner(System.in);
    }

    void expectInput() {
        setupScanner();

        while (true) {
            String chars = readNextCharFromScanner();

            if (inputMode == InputMode.normal) {
                boolean exit = processNormalInput(chars);
                if (exit) {
                    break;
                }

            } else if (inputMode == InputMode.enterPageNumber) {
                processEnterPageNumberInput(chars);
            } else {
                processEnterColumnNameForSortInput(chars);
            }
        }
    }

    private boolean processNormalInput(String chars) {
        switch (chars) {
            case "e":
                return true;
            case "f":
                printFirstPage();
                break;
            case "p":
                printPreviousPage();
                break;
            case "n":
                printNextPage();
                break;
            case "l":
                printLastPage();
                break;
            case "j":
                printEnterPageNumber();
                setInputMode(InputMode.enterPageNumber);
                break;
            case "s":
                printEnterColumnNameForSorting();
                setInputMode(InputMode.enterColumnNameForSort);
                break;
        }

        return false;
    }

    private void processEnterColumnNameForSortInput(String columnName) {
        boolean isSorted = outputter.data.sortByColumn(columnName);
        if (!isSorted) {
            printErrorSortColumnNotFound();
        } else {
            setInputMode(InputMode.normal);
            printPage(outputter.currentPage);
        }
    }

    private void processEnterPageNumberInput(String chars) {
        try {
            int newPageIndexNumber = Integer.parseInt(chars) - 1;
            if (isPagePossible(newPageIndexNumber)) {
                printPage(newPageIndexNumber);
                setInputMode(InputMode.normal);
            } else {
                printRepeatNumberInput(chars);
            }
        } catch (NumberFormatException e) {
            printRepeatNumberInput(chars);
        }
    }

    private void setInputMode(InputMode mode) {
        this.inputMode = mode;
    }

    void printErrorSortColumnNotFound() {
        System.out.println(errorColumnNotFound);
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

    private void printEnterColumnNameForSorting() {
        System.out.println(enterColumnNameForSort);
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

package de.wbg.jotte;

import java.util.Scanner;

public class CommandLineTool {

    enum InputMode {
        normal,
        enterPageNumber,
        enterColumnNameForSort
    }

    private CsvOutput outputter;
    private InputMode inputMode = InputMode.normal;

    private Scanner scanner;

    private String readNextCharFromInputScanner() {
        return scanner.next().toLowerCase();
    }

    private void setupInputScanner() {
        scanner = new Scanner(System.in);
    }

    void expectInput() {
        setupInputScanner();

        while (true) {
            String chars = readNextCharFromInputScanner();

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

    private void printErrorSortColumnNotFound() {
        System.out.println(Messages.errorColumnNotFound);
    }

    public void printErrorArgNotParsed(String error) {
        System.out.println(error);
        System.out.println(Messages.standartRows);
    }

    void printMenu() {
        var page = this.outputter.currentPage + 1;
        var lastPage = this.outputter.getLastPageCount();
        var pageString = "Page " + page + " of " + lastPage;

        System.out.println();
        System.out.println(pageString);
        System.out.println(Messages.menuLine);
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
        System.out.println(Messages.enterPageNumberLine);
    }

    private void printEnterColumnNameForSorting() {
        System.out.println(Messages.enterColumnNameForSort);
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

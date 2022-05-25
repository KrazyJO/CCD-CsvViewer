package de.wbg.jotte;

public class PageArgResult {

    int pageSizes;
    boolean argFound;

    boolean hasError;

    String errorReason;

    public PageArgResult() {

    }

    public int getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(int pageSizes) {
        this.pageSizes = pageSizes;
    }

    public boolean isArgFound() {
        return argFound;
    }

    public void setArgFound(boolean argFound) {
        this.argFound = argFound;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}

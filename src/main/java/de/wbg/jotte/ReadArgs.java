package de.wbg.jotte;

import org.jetbrains.annotations.NotNull;

public class ReadArgs {

    public static PageArgResult readPageSize(String @NotNull [] args) {

        PageArgResult pageArgResult = new PageArgResult();
        pageArgResult.setArgFound(false);
        pageArgResult.setHasError(true);

        if (args.length > 0) {
            // we assume that the first arg is the page size and the user will never ever do a failure
            try {
                var pageSize = Integer.parseInt(args[0]);
                pageArgResult.setPageSizes(pageSize);
                pageArgResult.setArgFound(true);
                pageArgResult.setHasError(false);
            } catch (Exception e) {
                pageArgResult.setErrorReason(Messages.noNumber +args[0]);
            }

        }

        return pageArgResult;
    }
}

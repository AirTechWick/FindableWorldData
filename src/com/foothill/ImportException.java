package com.foothill;

import java.io.IOException;

// The purpose of this class is to create an Exception for failing to import any data.
public class ImportException extends IOException {

    private int failCount;

    ImportException(int failCount) {
        super("<" + failCount + "> non-matching or failed imports");
        this.failCount = failCount;
    }
}

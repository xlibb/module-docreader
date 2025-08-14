# Document Reader Module

## Overview

The `xlibb/docreader` module provides functionality to read various document formats and extract their content using Apache Tika.

## Usage

```ballerina
import xlibb/docreader;
import ballerina/io;

public function main() returns error? {
    docreader:DocumentInfo|docreader:Error result = docreader:parseDocument("./document.pdf");
    
    if result is docreader:DocumentInfo {
        io:println("Document Type: ", result.docType);
        io:println("Extension: ", result.extension);
        io:println("Content: ", result.content);
    } else {
        io:println("Error: ", result.message());
    }
}
```

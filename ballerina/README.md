# Ballerina DocReader Library

A Ballerina library for reading documents using Apache Tika.

## Overview

The DocReader library leverages Apache Tika to parse and extract information from various document formats.

## Installation

Add the dependency to your `Ballerina.toml` file:

```toml
[dependency]
org = "xlibb"
name = "docreader"
version = "0.1.0"
```

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

## Building from Source

```bash
git clone https://github.com/xlibb/module-docreader.git
cd module-docreader
./gradlew build
```

## Issues and Contributing

Report issues and contribute at: https://github.com/xlibb/module-docreader

## License

This project is licensed under the Apache License 2.0.

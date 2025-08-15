# Ballerina DocReader Library

A Ballerina library for reading various document formats and extracting their content using Apache Tika.

## Overview

The DocReader library leverages Apache Tika to parse and extract information from various document formats including TXT, DOCX, PDF, XLS, PPT, HTML, CSV, XML, JSON, and RTF.

## Usage

```ballerina
import xlibb/docreader;
import ballerina/io;

public function main() returns error? {
    // Read a document file and extract its content
    docreader:DocumentInfo|docreader:Error result = docreader:readDocument("./document.pdf");
    
    if result is docreader:DocumentInfo {
        io:println("MIME Type: ", result.mimeType);
        io:println("Extension: ", result.extension);
        io:println("Content: ", result.content);
    } else {
        io:println("Error: ", result.message());
    }
}
```

### API

#### `readDocument(string filePath) returns DocumentInfo|Error`

Reads a document file and extracts its type, extension, and content.

**Parameters:**
- `filePath` - The absolute or relative path to the document file

**Returns:**
- `DocumentInfo` record with `mimeType`, `extension`, and `content` fields
- `Error` if the file cannot be read or parsed

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

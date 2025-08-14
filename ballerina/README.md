# Ballerina DocReader Library

A Ballerina library for reading documents using Apache Tika. This library provides APIs to extract document type, extension, and content from various document formats.

## Overview

The DocReader library leverages Apache Tika to parse and extract information from multiple document formats including:

- Plain text files (.txt)
- PDF documents (.pdf)  
- Microsoft Office documents (.docx, .xlsx, .pptx)
- OpenDocument formats (.odt, .ods, .odp)
- HTML and XML files (.html, .xml)
- And many more formats supported by Apache Tika

## Installation

Add the dependency to your `Ballerina.toml` file:

```toml
[dependency]
org = "xlibb"
name = "docreader"
version = "0.1.0"
```

## Usage

### Basic Document Reading

```ballerina
import xlibb/docreader;
import ballerina/io;

public function main() returns error? {
    docreader:DocumentInfo|docreader:Error result = docreader:parseDocument("./document.pdf");
    
    if result is docreader:DocumentInfo {
        io:println("Document Type: ", result.docType);
        io:println("Extension: ", result.extension);
        io:println("Content Length: ", result.content.length());
        io:println("Content: ", result.content);
    } else {
        io:println("Error parsing document: ", result.message());
    }
}
```

## API Reference

### Functions

#### `parseDocument(string filePath) returns DocumentInfo|Error`

Parses a document file and extracts its type, extension, and content using Apache Tika's AutoDetectParser.

**Parameters:**
- `filePath` - The absolute or relative path to the document file to be parsed

**Returns:**
- `DocumentInfo` record containing the document type, extension, and content on success
- `Error` on failure (file not found, parsing error, etc.)

### Types

#### `DocumentInfo`

Record containing parsed document information:

```ballerina
public type DocumentInfo record {|
    string docType;    // MIME type (e.g., "application/pdf", "text/plain")
    string extension;  // File extension (e.g., "pdf", "txt")  
    string content;    // Extracted text content
|};
```

## Error Handling

The library provides descriptive error messages for different failure scenarios:

```ballerina
docreader:DocumentInfo|docreader:Error result = docreader:parseDocument("./document.pdf");

if result is docreader:Error {
    // Handle parsing errors
    io:println("Parsing failed: ", result.message());
} else {
    // Process successful result
    io:println("Parsed successfully: ", result.docType);
}
```

## Supported Formats

The library supports a wide range of document formats through Apache Tika:

- **Text:** .txt, .rtf
- **PDF:** .pdf
- **Microsoft Office:** .doc, .docx, .xls, .xlsx, .ppt, .pptx
- **OpenDocument:** .odt, .ods, .odp
- **Web:** .html, .htm, .xml
- **And many more...**

## Building from Source

```bash
git clone https://github.com/AzeemMuzammil/module-docreader.git
cd module-docreader
./gradlew build
```

## Testing

```bash
cd ballerina
bal test
```

## Issues and Contributing

Report issues and contribute at: https://github.com/AzeemMuzammil/module-docreader

## License

This project is licensed under the Apache License 2.0.

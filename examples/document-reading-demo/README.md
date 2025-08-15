# DocReader Library - Document Reading Demo

A demonstration of the Ballerina DocReader library for parsing various document formats.

## Project Structure

```
document-reading-demo/
├── main.bal         # Main demonstration program
├── Ballerina.toml   # Project configuration
└── resources/       # Sample documents (10 formats)
```

## Running the Demo

```bash
cd examples/document-reading-demo
bal run
```

## Features

- Multi-format document parsing (TXT, DOCX, PDF, XLS, PPT, HTML, CSV, XML, JSON, RTF)
- Error handling examples
- Content analysis

## Usage Examples

### Basic Document Reading

```ballerina
import xlibb/docreader;
import ballerina/io;

public function main() returns error? {
    docreader:DocumentInfo|docreader:Error result = docreader:readDocument("./resources/sample.pdf");

    if result is docreader:DocumentInfo {
        io:println("MIME Type: ", result.mimeType);
        io:println("Extension: ", result.extension);
        io:println("Content Length: ", result.content.length());
    } else {
        io:println("Error: ", result.message());
    }
}
```

### Error Handling

```ballerina
docreader:DocumentInfo|docreader:Error result = docreader:readDocument("./non-existent.txt");
if result is docreader:Error {
    io:println("Failed to read document: ", result.message());
}
```

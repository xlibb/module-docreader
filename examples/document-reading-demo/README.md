# DocReader Library - Document Reading Demo

A demonstration of the Ballerina DocReader library for parsing various document formats.

## Project Structure

```
document-reading-demo/
├── main.bal         # Main demonstration program
├── Ballerina.toml   # Project configuration
├── Dependencies.toml # Dependency lock file
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

## Usage Example

```ballerina
import xlibb/docreader;

docreader:DocumentInfo|docreader:Error result = docreader:parseDocument("./resources/sample.pdf");

if result is docreader:DocumentInfo {
    // Process document info
} else {
    // Handle error
}
```

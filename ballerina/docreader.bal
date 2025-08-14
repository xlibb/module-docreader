// Copyright (c) 2024, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

import ballerina/jballerina.java;

# Parses a document file and extracts its type, extension, and content.
#
# This function uses Apache Tika to automatically detect the document type and extract text content
# from various document formats including:
# - PDF documents
# - Microsoft Office documents (Word, Excel, PowerPoint)  
# - OpenDocument formats (ODF)
# - HTML and XML files
# - Plain text files
# - And many other formats supported by Apache Tika
#
# + filePath - The absolute or relative path to the document file to be parsed
# + t - Type descriptor for the return type (optional, defaults to DocumentInfo)
# + return - `DocumentInfo` record containing the document type, extension, and content, or `Error` on failure
#
# # Examples
# ```ballerina
# import xlibb/docreader;
# import ballerina/io;
#
# public function main() returns error? {
#     docreader:DocumentInfo|docreader:Error result = docreader:parseDocument("./document.pdf");
#     if result is docreader:DocumentInfo {
#         io:println("Document Type: ", result.docType);
#         io:println("Extension: ", result.extension);
#         io:println("Content: ", result.content);
#     } else {
#         io:println("Error parsing document: ", result.message());
#     }
# }
# ```
public isolated function parseDocument(string filePath, typedesc<DocumentInfo> t = <>) returns t|error = @java:Method {
    'class: "io.xlibb.docreader.DocReader"
} external;

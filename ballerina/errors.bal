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

# Represents any error related to the DocReader module.
# This is the base error type for all document reading related errors.
public type Error distinct error;

# Represents an error that occurred during document reading operations.
# This error is thrown when the document reading process fails due to various reasons
# such as unsupported file formats, corrupted files, or internal reading issues.
public type DocReaderError distinct Error;

# Represents an error that occurred when a file is not found or inaccessible.
# This error is thrown when the specified file path does not exist, is not readable,
# or when there are permission issues accessing the file.
public type FileNotFoundError distinct Error;

# Represents an error that occurred due to unsupported file format.
# This error is thrown when the document format is not supported by the parser
# or when the file format detection fails.
public type UnsupportedFormatError distinct Error;

# Represents an error that occurred due to invalid configuration.
# This error is thrown when the parsing configuration contains invalid parameters
# or when required configuration is missing.
public type InvalidConfigError distinct Error;

# Represents an error that occurred during metadata extraction.
# This error is thrown when metadata extraction fails, but content reading might still succeed.
public type MetadataExtractionError distinct Error;

# Represents an error that occurred when the file size exceeds limits.
# This error is thrown when the document is too large to process or when
# content extraction exceeds specified limits.
public type FileSizeExceededError distinct Error;

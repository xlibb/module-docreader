/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.xlibb.docreader;

import io.ballerina.runtime.api.creators.ErrorCreator;
import io.ballerina.runtime.api.creators.ValueCreator;
import io.ballerina.runtime.api.types.RecordType;
import io.ballerina.runtime.api.utils.StringUtils;
import io.ballerina.runtime.api.values.BError;
import io.ballerina.runtime.api.values.BMap;
import io.ballerina.runtime.api.values.BString;
import io.ballerina.runtime.api.values.BTypedesc;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

/**
 * Document reader implementation using Apache Tika for extracting document information.
 *
 * This class provides functionality to read various document formats and extract
 * their content, type, and metadata using Apache Tika's AutoDetectParser.
 */
public class DocReader {

    private static final Tika tika = new Tika();

    /**
     * Parses a document file and extracts its type, extension, and content.
     *
     * @param filePath the path to the document file as BString
     * @param typed the type descriptor for the return type
     * @return BMap containing the parsed DocumentInfo or BError on failure
     */
    public static Object parseDocument(BString filePath, BTypedesc typed) {
        try {
            String filePathStr = filePath.getValue();
            File file = new File(filePathStr);
            
            if (!file.exists()) {
                return createError("File does not exist: " + filePathStr);
            }
            
            if (!file.isFile()) {
                return createError("Path is not a file: " + filePathStr);
            }

            // Extract file extension
            String extension = extractFileExtension(file);

            // Detect MIME type and extract content
            String mimeType = tika.detect(file);
            String content = extractContent(file);

            // Create and return Ballerina DocumentInfo record
            return createDocumentInfo(mimeType, extension, content, typed);
            
        } catch (IOException | TikaException | SAXException e) {
            return createError("Error parsing document: " + e.getMessage());
        } catch (Exception e) {
            return createError("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Extracts the file extension from a file.
     *
     * @param file the file to extract extension from
     * @return the file extension in lowercase, or empty string if no extension
     */
    private static String extractFileExtension(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        Path fileNamePath = path.getFileName();
        
        if (fileNamePath != null) {
            String fileName = fileNamePath.toString();
            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                return fileName.substring(lastDotIndex + 1).toLowerCase(Locale.ENGLISH);
            }
        }
        return "";
    }

    /**
     * Extracts content from a document file using Apache Tika's AutoDetectParser.
     *
     * @param file the file to extract content from
     * @return the extracted text content
     * @throws IOException if an I/O error occurs
     * @throws TikaException if a parsing error occurs
     * @throws SAXException if an XML parsing error occurs
     */
    private static String extractContent(File file) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler(-1);  // No content length limit
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        // Configure PDF parsing
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(false);
        pdfConfig.setExtractUniqueInlineImagesOnly(true);
        context.set(PDFParserConfig.class, pdfConfig);

        try (FileInputStream inputStream = new FileInputStream(file)) {
            parser.parse(inputStream, handler, metadata, context);
            return handler.toString().trim();
        }
    }

    /**
     * Creates a Ballerina DocumentInfo record from the extracted information.
     *
     * @param docType the MIME type of the document
     * @param extension the file extension
     * @param content the extracted content
     * @param typed the type descriptor for the return type
     * @return BMap representing the DocumentInfo record
     */
    private static BMap<BString, Object> createDocumentInfo(String docType, String extension, 
            String content, BTypedesc typed) {
        DocumentInfo docInfo = new DocumentInfo(
            docType != null ? docType : "application/octet-stream", 
            extension, 
            content
        );

        RecordType resultRecordType = (RecordType) typed.getDescribingType();
        BMap<BString, Object> documentInfo = ValueCreator.createRecordValue(resultRecordType);
        documentInfo.put(StringUtils.fromString("docType"), StringUtils.fromString(docInfo.docType()));
        documentInfo.put(StringUtils.fromString("extension"), StringUtils.fromString(docInfo.extension()));
        documentInfo.put(StringUtils.fromString("content"), StringUtils.fromString(docInfo.content()));
        
        return documentInfo;
    }

    /**
     * Creates a Ballerina error with the specified message.
     *
     * @param message the error message
     * @return BError representing the DocReaderError
     */
    private static BError createError(String message) {
        return ErrorCreator.createError(
            StringUtils.fromString("DocReaderError"), 
            StringUtils.fromString(message)
        );
    }
}

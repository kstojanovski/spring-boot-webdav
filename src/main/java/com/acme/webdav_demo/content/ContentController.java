package com.acme.webdav_demo.content;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/webdav/content")
class ContentController {

    private final ContentService contentService;

    ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/createFileWithContent")
    @ResponseStatus(HttpStatus.CREATED)
    void createFileWithContent(@RequestBody FilePartsDto fileParts) {
        contentService.putFileWithContent(fileParts.filename(), fileParts.fileContent());
    }

    @GetMapping("/getContentFromFile")
    String getContentFromFile(@RequestParam String filename) {
        return contentService.getFileContent(filename);
    }

}

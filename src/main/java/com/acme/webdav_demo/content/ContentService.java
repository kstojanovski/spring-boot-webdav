package com.acme.webdav_demo.content;

import com.acme.webdav_demo.sardine.SardineService;
import org.springframework.stereotype.Service;

@Service
class ContentService {

    private final SardineService sardineService;

    ContentService(SardineService sardineService) {
        this.sardineService = sardineService;
    }

    void putFileWithContent(String filename, String fileContent) {
        sardineService.putFileWithContent(filename, fileContent);
    }

    String getFileContent(String filename) {
        return sardineService.getFileContent(filename);
    }
}

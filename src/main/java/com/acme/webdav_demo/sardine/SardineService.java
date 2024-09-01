package com.acme.webdav_demo.sardine;

import com.github.sardine.impl.SardineException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class SardineService {
    private final SardineHandler sardineHandler;

    SardineService(SardineHandler sardineHandler) {
        this.sardineHandler = sardineHandler;
    }

    public void putFileWithContent(String fileName, String fileContent) {
        try {
            sardineHandler.sardinePutText(fileName, fileContent.getBytes(StandardCharsets.UTF_8));
        } catch (SardineException se) {
            HttpStatus httpStatus = HttpStatus.resolve(se.getStatusCode());
            throw new ResponseStatusException(httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public String getFileContent(String fileName) {
        try (InputStream inputStream = sardineHandler.sardineGet(fileName)) {
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
        } catch (SardineException se) {
            HttpStatus httpStatus = HttpStatus.resolve(se.getStatusCode());
            throw new ResponseStatusException(httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

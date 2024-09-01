package com.acme.webdav_demo.sardine;

import com.acme.webdav_demo.webdav.WebDavProperties;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
class SardineHandler {

    WebDavProperties webDavProperties;

    public SardineHandler(WebDavProperties webDavProperties) {
        this.webDavProperties = webDavProperties;
    }

    void sardinePutText(String filename, byte[] bytes) throws IOException {
        getSardineInstance().put(getUrl() + filename, bytes);
    }

    @SuppressWarnings("unused")
    void sardinePutText(String filename, InputStream inputStream) throws IOException {
        getSardineInstance().put(getUrl() + filename, inputStream);
    }

    InputStream sardineGet(String filename) throws IOException {
        return getSardineInstance().get(getUrl() + filename);
    }

    private String getUrl() {
        return String.format("http://%s:%d/", webDavProperties.getHost(), webDavProperties.getPort());
    }

    private Sardine getSardineInstance() {
        return SardineFactory.begin(webDavProperties.getUsername(), webDavProperties.getPassword());
    }

}

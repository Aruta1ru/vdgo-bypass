package com.vdgo.bypass.execvdgo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {

    void init();

    String store(MultipartFile file, int objId);

    Path load(String filename, int objId);

    Resource loadAsResource(String filename, int objId);

    void deleteAll();

    void deleteOne(String filename, int objId) throws IOException;

}
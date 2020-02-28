package com.vdgo.bypass.execvdgo.service;

import com.vdgo.bypass.execvdgo.config.StorageProperties;
import com.vdgo.bypass.execvdgo.exception.FileNotFoundException;
import com.vdgo.bypass.execvdgo.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public  StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Не удалось установить директорию хранилища", e);
        }
    }

    @Override
    public String store(MultipartFile file, int objId) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Не удалось разместить пустой файл " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Попытка разместить файл за пределами текущей директории "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {

                File f = new File(rootLocation+"/"+objId);
                if (!f.mkdir()) {
                    f.mkdir();
                }

                Path newLocation = f.toPath();

                Files.copy(inputStream, newLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);

            }
        }
        catch (IOException e) {
            throw new StorageException("Не удалось разместить файл " + filename, e);
        }

        return filename;
    }

    @Override
    public Path load(String filename, int objId) {

        Path newLocation = Paths.get(this.rootLocation+"/"+objId);
        return newLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename, int objId) {
        try {
            Path file = load(filename, objId);
            System.out.println(file);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new FileNotFoundException(
                        "Не удалось прочитать файл: " + filename);
            }
        }
        catch (MalformedURLException | FileNotFoundException e) {
            throw new FileNotFoundException("Не удалось прочитать файл: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void deleteOne(String filename, int objId)  {
        try {
            Path fileToDeletePath = load(filename, objId);
            Files.delete(fileToDeletePath);
        }
        catch(FileNotFoundException | IOException e) {
            throw new FileNotFoundException("Файла " + filename + " не обнаружено", e);
        }

    }
}

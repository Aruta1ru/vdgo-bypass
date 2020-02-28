package com.vdgo.bypass.execvdgo.controller;

import com.vdgo.bypass.execvdgo.domain.FileStorage;
import com.vdgo.bypass.execvdgo.repo.FileStorageRepo;
import com.vdgo.bypass.execvdgo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("files")
public class FileController {

    private StorageService storageService;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private FileStorageRepo fileStorageRepo;

    public FileController(StorageService storageService, JdbcTemplate jdbcTemplate) {
        this.storageService = storageService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/obj-download/{fileId:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) {

        //TODO: Add NullPointerException Check
        FileStorage fileStorage = fileStorageRepo.findById(fileId);

        Resource resource = storageService
                .loadAsResource(fileStorage.getName(), fileStorage.getAddress().getId());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/obj-upload/{objId:.+}")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int objId) {

        String name = storageService.store(file, objId);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(x -> {
            PreparedStatement preparedStatement = x.prepareStatement("INSERT INTO vdg_obj_files (id_obj, name, size) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, objId);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3, file.getSize());
            return preparedStatement;
        }, keyHolder);

        return "Загружен файл " + name;
    }

    @PostMapping("/obj-upload-multiple/{objId:.+}")
    @ResponseBody
    public List<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable int objId) {
        return Arrays.stream(files)
                .map(file -> uploadFile(file, objId))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/obj-delete/{fileId:.+}")
    @ResponseBody
    public String deleteFile(@PathVariable int fileId) throws IOException {

        //TODO: Add NullPointerException Check
        FileStorage fileStorage = fileStorageRepo.findById(fileId);

        storageService.deleteOne(fileStorage.getName(), fileStorage.getAddress().getId());
        fileStorageRepo.deleteById(fileId);

        return "Удалён файл " + fileStorage.getName();
    }
}

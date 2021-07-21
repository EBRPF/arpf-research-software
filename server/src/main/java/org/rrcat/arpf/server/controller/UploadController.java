package org.rrcat.arpf.server.controller;

import org.rrcat.arpf.server.entity.UploadedImage;
import org.rrcat.arpf.server.repository.UploadedImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/image")
public final class UploadController {
    private final UploadedImageRepository imageRepository;

    @Autowired
    public UploadController(final UploadedImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping("/upload/{dir}")
    public @ResponseBody UploadedImage uploadImage(@RequestPart("file") MultipartFile file, @PathVariable String dir) throws IOException {
        final Path filePath = Paths.get(".", "upload", dir, UUID.randomUUID() + "_" + file.getOriginalFilename());
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
        }
        Files.write(filePath, file.getBytes());
        final UploadedImage image = new UploadedImage();
        image.setName(filePath.getFileName().toString());
        image.setTag(dir);
        return imageRepository.save(image);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(500000000);
        return multipartResolver;
    }
}

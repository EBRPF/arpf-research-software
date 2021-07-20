package org.rrcat.arpf.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/api/v1/image")
public final class UploadController {
    @PostMapping("/upload/{dir}")
    public void uploadImage(@RequestPart("file") MultipartFile file, @PathVariable String dir) throws IOException {
        Files.write(new File("./test/" + file.getName()).toPath(), file.getBytes());
    }
}

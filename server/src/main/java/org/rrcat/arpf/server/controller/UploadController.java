package org.rrcat.arpf.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/image")
public final class UploadController {
    @PostMapping("/upload/{dir}")
    public void uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String dir) {
        
    }
}

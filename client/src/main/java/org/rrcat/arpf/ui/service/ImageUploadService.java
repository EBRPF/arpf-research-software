package org.rrcat.arpf.ui.service;

import org.dae.arpf.dto.UploadedImageDTO;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ImageUploadService {
    UploadedImageDTO upload(final File file);
}

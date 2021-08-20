package org.rrcat.arpf.ui.upload;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.inject.Inject;
import java.io.File;
import java.util.function.Supplier;

public final class UploadFileSupplier implements Supplier<File> {
    private final Window window;

    @Inject
    public UploadFileSupplier(final Window window) {
        this.window = window;
    }

    @Override
    public File get() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Image");
        return fileChooser.showOpenDialog(window);
    }
}

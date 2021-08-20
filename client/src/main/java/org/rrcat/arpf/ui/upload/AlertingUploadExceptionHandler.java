package org.rrcat.arpf.ui.upload;

import javafx.scene.control.Alert;

import javax.inject.Inject;
import javax.swing.text.html.ImageView;
import java.util.function.Consumer;

public class AlertingUploadExceptionHandler implements Consumer<Exception> {

    @Inject
    public AlertingUploadExceptionHandler() {
    }

    @Override
    public void accept(final Exception exception) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Upload File");
        alert.setHeaderText("Failed to upload file");
        alert.setContentText("Upload failed (" + exception.getMessage() + "). Kindly try again/contact system administrator");
        alert.show();
    }
}

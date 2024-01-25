package org.example.data;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class FileSelectorController {
    static FileChooser fileChooser = new FileChooser();
    public static Optional<File> openFolder(TextField filePathTextField){
        fileChooser.setTitle("Select File");

        String currentWorkingDirectory = System.getProperty("user.dir");
        File initialDirectory = new File(currentWorkingDirectory, "Config");
        String absolutePath = initialDirectory.getAbsolutePath();

        fileChooser.setInitialDirectory(new File(absolutePath));

        Optional<File> selectedFile = Optional.ofNullable(fileChooser.showOpenDialog(new Stage()));

        if (selectedFile.isPresent()) {
            filePathTextField.setText(selectedFile.orElseThrow().getAbsolutePath());
        }
        return selectedFile;
    }
}

package com.treasure_hunt.application.service.utils;

import com.treasure_hunt.application.exception.ImageException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

public class ImageService {
    private static final int MAX_FILE_SIZE = 2 * 1024 * 1024; // 2MB
    private static final int MAX_DEFAULT_WIDTH = 500;
    private static final int MAX_PROFILE_WIDTH = 150;
    private static final String UPLOADS_ROOT_DIR = System.getProperty("user.dir") + "/uploads";

    public String processImage(MultipartFile image, Map<String, Object> infos) throws IOException {
        String type = image.getContentType();
        if (type == null
                || (!type.equals("image/jpg")
                && !type.equals("image/jpeg")
                && !type.equals("image/png"))) {
            throw new ImageException("Only .jpg or .png allowed.");
        }

        if (image.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Max size 2MB.");
        }

        BufferedImage original = ImageIO.read(image.getInputStream());
        if (original == null) {
            throw new IllegalArgumentException("Invalid image file.");
        }

        int width = MAX_DEFAULT_WIDTH;

        if (infos.get("type") == "profile") {
            width = Math.min(original.getWidth(), MAX_PROFILE_WIDTH);
        }
        int height = (int) ((double) original.getHeight() / original.getWidth() * width);

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resized.createGraphics();
        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();

        String fileName = infos.get("ownerId").toString()
                + "_" + infos.get("type").toString()
                + "_" + UUID.randomUUID() + ".jpg";

        String userFolderName = "user_" + infos.get("userId").toString();
        Path userFolder = Paths.get(UPLOADS_ROOT_DIR, userFolderName);
        Files.createDirectories(userFolder);

        Path output = userFolder.resolve(fileName);

        ImageIO.write(resized, "jpg", output.toFile());

        return  "/uploads/" +userFolderName + "/" +fileName;
    }

    public void deleteImage(String imagePath) {
        if (imagePath == null || imagePath.isBlank())
            throw new ImageException("Image path is null.");

        try {
            Path path = Paths.get(imagePath);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image: " + imagePath, e);
        }
    }
}

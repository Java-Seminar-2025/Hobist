package gio.hobist.Controller;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DbFileTransferController {

    @Value("${file.upload-dir:C:/Users/Marko/Desktop/Java/Hobist/uploads}")
    private String uploadDir;

    @GetMapping("/media/{userId}/{imagePath}")
    public ResponseEntity<Resource> GetImage(@PathVariable String imagePath, @PathVariable UUID userId) {
        try {
            Path path;
            if ("noImage.jpg".equals(imagePath) || imagePath == null) {
                path = Paths.get(uploadDir).resolve("defaultImage.jpg");
            } else {
                path = Paths.get(uploadDir).resolve(userId.toString()).resolve(imagePath);
                if (!Files.exists(path)) {
                    path = Paths.get(uploadDir).resolve("defaultImage.jpg");
                }
            }

            if (Files.exists(path)) {
                Resource resource = new FileSystemResource(path.toFile());
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                ClassPathResource fallback = new ClassPathResource("/static/images/defaultImage.jpg");
                if (fallback.exists()) {
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(fallback);
                } else {
                    return ResponseEntity.notFound().build();
                }
            }

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
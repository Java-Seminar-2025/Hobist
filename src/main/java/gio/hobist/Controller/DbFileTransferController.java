package gio.hobist.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class DbFileTransferController {

//    @Value("${file.upload-dir}")//M.G: @Value doesn't work so i will hardcode it for now.
    private final String uploadDir="uploads";

    @GetMapping("/media/{imagePath}")//M.G: thymeleaf can access files only with url path so we put our local files first on site
    public ResponseEntity<Resource> GetImage(@PathVariable String imagePath) throws FileNotFoundException {

        var path= Paths.get(uploadDir).resolve(imagePath);
        if (!Files.exists(path)){
            throw new FileNotFoundException("Error: File not found");
        }
        Resource resource = new FileSystemResource(path.toFile());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }


}

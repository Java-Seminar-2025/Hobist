package gio.hobist.RestController;

import gio.hobist.Service.SimpleS3Service;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/files")
public class SimpleFileController {

    @Autowired
    private SimpleS3Service s3Service;

//    @PostMapping("/{userId}/upload")
//    public String uploadFile(@RequestParam("userId") UUID userId,@RequestParam("file") MultipartFile file) {
//        try {
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename()+userId.toString();
//            String fileUrl = s3Service.uploadFile(fileName, file);
//            return "File uploaded: " + fileUrl;
//        } catch (Exception e) {
//            return "Upload failed: " + e.getMessage();
//        }
//    }

    @GetMapping("/media/{userId}/{imagePath}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String imagePath, @PathVariable UUID userId) {
        try {
            byte[] data = s3Service.downloadFile(imagePath);

            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + imagePath + userId.toString())
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .contentLength(data.length)
                    .body((Resource) resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        try {
            s3Service.deleteFile(fileName);
            return "File deleted: " + fileName;
        } catch (Exception e) {
            return "Delete failed: ";
        }
    }
}
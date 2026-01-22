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

    public String GetDefauldImage() throws FileNotFoundException {

        var dbFileTransfer = new DbFileTransferController();
        String imageFileName; //not var because of try catch

        try {
            var image = dbFileTransfer.GetImage("defaultImage.jpg");//M.G: defaultImage.jpg is template for now
            imageFileName=image.getBody().getFilename();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            imageFileName=null;
        }

        return imageFileName; //M.G: this var was necessary for lambda expression according to the compiler error. Error: "Variable used in lambda expression should be final or effectively final"

    }

}

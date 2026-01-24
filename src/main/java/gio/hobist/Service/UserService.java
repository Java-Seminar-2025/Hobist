package gio.hobist.Service;

import gio.hobist.Dto.UserDto;
import gio.hobist.Repository.UserRepository;
import gio.hobist.Controller.DbFileTransferController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUser(UUID userId){
       var user= userRepository.findByid(userId);

        DbFileTransferController dbFileTransfer = new DbFileTransferController();
        String imageFileName; //not var because of try catch

        try {
            var image = dbFileTransfer.GetImage("defaultImage.jpg");//M.G: defaultImage.jpg is template for now
             imageFileName=image.getBody().getFilename();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
             imageFileName=null;
        }

       return new UserDto(user.getId(),
               user.getName(),
                user.getSurname(),
                null,
                user.getEmail(),
               imageFileName
               );
    }

    public List<UserDto> searchByQuery(String query) {
        var users = userRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(query, query);

        var dbFileTransfer = new DbFileTransferController();
        String imageFileName;

        try {
            var image = dbFileTransfer.GetImage("defaultImage.jpg");
            imageFileName = image.getBody().getFilename();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            imageFileName = null;
        }

        var finalImageFileName = imageFileName;

        return users.stream().map(user -> new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                null,
                user.getEmail(),
                finalImageFileName
        )).toList();
    }

    public List<UserDto> searchByName(String name, String surname){
        var users= userRepository.findByNameAndSurname(name,surname);

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

        var finalImageFileName = imageFileName; //M.G: this var was necessary for lambda expression according to the compiler error. Error: "Variable used in lambda expression should be final or effectively final"

        return users.stream().map(user-> new UserDto(user.getId(),
               user.getName(),
               user.getSurname(),
               null,
               user.getEmail(),
                finalImageFileName
        )).toList();

    }
}

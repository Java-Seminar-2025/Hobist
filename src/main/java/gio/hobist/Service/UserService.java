package gio.hobist.Service;

import gio.hobist.Dto.CountryCityDto;
import gio.hobist.Dto.UserDto;
import gio.hobist.Entity.Post;
import gio.hobist.Repository.FriendshipRepository;
import gio.hobist.Repository.PostRepository;
import gio.hobist.Repository.UserRepository;
import gio.hobist.Controller.DbFileTransferController;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FriendshipRepository friendshipRepository;

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
               imageFileName,
               Try.of(()-> new CountryCityDto(user.getCountry())).recover(NullPointerException.class,e->null).get(),
               Try.of(()-> new CountryCityDto(user.getCity())).recover(NullPointerException.class,e->null).get(),
               user.getUserPageDescription(),
               getNumberOfPosts(user.getId()),
               getNumberOfFriends(user.getId())
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
                finalImageFileName,
                Try.of(()-> new CountryCityDto(user.getCountry())).recover(NullPointerException.class,e->null).get(),
                Try.of(()-> new CountryCityDto(user.getCity())).recover(NullPointerException.class,e->null).get(),
                user.getUserPageDescription(),
                getNumberOfPosts(user.getId()),
                getNumberOfFriends(user.getId())
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
                finalImageFileName,
                Try.of(()-> new CountryCityDto(user.getCountry())).recover(NullPointerException.class,e->null).get(),
                Try.of(()-> new CountryCityDto(user.getCity())).recover(NullPointerException.class,e->null).get(),
                user.getUserPageDescription(),
                getNumberOfPosts(user.getId()),
                getNumberOfFriends(user.getId())
        )).toList();

    }

   public int getNumberOfPosts(UUID userId){
       var posts=Try.of(()->postRepository.findAllByIdUser(userId)).getOrElse(ArrayList::new);
       return posts.size();
   }

   public int getNumberOfFriends(UUID userId){
       var friends=Try.of(()->friendshipRepository.findByUser1Id(userId)).getOrElse(ArrayList::new);
       return friends.size();
   }

}

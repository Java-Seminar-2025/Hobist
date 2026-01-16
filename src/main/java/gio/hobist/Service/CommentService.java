package gio.hobist.Service;

import gio.hobist.Dto.CommentDto;
import gio.hobist.Entity.Comment;
import gio.hobist.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {//M.G: table is wrong in database, until fixed we cant work with commen
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    public CommentDto getCurrentComment(UUID commentId) {
//        var comment = commentRepository.findById(commentId).orElse(null);
//
//        if (comment == null) {
//            return null;
//        }
//
//        return new CommentDto(
//            comment.getId(),
//            comment.getPost().getId(),
//            comment.getUserId(),
//            comment.getMessage(),
//            comment.getLikeNumber()
//        );
//    }
//
//    public CommentDto updateComment(UUID commentId, CommentDto commentDto) {
//        var comment = commentRepository.findById(commentId).orElse(null);
//
//        if (comment == null) {
//            return null;
//        }
//
//        comment.setMessage(commentDto.getMessage());
//        comment.setLikeNumber(commentDto.getLikeNumber());
//
//        commentRepository.save(comment);
//
//        return new CommentDto(
//            comment.getId(),
//            comment.getPost().getId(),
//            comment.getUserId(),
//            comment.getMessage(),
//            comment.getLikeNumber()
//        );
//    }
//
//    public CommentDto createComment(CommentDto commentDto) {
//        var comment = new Comment();
//        comment.setUserId(commentDto.getUserId());
//        comment.setMessage(commentDto.getMessage());
//        comment.setLikeNumber(commentDto.getLikeNumber());
//
//        commentRepository.save(comment);
//
//        return new CommentDto(
//            comment.getId(),
//            comment.getPost().getId(),
//            comment.getUserId(),
//            comment.getMessage(),
//            comment.getLikeNumber()
//        );
//    }
//
//    public void deleteComment(UUID commentId) {
//        commentRepository.deleteById(commentId);
//    }
}
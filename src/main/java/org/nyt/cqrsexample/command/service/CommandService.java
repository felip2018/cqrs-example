package org.nyt.cqrsexample.command.service;

import org.nyt.cqrsexample.command.model.CommentCommand;
import org.nyt.cqrsexample.command.model.PostCommand;
import org.nyt.cqrsexample.command.model.ReactionCommand;
import org.nyt.cqrsexample.command.repository.CommentCommandRepository;
import org.nyt.cqrsexample.command.repository.PostCommandRepository;
import org.nyt.cqrsexample.command.repository.ReactionCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

  @Autowired
  PostCommandRepository postRepository;

  @Autowired
  CommentCommandRepository commentRepository;

  @Autowired
  ReactionCommandRepository reactionRepository;

  public PostCommand addPost(PostCommand post) {
    post.setIsSync(0);
    return postRepository.save(post);
  }

  public CommentCommand addComment(Long postId, CommentCommand comment) {
    comment.setPostId(postId);
    comment.setIsSync(0);
    return commentRepository.save(comment);
  }

  public ReactionCommand addReaction(Long postId, Long commentId, ReactionCommand reaction) {
    CommentCommand comment = new CommentCommand();
    comment.setId(commentId);
    reaction.setComment(comment);
    reaction.setIsSync(0);
    return reactionRepository.save(reaction);
  }
}

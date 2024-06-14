package org.nyt.cqrsexample.sync.service;

import org.nyt.cqrsexample.command.model.CommentCommand;
import org.nyt.cqrsexample.command.model.PostCommand;
import org.nyt.cqrsexample.command.model.ReactionCommand;
import org.nyt.cqrsexample.command.repository.CommentCommandRepository;
import org.nyt.cqrsexample.command.repository.PostCommandRepository;
import org.nyt.cqrsexample.command.repository.ReactionCommandRepository;
import org.nyt.cqrsexample.query.model.Comment;
import org.nyt.cqrsexample.query.model.Post;
import org.nyt.cqrsexample.query.model.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SyncService {

  Date lastSyncDate = new Date();

  @Autowired
  PostCommandRepository postCommandRepository;

  @Autowired
  CommentCommandRepository commentCommandRepository;

  @Autowired
  ReactionCommandRepository reactionCommandRepository;

  @Autowired
  private MongoOperations mongoOps;

  public void sync() {
    Date newSyncDate = new Date();
    updatePosts();
    updateComments();
    updateReactions();
    lastSyncDate = newSyncDate;
  }

  private void updatePosts() {
    List<PostCommand> modifiedPosts = postCommandRepository.findAllByIsSync(0);

    for(PostCommand post: modifiedPosts) {
      Query query = new Query(Criteria.where("id").is(post.getId().toString()));
      Update update = new Update();
      update.set("content", post.getContent());
      mongoOps.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(true), Post.class);
      post.setIsSync(1);
    }
    postCommandRepository.saveAll(modifiedPosts);
  }

  private void updateComments() {
    List<CommentCommand> modifiedComments = commentCommandRepository.findAllByIsSync(0);

    for(CommentCommand comment : modifiedComments) {
      Query query = new Query(Criteria.where("id").is(comment.getPostId().toString()));
      Update update = new Update();
      Comment mongoComment = new Comment();
      mongoComment.setId(comment.getId().toString());
      mongoComment.setContent(comment.getContent());
      update.addToSet("comments", mongoComment);
      mongoOps.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(true), Post.class);
      comment.setIsSync(1);
    }
    commentCommandRepository.saveAll(modifiedComments);
  }

  private void updateReactions() {
    List<ReactionCommand> modifiedReactions = reactionCommandRepository.findAllByIsSync(0);

    for(ReactionCommand reaction : modifiedReactions) {
      Query query = new Query(new Criteria().andOperator(
          Criteria.where("id").is(reaction.getComment().getPostId().toString()),
          Criteria.where("comments").elemMatch(Criteria.where("id").is(reaction.getCommentId().toString()))
      ));
      Reaction mongoReaction = new Reaction();
      mongoReaction.setId(reaction.getId().toString());
      mongoReaction.setEmoji(reaction.getEmoji());
      Update update = new Update().addToSet("comments.$.reactions", mongoReaction);
      mongoOps.findAndModify(query, update, FindAndModifyOptions.options().upsert(true), Post.class);
      reaction.setIsSync(1);
      reactionCommandRepository.save(reaction);
    }
  }
}

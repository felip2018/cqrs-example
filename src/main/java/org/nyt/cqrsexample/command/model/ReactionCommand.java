package org.nyt.cqrsexample.command.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment_reaction")
public class ReactionCommand {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String emoji;
  @Column(name = "comment_id", insertable = false, updatable = false)
  private Long commentId;
  @ManyToOne
  private CommentCommand comment;
  @Column(name = "is_sync")
  private Integer isSync;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmoji() {
    return emoji;
  }

  public void setEmoji(String emoji) {
    this.emoji = emoji;
  }

  public Long getCommentId() {
    return commentId;
  }

  public void setCommentId(Long commentId) {
    this.commentId = commentId;
  }

  public Integer getIsSync() {
    return isSync;
  }

  public void setIsSync(Integer isSync) {
    this.isSync = isSync;
  }

  public CommentCommand getComment() {
    return comment;
  }

  public void setComment(
      CommentCommand comment) {
    this.comment = comment;
  }
}

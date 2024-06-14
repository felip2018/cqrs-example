package org.nyt.cqrsexample.command.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class CommentCommand {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  @Column(name = "post_id")
  private Long postId;
  @Column(name = "is_sync")
  private Integer isSync;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Integer getIsSync() {
    return isSync;
  }

  public void setIsSync(Integer isSync) {
    this.isSync = isSync;
  }
}

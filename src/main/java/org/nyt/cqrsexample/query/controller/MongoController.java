package org.nyt.cqrsexample.query.controller;

import org.nyt.cqrsexample.query.model.Post;
import org.nyt.cqrsexample.query.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController {

  @Autowired
  private QueryService queryService;

  @GetMapping("/post/{id}")
  public Post getPost(@PathVariable String id) {
    return queryService.getPost(id);
  }
}

package org.nyt.cqrsexample.query.service;

import org.nyt.cqrsexample.query.model.Post;
import org.nyt.cqrsexample.query.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class QueryService {

  @Autowired
  PostRepository postRepository;

  public Post getPost(String id) {
    return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}

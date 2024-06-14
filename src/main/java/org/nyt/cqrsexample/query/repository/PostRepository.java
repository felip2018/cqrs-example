package org.nyt.cqrsexample.query.repository;


import org.nyt.cqrsexample.query.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}

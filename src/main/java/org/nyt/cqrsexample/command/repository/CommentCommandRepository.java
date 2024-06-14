package org.nyt.cqrsexample.command.repository;

import org.nyt.cqrsexample.command.model.CommentCommand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentCommandRepository extends CrudRepository<CommentCommand, Long> {

  List<CommentCommand> findAllByIsSync(Integer isSync);
}

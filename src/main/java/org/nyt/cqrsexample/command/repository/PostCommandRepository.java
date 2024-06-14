package org.nyt.cqrsexample.command.repository;

import org.nyt.cqrsexample.command.model.PostCommand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommandRepository extends CrudRepository<PostCommand, Long> {

  List<PostCommand> findAllByIsSync(Integer isSync);
}

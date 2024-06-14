package org.nyt.cqrsexample.command.repository;

import org.nyt.cqrsexample.command.model.ReactionCommand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionCommandRepository extends CrudRepository<ReactionCommand, Long> {

  List<ReactionCommand> findAllByIsSync(Integer isSync);
}

package org.nyt.cqrsexample.command.controller;

import org.nyt.cqrsexample.command.model.CommentCommand;
import org.nyt.cqrsexample.command.model.PostCommand;
import org.nyt.cqrsexample.command.model.ReactionCommand;
import org.nyt.cqrsexample.command.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    @Autowired
    private CommandService commandService;

    @PostMapping("/post")
    public PostCommand addPost(@RequestBody PostCommand post) {
        return commandService.addPost(post);
    }

    @PostMapping("/post/{postId}/comment")
    public CommentCommand addComment(@PathVariable Long postId, @RequestBody CommentCommand comment) {
        return commandService.addComment(postId, comment);
    }

    @PostMapping("/post/{postId}/comment/{commentId}/reaction")
    public ReactionCommand addReaction(@PathVariable Long postId, @PathVariable Long commentId,
                                       @RequestBody ReactionCommand reaction) {
        return commandService.addReaction(postId, commentId, reaction);
    }
}

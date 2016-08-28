package controller;

import entities.Forum;
import services.ForumService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author johan
 * @since 2016-08-24.
 */
@Named
@RequestScoped
public class ForumController {
    @Inject
    private ForumService forumService;

    public List<Forum> getForumList() {
        return forumService.getAll();
    }
}

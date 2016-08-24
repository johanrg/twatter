package controller;

import services.ForumService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author johan
 * @since 2016-08-24.
 */
@Named
@RequestScoped
public class forumController {
    @Inject
    private ForumService forumService;

    public ForumService getForumService() {
        return forumService;
    }
}

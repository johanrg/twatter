package controller;

import entity.ForumPost;
import entity.ForumThread;
import service.ForumPostService;
import service.ForumThreadService;
import util.Node;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @author johan
 * @since 2016-08-27.
 */
@Named
@RequestScoped
public class ShowThreadController {
    @Inject
    private ForumThreadService forumThreadService;
    @Inject
    private ForumPostService forumPostService;

    private int threadId;
    private ForumThread forumThread;

    public String getTopic() {
        if (forumThread != null) {
            return forumThread.getTopic();
        }
        return null;
    }

    public List<Node<ForumPost>> getTreeNodes() {
        ForumThread forumThread = forumThreadService.find(threadId);
        if (forumThread == null) return null;

        Node<ForumPost> root = new Node(null, forumThread.getForumPosts().get(0));
        createNodeTree(root, root.getValue().getReplies());
        return flatList(new ArrayList<Node<ForumPost>>(), root);
    }

    private void createNodeTree(Node<ForumPost> root, List<ForumPost> replies) {
        for (ForumPost reply : replies) {
            Node<ForumPost> node = new Node(root, reply);
            root.getKids().add(node);
            if (node.getValue().getReplies().size() > 0) {
                createNodeTree(node, node.getValue().getReplies());
            }
        }
    }

    private List<Node<ForumPost>> flatList(List<Node<ForumPost>> list, Node<ForumPost> node) {
        list.add(node);
        for (Node<ForumPost> kid : node.getKids()) {
            flatList(list, kid);
        }
        return list;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
        if (threadId > 0) {
            forumThread = forumThreadService.find(threadId);
        }
    }
}

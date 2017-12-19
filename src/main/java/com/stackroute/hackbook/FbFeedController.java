package com.stackroute.hackbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FbFeedController {

    @Autowired
    Facebook facebook;
    @Autowired
    ConnectionRepository connectionRepository;

    @GetMapping("/")
    public String getFeeds(Model model)
    {
        if(connectionRepository.findPrimaryConnection(Facebook.class)==null){
            return "redirect:/connect/facebook";
        }

       PagedList<Post> feed=facebook.feedOperations().getFeed();
       model.addAttribute("feed",feed);
        return "feed";

    }



}

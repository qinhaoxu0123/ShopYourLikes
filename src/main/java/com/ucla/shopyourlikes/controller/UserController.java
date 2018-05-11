package com.ucla.shopyourlikes.controller;


import com.ucla.shopyourlikes.security.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ucla.shopyourlikes.payload.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser Object currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.toString());
        return userSummary;
    }
}

package com.egfavre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by user on 6/21/16.
 */
@Controller
public class RestaurantTrackerController {
    @Autowired
    UserRepository users;
    @Autowired
    RestaurantRepository restaurants;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "login";
        } else {
            return "home";
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) throws Exception {
        User user = users.findByName(username);
        if (user == null){
            user = new User(username, password);
            users.save(user);
        }
        else if (!user.password.equals(password)){
            throw new Exception("wrong password");
        }
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path = "logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "create-restaurant", method = RequestMethod.POST)
    public String create(String name, String location, int rating, String comment){
        Restaurant r = new Restaurant(name, location, rating, comment);
        restaurants.save(r);
        return "redirect:/";
    }
}

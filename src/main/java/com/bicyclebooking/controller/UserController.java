package com.bicyclebooking.controller;

import com.bicyclebooking.model.User;
import com.bicyclebooking.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public void userLogged(int userID, Model model){
        User user = userService.get(userID);
        model.addAttribute("user", user);
        model.addAttribute("userID", userID);
    }

//Đăng ký
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new User());
        return "register_page";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        User registeredUser = userService.registerUser(user.getFullName(), user.getEmail(),user.getPhone(), user.getPassword());
        return registeredUser == null? "redirect:/register?fail" : "redirect:/login";
    }

//Đăng nhập
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new User());
        return "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user){
        User authenticated = userService.authenticate(user.getPhone(), user.getPassword());

        if(authenticated != null){
            User user1 = userService.get(user.getPhone());
            return "redirect:/" + user1.getId() + "/homepage";
        }
        else{
            return "redirect:/login?fail";
        }
    }

    @GetMapping("/{userID}/homepage")
    public String getHomePage(@PathVariable("userID") int userID, Model model){
        userLogged(userID, model);
        return "homepage";
    }
//Trang cài đặt người dùng
    @GetMapping("/{userID}/expand")
    public String getExpandPage(@PathVariable("userID") int userID,Model model){
        userLogged(userID, model);
        return "expand";
    }
//Sửa thông tin người dùng
    @GetMapping("/{userID}/expand/update")
    public String showUpdateForm(@PathVariable("userID") int userID,Model model){
        userLogged(userID, model);
        return "updateForm";
    }

    @PostMapping("/{userID}/expand/update/save")
    public String updateUser(@PathVariable("userID") int userID,User user){
        User updatedUser = userService.save(user);
        if (updatedUser == null){
            return "redirect:/{userID}/expand/update?fail";
        }
        return "redirect:/{userID}/expand/update?success";
    }
//Thay đổi mật khẩu
    @GetMapping("/{userID}/expand/changePass")
    public String showChangePassForm(@PathVariable("userID") int userID, Model model){
        userLogged(userID, model);
        return "changePass";
    }

    @PostMapping("/{userID}/expand/changePass/save")
    public String changePass(@PathVariable("userID") int userID, HttpServletRequest request){
        String currentPass = userService.get(userID).getPassword();
        String oldPass = request.getParameter("oldPass") ;
        if (oldPass.equals(currentPass)){
            String newPass = request.getParameter("newPass") ;
            userService.changePassword(newPass, userID);
            return "redirect:/{userID}/expand/changePass?success";
        }
        return "redirect:/{userID}/expand/changePass?fail";
    }
//Xoá tài khoản
    @GetMapping("/{userID}/expand/delete")
    public String deleteUser(@PathVariable("userID") int userID){
        userService.delete(userID);
        return "redirect:/login";
    }
//
    @GetMapping("/{userID}/map")
    public String getMapPage(@PathVariable("userID") int userID, Model model){
        model.addAttribute("userID", userID);
        return "map";
    }

    @GetMapping("/{userID}/wallet")
    public String getWalletPage(@PathVariable("userID") int userID,Model model){
        userLogged(userID, model);
        return "wallet";
    }
}


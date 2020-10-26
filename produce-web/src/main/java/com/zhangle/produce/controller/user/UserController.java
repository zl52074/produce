package com.zhangle.produce.controller.user;


import com.zhangle.produce.common.util.UUIDUtils;
import com.zhangle.produce.dao.produce.ProduceDao;
import com.zhangle.produce.domain.produce.PriceTable;
import com.zhangle.produce.domain.produce.PriceTableCondition;
import com.zhangle.produce.domain.user.User;
import com.zhangle.produce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/21 14:01
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProduceDao produceDao;


    @RequestMapping("toLoginPage.do")
    public String  toLoginPage(){
        return "user/login";
    }

    @RequestMapping("toRegisterPage.do")
    public String  toRegisterPage(){
        return "user/register";
    }

    @RequestMapping("toUpdatePage.do")
    public ModelAndView toUpdatePage(HttpServletRequest request) throws SQLException {
        ModelAndView mv = new ModelAndView();
        HttpSession  session = request.getSession();
        if(session.getAttribute("user")!=null){
            User user = (User)session.getAttribute("user");
            mv.addObject("u", userService.findById(user.getId()));
            mv.setViewName("user/update");
        }else{
            mv.setViewName("user/update");
        }

        return mv;
    }


    @RequestMapping("code.do")
    public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 使用java图形界面技术绘制一张图片
        int charNum = 4;
        int width = 20 * 4;
        int height = 28;
        // 1. 创建一张内存图片
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 2.获得绘图对象
        Graphics graphics = bufferedImage.getGraphics();
        // 3、绘制背景颜色
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(0, 0, width, height);
        // 4、绘制图片边框
        graphics.setColor(Color.GRAY);
        graphics.drawRect(0, 0, width - 1, height - 1);
        // 5、输出验证码内容
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("宋体", Font.BOLD, 22));
        // 随机输出4个字符
        String s = "ABCDEFGHGKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        // session中要用到
        String msg = "";
        int x = 5;
        for (int i = 0; i < charNum; i++) {
            int index = random.nextInt(32);
            String content = String.valueOf(s.charAt(index));
            msg += content;
            graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            graphics.drawString(content, x, 22);
            x += 20;
        }
        // 6、绘制干扰线
        graphics.setColor(Color.GRAY);
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);

            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }
        request.getSession().setAttribute("sessionYzm", msg);
        // 释放资源
        graphics.dispose();
        // 图片输出 ImageIO
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }

    @RequestMapping("codeValidate.do")
    public void validateCode(@RequestParam(name = "code",required = true) String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sessionYzm = (String)request.getSession().getAttribute("sessionYzm");
        String f = "{\"invalid\":\"true\"}";
        String t = "{\"valid\":\"true\"}";
        if(!sessionYzm.equals(code.toUpperCase())){
            response.getWriter().print(f);
        }else{
            response.getWriter().print(t);
        }
    }


    @RequestMapping("/usernameValidate.do")
    public void usernameValidate(@RequestParam(name = "username",required = true) String username, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int count = userService.getCountByUsername(username);
        String f = "{\"invalid\":\"true\"}";
        String t = "{\"valid\":\"true\"}";
        if(count!=0){
            response.getWriter().print(f);
        }else{
            response.getWriter().print(t);
        }
    }

    @RequestMapping("/passwordValidate.do")
    public void passwordValidate(@RequestParam(name = "oldPassword",required = true) String oldPassword, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String f = "{\"invalid\":\"true\"}";
        String t = "{\"valid\":\"true\"}";
        if(oldPassword.equals(user.getPassword())){
            response.getWriter().print(t);
        }else{
            response.getWriter().print(f);
        }
    }

    @RequestMapping("save.do")
    public String save(User user,HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        user.setId(UUIDUtils.getUUID());
        userService.save(user);
        System.out.println(user);
        session.setAttribute("user", user);
        return "redirect:/priceTable.do";
    }

    @RequestMapping("login.do")
    public String login(User user,HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        User u = userService.findByUsernameAndPassword(user);
        if (u != null) {
            session.setAttribute("user", u);
            return "redirect:/priceTable.do";
        } else {
            request.setAttribute("msg", "账号或密码错误，请重新登陆");
            return "user/login";
        }
    }

    @ResponseBody
    @RequestMapping("logout.do")
    public String  logout(User user,HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "success";
    }

    @ResponseBody
    @RequestMapping("update.do")
    public String update(User user,HttpServletRequest request) throws SQLException {
        userService.update(user);
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "success";
    }

}

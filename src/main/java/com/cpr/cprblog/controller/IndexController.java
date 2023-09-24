package com.cpr.cprblog.controller;

import com.cpr.cprblog.common.MD5Util;
import com.cpr.cprblog.entity.Member;
import com.cpr.cprblog.entity.Product;
import com.cpr.cprblog.service.MemberService;
import com.cpr.cprblog.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    /* 主页以 及 登陆注册系统 */
    @RequestMapping ("/index")
    public String index(String product_name, String product_class, String minprice, String maxprice, Model model){
        List<String> product_classlist=productService.findclass();
        model.addAttribute("product_classes",product_classlist);
        //为搜索条件设置默认值，并检索条件是否合法
        Integer _minPrice=0; //默认从0到最大值
        Integer _maxPrice = Integer.MAX_VALUE;

        String reg = "^\\d+$"; //只能输入数字
        if(minprice!=null && !"".equals(minprice.trim()) && minprice.matches(reg)){
            _minPrice = Integer.parseInt(minprice);
        }
        if(maxprice!=null && !"".equals(maxprice.trim()) && maxprice.matches(reg)){
            //最高价格如果大于等于最低价格
            if(Integer.parseInt(maxprice) >= _minPrice){
                _maxPrice = Integer.parseInt(maxprice);
            }
        }

        List<Product> products;
        if(product_name!=null && !"".equals(product_name.trim()) && product_class!=null && !"".equals(product_class)) {
            products = productService.searchproduct(product_name, product_class, _minPrice, _maxPrice);
        }else if(product_name!=null && !"".equals(product_name.trim())){
            products = productService.searchproductbyfname(product_name,_minPrice, _maxPrice);
        }else if(product_class!=null && !"".equals(product_class.trim())){
            products = productService.searchproductbyfclass(product_class,_minPrice, _maxPrice);
        }else{
            products = productService.searchproduct(_minPrice, _maxPrice);
        }
        model.addAttribute("productlist",products);
        return "index";
    }

    @RequestMapping ("/index/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping ("/index/doLogin")
    @ResponseBody
    public String doLogin(String email, String password, HttpSession session){

        Member member=memberService.findByEmail(email);
        if(member!=null && member.getPassword().equals(MD5Util.md5(password))){
            session.setAttribute("member", member);
            return "登录成功";
        }else{
            return "登录失败";
        }
    }

    @RequestMapping("/index/toLogout")
    public String logout(HttpSession session){
        session.setAttribute("member", null);
        return "redirect:/index";
    }

    @RequestMapping ("/index/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/index/checkemail")
    @ResponseBody
    public String checkemail(String email){
        if(memberService.existsById(email)){
            return "该emall已被注册";
        }else{
            return "该emall可以使用";
        }
    }

    @RequestMapping("/index/doRegister")
    @ResponseBody
    public String doRegister(String email, String passw1, HttpSession session) {

        if(memberService.existsById(email)){
            return "该emall已被注册";
        }else {

            Member member1=new Member();
            member1.setEmail(email);
            member1.setPassword(MD5Util.md5(passw1));
            Member member2=memberService.save(member1);
            session.setAttribute("member", member2);
            return "注册成功！";
        }
    }

    /* 商店页面 */
    @RequestMapping("/store")
    public String store(Model model){
        model.addAttribute("productlist",productService.findAll());
        return "store";
    }

    @RequestMapping("/store/productdetail")
    public String productdetail(String product_id, Model model){
        Product product=productService.findById(product_id);
        model.addAttribute("product", product);
        return "productdetail";
    }
}

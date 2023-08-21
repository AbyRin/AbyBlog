package com.aby.abyblog.controller;

import com.aby.abyblog.entity.Member;
import com.aby.abyblog.service.MemberService;
import com.aby.abyblog.service.ProductService;
import com.aby.abyblog.common.MD5Util;
import com.aby.abyblog.entity.Product;
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
    public String index(String productname, String productclass, String minprice, String maxprice, Model model){
        List<String> productclasslist=productService.findclass();
        model.addAttribute("productclasses",productclasslist);
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
        if(productname!=null && !"".equals(productname.trim()) && productclass!=null && !"".equals(productclass)) {
            products = productService.searchproduct(productname, productclass, _minPrice, _maxPrice);
        }else if(productname!=null && !"".equals(productname.trim())){
            products = productService.searchproductbyfname(productname,_minPrice, _maxPrice);
        }else if(productclass!=null && !"".equals(productclass.trim())){
            products = productService.searchproductbyfclass(productclass,_minPrice, _maxPrice);
        }else{
            products = productService.searchproduct(_minPrice, _maxPrice);
        }
        model.addAttribute("productlist",products);
        return "index";
    }

    /* 跳转至: 登陆页面 */
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

    /* 登出 */
    @RequestMapping("/index/toLogout")
    public String logout(HttpSession session){
        session.setAttribute("member", null);
        return "redirect:/index";
    }

    /* 跳转至: 注册页面 */
    @RequestMapping ("/index/toRegister")
    public String toRegister(){
        return "register";
    }

    /* 注册页面: 检查E-mail是否可用 */
    @RequestMapping("/index/checkemail")
    @ResponseBody
    public String checkemail(String email){
        if(memberService.existsById(email)){
            return "该emall已被注册";
        }else{
            return "该emall可以使用";
        }
    }

    /* 注册功能 */
    @RequestMapping("/index/doRegister")
    @ResponseBody
    public String doRegister(String email, String passw1, HttpSession session) {

        if(memberService.existsById(email)){
            return "该E-mall已被注册";
        }else {

            Member member1=new Member();
            member1.setEmail(email);
            member1.setPassword(MD5Util.md5(passw1));
            Member member2=memberService.save(member1);
            session.setAttribute("member", member2);
            return "注册成功！";
        }
    }

    /* 跳转至: 商店页面 */
    @RequestMapping("/store")
    public String store(Model model){
        model.addAttribute("productlist",productService.findAll());
        return "store";
    }

    /* 跳转至: 商品详情 */
    @RequestMapping("/store/productdetail")
    public String productdetail(String productid, Model model){
        Product product=productService.findById(productid);
        model.addAttribute("product", product);
        return "productdetail";
    }
}

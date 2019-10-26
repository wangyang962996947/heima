package com.itheima.ssm.controller;import com.itheima.ssm.domain.Product;import com.itheima.ssm.service.IProductService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.servlet.ModelAndView;import javax.annotation.security.RolesAllowed;import java.util.List;/** * 商品Product * @auther wangyang * @date 2019/10/21 11:31 PM */@Controller@RequestMapping("/product")public class ProductController {    @Autowired    private IProductService productService;    /**     * 获取全部商品     * @return     * @throws Exception     */    @RequestMapping("/findAll.do")    public ModelAndView findAll() throws Exception {        ModelAndView mv = new ModelAndView();        List<Product> list = productService.findAll();        mv.addObject("productList",list);        mv.setViewName("product-list");        return mv;    }    /**     * 添加商品     * @param product     * @return     * @throws Exception     */    @RequestMapping("/save.do")    public String save(Product product) throws Exception {         productService.save(product);         return "redirect:findAll.do";    }}
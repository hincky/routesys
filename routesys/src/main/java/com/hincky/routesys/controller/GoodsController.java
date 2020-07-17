package com.hincky.routesys.controller;

import com.github.pagehelper.PageInfo;
import com.hincky.routesys.dao.GoodsDao;
import com.hincky.routesys.pojo.entity.Goods;
import com.hincky.routesys.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class GoodsController {
    @Resource
    GoodsDao goodsDao;
    @Resource
    GoodsService goodsService;

    /**
     * 开启分页
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody //自动返回json格式的数据
    @RequestMapping(value="/getGoodsPage",method= RequestMethod.GET)
    public Map<String, Object> getPage(//page、limit参数都是layui的table组件默认自动传递的，我们只需接收即可
                                        HttpServletRequest request,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit",defaultValue = "5") Integer limit){
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println("用户id为："+userId);
        Map<String, Object> map = new HashMap<>();
        List<Goods> list = goodsService.getData(page, limit);//执行分页查询的方法
        int count = goodsDao.findAll().size();
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        System.out.println("商品列表内容条数为：" + count);
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", count);
        map.put("data", pageInfo.getList());//最最最关键的代码，layui的table会自动获取并显示该数据集
        return map;
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @RequestMapping("/goodsAdd")
    public String GoodsAdd(Goods goods){
        int result = goodsDao.insert(goods);
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("result","添加成功");
        }else{
            map.put("result","添加失败");
        }
        return "redirect: /goodsAddPage";
    }
}

package cn.crabapples.spring.controller;

import cn.crabapples.spring.common.BaseController;
import cn.crabapples.spring.dto.ResponseDTO;
import cn.crabapples.spring.entity.OrderInfo;
import cn.crabapples.spring.entity.ShopInfo;
import cn.crabapples.spring.entity.ShopInfoOrder;
import cn.crabapples.spring.entity.ShopList;
import cn.crabapples.spring.service.ShopService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;


/**
 * TODO
 *
 * @author Mr.He
 * 2020/3/18 23:36
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Controller
@ResponseBody
@RequestMapping(value = "/api/shop")
@Api("用户管理")
public class ShopController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(ShopController.class);
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("/getMenuList")
    public ResponseDTO getMenuList() {
        logger.info("收到请求->获取菜单列表");
        List<ShopList> shopLists = shopService.findAllShopList();
        logger.info("获取菜单列表->菜单列表信息:[{}]", shopLists);
        return ResponseDTO.returnSuccess("操作成功", shopLists);
    }

    @RequestMapping("/getGoodsInfo/{id}")
    public ResponseDTO getGoodsInfo(@PathVariable String id) {
        logger.info("收到请求->获取菜单详情:[{}]", id);
        List<ShopInfo> shopInfos = shopService.findShopInfoByListId(id);
        logger.info("获取菜单详情->菜单详情信息:[{}]", shopInfos);
        return ResponseDTO.returnSuccess("操作成功", shopInfos);
    }

    @PostMapping("/submit")
    public ResponseDTO submit(@RequestBody List<ShopInfo> shopInfos) {
        logger.info("收到请求->确认订单:[{}]", shopInfos);
        OrderInfo orderInfo = shopService.saveShopInfoOrders(shopInfos);
        logger.info("下单成功->订单信息:[{}]",orderInfo);
        return ResponseDTO.returnSuccess("操作成功",orderInfo);
    }
//
//    @PostMapping("/delUser")
//    @ApiOperation(value = "删除用户", notes = "删除用户接口")
//    public ResponseDTO delUser(@RequestBody UserForm form){
//        super.validator(form, IsStatusChange.class);
//        logger.info("收到请求->删除用户:[{}]",form.getId());
//        userService.delUser(form.getId());
//        logger.info("用户删除完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }
//
//    @PostMapping("/unActiveUser")
//    @ApiOperation(value = "禁用用户", notes = "禁用用户接口")
//    public ResponseDTO unActiveUser(@RequestBody UserForm form){
//        super.validator(form, IsStatusChange.class);
//        logger.info("收到请求->禁用用户:[{}]",form.getId());
//        userService.unActiveUser(form.getId());
//        logger.info("用户禁用完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }
//
//    @PostMapping("/activeUser")
//    @ApiOperation(value = "激活用户", notes = "激活用户接口")
//    public ResponseDTO activeUser(@RequestBody UserForm form){
//        super.validator(form, IsStatusChange.class);
//        logger.info("收到请求->激活用户:[{}]",form.getId());
//        userService.activeUser(form.getId());
//        logger.info("用户激活完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }

}
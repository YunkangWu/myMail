package com.mall.controller.backend;

import com.mall.common.ServerResponse;
import com.mall.service.ICategoryService;
import com.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yunkang Wu
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 增加品类
     *
     * @param
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest request, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        //String loginToken = CookieUtil.readLoginToken(request);
        //if (loginToken == null) {
        //    return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        //}
        //String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        //User user = JsonUtil.string2Obj(userJsonStr, User.class);
        //if (user == null) {
        //    return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        //}
        ////校验是否是管理员
        //if (iUserService.checkAdminRole(user).isSuccess()) {
        //    return iCategoryService.addCategory(categoryName, parentId);
        //} else {
        //    return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        //}
        //注释原有的权限验证，全部通过拦截器验证是否登录以及权限
        return iCategoryService.addCategory(categoryName, parentId);
    }

    /**
     * 修改品类名字
     *
     * @param
     * @param categroyId
     * @param categoryName
     * @return
     */
    @RequestMapping("set_category.do")
    @ResponseBody
    public ServerResponse setCategroy(HttpServletRequest request, Integer categroyId, String categoryName) {
        //全部通过拦截器验证是否登录以及权限
        return iCategoryService.updateCategoryName(categroyId, categoryName);
    }

    /**
     * 查询子节点的category信息，并且不递归保持平级
     *
     * @param
     * @param categroyId
     * @return
     */
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpServletRequest request, @RequestParam(value = "categoryId", defaultValue = "0") Integer categroyId) {
        //全部通过拦截器验证是否登录以及权限
        return iCategoryService.getChildrenParallelCategory(categroyId);
    }

    /**
     * 查询当前节点id和递归子节点id
     *
     * @param
     * @param categroyId
     * @return
     */
    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpServletRequest request, @RequestParam(value = "categoryId", defaultValue = "0") Integer categroyId) {
        return iCategoryService.selectCategoryAndChildrenById(categroyId);
    }
}

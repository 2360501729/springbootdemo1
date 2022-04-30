package com.lcl.pname.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.Teacher;
import com.lcl.pname.entity.User;
import com.lcl.pname.responsestatus.R;
import com.lcl.pname.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public R<User> save(@RequestBody User user) {
        user.setId(null);
        boolean save = userService.save(user);
        if (save) {
            return R.ok(user);
        }
        return R.error();
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public R delete(Long tId) {
        boolean b = userService.removeById(tId);
        if (b) {
            return R.ok();
        }
        return R.error();
    }

    @PutMapping("/modify")
    @ResponseStatus(HttpStatus.OK)
    public R<User> modify(@RequestBody User user) {
        boolean b = userService.updateById(user);
        if (b) {
            return R.ok(user);
        }
        return R.error();
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public R<List<User>> users(@RequestParam Map<String, Object> map) {
        PageVO<User> pageVo = userService.pageWhere(map);
        return R.ok();
    }
}

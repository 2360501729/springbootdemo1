package com.lcl.pname.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcl.pname.appcontext.ProjectAutoConfiguration;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.controllerconfig.security.utils.RedisCacheUtils;
import com.lcl.pname.entity.User;
import com.lcl.pname.responsestatus.R;
import com.lcl.pname.responsestatus.ResultCode;
import com.lcl.pname.service.UserService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Tag(name = "user-controller",description = "这是针对user信息处理的API")
@RestController //responsebody 和 controller 的结合体
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisCacheUtils redisCacheUtils;

    @Operation(summary = "处理保存用户的接口",description = "用于保存用户",
            parameters = {
                    @Parameter(
                            name = "user",description = "前端传输的用户对象",in = ParameterIn.PATH,
                            schema = @Schema(implementation = User.class)
                    )
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "操作成功",
                    content = {
                            @Content(mediaType = "application/json",schema=@Schema(implementation = R.class))
                    })
    })
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public R<User> save(@RequestBody User user) {
        user.setId(null);
        boolean save = userService.save(user);
        if (save) {
            return R.ok(user);
        }
        return R.error();
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public R delete(Long tId) {
        boolean b = userService.removeById(tId);
        if (b) {
            return R.ok();
        }
        return R.error();
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public R<User> modify(@RequestBody User user) {
        boolean b = userService.updateById(user);
        if (b) {
            return R.ok(user);
        }
        return R.error();
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public R<List<User>> users(@RequestParam Map<String, Object> map) {
        PageVO<User> pageVo = userService.pageWhere(map);
        return R.ok();
    }

    /**
     * 用于登录
     *
     * @return 用户对象
     */
    @PostMapping("/旧的login")
    @ResponseStatus(HttpStatus.OK)
    public R<User> user(@RequestBody Map<String, String> map, HttpServletRequest httpServletRequest,
                          HttpSession httpSession, @CookieValue(value = "JSESSIONID", required = false) String sessionId,
                          HttpServletResponse httpServletResponse) {
        //获取用户输入的验证码
        String checkNum = map.remove("checkNum");
        //验证验证码是否正确
        if (!CaptchaUtil.ver(checkNum, httpServletRequest)) {
            //验证码不正确,清除session中的验证码
            CaptchaUtil.clear(httpServletRequest);
            return R.error(ResultCode.CODE_ERROR);
        }
        /*是否保存 cookie,是 boolean 值*/
        Boolean saveme = Boolean.parseBoolean(map.remove("saveme"));
        //查询数据库
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        User user = userService.getOne(wrapper);
        if (user == null) {
            return R.error(ResultCode.LOGIN_ERROR);
        }
        httpSession.setAttribute(ProjectAutoConfiguration.sessionUserKey, user);
        //先判断当前参数中中有没有 sessionId,没有的话就用 session 中的 sessionId
        if (!StringUtils.hasLength(sessionId)) {
            sessionId = httpSession.getId();
        }
        Cookie cookie = new Cookie("JSESSIONID", sessionId);
        if (saveme) {
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
        } else {
            cookie.setMaxAge(-1);
        }
        httpServletResponse.addCookie(cookie);
        return R.ok("登录成功", user);
    }

/*
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody Map<String, String> map) {
    }
*/

    @GetMapping("/logout")
    public R<Object> logout(HttpSession httpSession) {
        //直接让当前会话失效
        httpSession.invalidate();
        //重定向到登录页
        return R.ok().setMessage("退出成功");
    }

//    @RequestMapping("/captcha")
//    @ResponseStatus(HttpStatus.OK)
    public void captcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //去出java11 的警告提醒
        System.setProperty("nashorn.args", "--no-deprecation-warning");
        //gif
        //GifCaptcha captcha = new GifCaptcha(130,32);
        //算数类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 32);
        //几位运算,默认是两位
        captcha.setLen(2);
        //获取结果值,存入redis
        String textValue = captcha.text();
        String valueKey = UUID.randomUUID().toString().replace("-","");
        log.info("验证码的生成 key : {} => value : {}",valueKey,textValue);
        /*将生成的 uuid 作为 key ,验证值 作为 value,放入 redis 指定 验证码 hash 集合中 */
        redisCacheUtils.putValue(ProjectAutoConfiguration.captchaKey,valueKey,textValue);
        /*已经把session关闭了,使用token*/
        //httpServletResponse.setHeader("Cache-Control","no-store,no-cache");
        /*设置请求头*/
        httpServletResponse.setHeader(ProjectAutoConfiguration.CHECK_CODE_KEY,valueKey);
        //System.out.println("SessionId" + httpServletRequest.getSession().getId());
        CaptchaUtil.out(captcha,httpServletRequest,httpServletResponse);
    }
}

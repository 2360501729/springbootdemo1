package com.lcl.pname.controller;


import cn.hutool.core.lang.UUID;
import com.lcl.pname.appcontext.ProjectAutoConfiguration;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.Teacher;
import com.lcl.pname.responsestatus.R;
import com.lcl.pname.responsestatus.ResultCode;
import com.lcl.pname.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/teacher")
//name 是替代当前控制类的名字,后面这个是描述
@Tag(name="teacher-handler",description = "这是一个针对teacher信息处理的API")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 文件上传接口
     * @param multipartFile
     * @return
     * @throws Exception
     */
    //summary 是摘要信息,
    @Operation(summary = "处理文件上传接口",description = "对上传的文件保存指定路径(服务器)",
    parameters = {
            @Parameter(
                    name = "file",description = "前端传输的每个文件的名字",in = ParameterIn.PATH,
                    schema = @Schema(implementation = MultipartFile.class)
            )
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "操作成功",
            content = {
                    @Content(mediaType = "multipart/form-data",schema=@Schema(implementation = R.class))
            })
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/file-upload")           //不加这个file会拿不到数组中的文件,跟前端定义好的文件名字对应
    public R<String> fileUpload(@RequestPart("file") MultipartFile[] multipartFile) throws Exception{
        System.out.println(ProjectAutoConfiguration.fileServerURl);
        File file;
        String[] filesNames = new String[multipartFile.length];
        for (int i = 0; i < multipartFile.length; i++) {
            String child = UUID.randomUUID() +
                    Objects.requireNonNull(multipartFile[i].getOriginalFilename())
                    .substring(Objects.requireNonNull(multipartFile[i].getOriginalFilename()).lastIndexOf("."));
            filesNames[i] = child;
             file = new File(ProjectAutoConfiguration.fileServerURl, child);
            multipartFile[i].transferTo(file);
        }
        //暂时先返回第一个文件的名字,因为现在只有上传一个,暂时先做了扩展.
        return R.ok(filesNames[0]);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/download/{fileUrl}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileUrl")String fileName) throws Exception {
        File file = new File(ProjectAutoConfiguration.fileServerURl, fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] body = new byte[fileInputStream.available()];
        int read = fileInputStream.read(body);
        fileInputStream.close();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment",fileName);

        HttpStatus httpstatus = HttpStatus.OK;
        return new ResponseEntity<byte[]>(body, httpHeaders, httpstatus);
    }

    @GetMapping("/teachers")
    @ResponseStatus(HttpStatus.OK)
    public R<PageVO<Teacher>> findTeachers(@RequestParam Map<String, Object> map) {
        final Map<String, Object> cMap = new ConcurrentHashMap<String, Object>(map);
        cMap.forEach((k, v) -> {
            if (StringUtils.hasLength(v.toString())) {
                return;
            }
            cMap.remove(k);
        });
        PageVO<Teacher> pageVO = teacherService.pageWhere(cMap);
        return R.ok(ResultCode.SUCCESS, pageVO);
    }

    @GetMapping("/teacher/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R<Teacher> findTeacher(@PathVariable("id") Long tId) {
        Teacher byId = teacherService.getById(tId);
        return R.ok(byId);
    }

    @PostMapping("/teacher")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public R<Teacher> saveTeacher(@RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return R.ok(teacher);
        }
        return R.error();
    }

    @DeleteMapping("/teacher/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R deleteTeacher(@PathVariable("id") Long tId) {
        boolean b = teacherService.removeById(tId);
        if (b) {
            return R.ok();
        }
        return R.error();
    }

    @PutMapping("/teacher")
    @ResponseStatus(HttpStatus.OK)
    public R<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return R.ok(teacher);
        }
        return R.error();
    }
}

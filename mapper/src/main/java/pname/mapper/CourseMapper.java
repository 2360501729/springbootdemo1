package pname.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.pname.entity.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

}

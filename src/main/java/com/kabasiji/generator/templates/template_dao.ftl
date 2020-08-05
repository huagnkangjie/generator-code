package ${mapperClassPath};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${modelClassPath}.${fileName}Entity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ${auther}
 * @date ${time}
 */
@Repository
@Mapper
public interface I${fileName}Dao extends BaseMapper<${fileName}Entity>{

}
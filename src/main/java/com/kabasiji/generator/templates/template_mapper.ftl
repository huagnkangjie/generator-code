package ${mapperClassPath};

import ${modelClassPath}.${fileName}Entity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ${auther}
 * @create ${time}
 */
@Repository
@Mapper
public interface ${fileName}Mapper extends MyMapper<${fileName}Entity>{

}
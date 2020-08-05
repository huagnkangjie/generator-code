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
@Component
public class ${fileName}DaoImpl {
     private final I${fileName}Dao ${lowcaseFileName}Dao;

     @Autowired
     public ${fileName}Impl(I${fileName}Dao ${lowcaseFileName}Dao) {
          this.${lowcaseFileName}Dao = ${lowcaseFileName}Dao;
     }
}
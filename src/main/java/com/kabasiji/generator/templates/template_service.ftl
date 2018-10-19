package ${serviceClassPath};

import lombok.extern.log4j.Log4j2;
import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import ${mapperClassPath}.${fileName}Mapper;

/**
 * @author ${auther}
 * @create ${time}
 **/
@Service
@Log4j2
public class ${fileName}Service {

    private final ${fileName}Mapper mapper;

    @Autowired
    public ${fileName}Service(${fileName}Mapper mapper) {
        this.mapper = mapper;
    }

}
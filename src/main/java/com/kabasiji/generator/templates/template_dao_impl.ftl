package ${mapperClassPath};

import ${modelClassPath}.I${fileName}Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ${auther}
 * @date ${time}
 */
@Component
public class ${fileName}DaoImpl {

     private final I${fileName}Dao ${lowcaseFileName}Dao;

     @Autowired
     public ${fileName}DaoImpl(I${fileName}Dao ${lowcaseFileName}Dao) {
          this.${lowcaseFileName}Dao = ${lowcaseFileName}Dao;
     }

     public I${fileName}Dao getDao(){
          return ${lowcaseFileName}Dao;
     }

}
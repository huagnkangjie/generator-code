package com.kabasiji.generator.core.factroy;

import com.kabasiji.generator.core.GeneratorService;
import com.kabasiji.generator.core.impl.GeneratorControllerServiceImpl;
import com.kabasiji.generator.core.impl.GeneratorMapperServiceImpl;
import com.kabasiji.generator.core.impl.GeneratorModelServiceImpl;
import com.kabasiji.generator.core.impl.GeneratorServiceServiceImpl;

/**
 * @author huang_kangjie
 * @create 2018-09-04 10:55
 **/
public class GeneratoerFactroy {

     public enum GeneratoerType {
          MODEL, MAPPER, CONCTROLLER, SERVICE;
     }

     /**
      * 获取生成代码的对象
      * @param type
      * @return
      */
     public static GeneratorService getGenerator(GeneratoerType type){
          GeneratorService service = null;
          switch (type) {
               case MODEL:
                    service = new GeneratorModelServiceImpl();
                    break;
               case MAPPER:
                    service = new GeneratorMapperServiceImpl();
                    break;
               case CONCTROLLER:
                    service = new GeneratorControllerServiceImpl();
                    break;
               case SERVICE:
                    service = new GeneratorServiceServiceImpl();
                    break;
               default:
                    break;
          }
          return service;
     }
}

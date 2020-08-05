package com.kabasiji.generator.core.factroy;

import com.kabasiji.generator.core.GeneratorService;
import com.kabasiji.generator.core.impl.GeneratorCommonImpl;
import com.kabasiji.generator.core.impl.GeneratorControllerServiceImpl;
import com.kabasiji.generator.core.impl.GeneratorDaoImplServiceImpl;
import com.kabasiji.generator.core.impl.GeneratorDaoServiceImpl;
import com.kabasiji.generator.core.impl.GeneratorMapperServiceImpl;
import com.kabasiji.generator.core.impl.GeneratorModelServiceImpl;
import com.kabasiji.generator.core.impl.GeneratorServiceServiceImpl;

/**
 * @author huang_kangjie
 * @create 2018-09-04 10:55
 **/
public class GeneratoerFactroy {

     public enum GeneratoerType {
          MODEL("", "", ""),
          MAPPER("", "", ""),
          CONCTROLLER("", "", ""),
          SERVICE("", "", ""),
          DAO("", "", ""),
          DAO_IMPL("", "", ""),
          I_SERVICE("I%sService.java", "template_i_service.ftl", "IService"),
          CONCTROLLER_2("%sController.java", "template_controllers2.ftl", "controller 2"),
          ;

          private String fileName;
          private String templateName;
          private String remark;
          GeneratoerType(String fileName, String templateName, String remark){
               this.fileName = fileName;
               this.templateName = templateName;
               this.remark = remark;
          }


          public String getRemark() {
               return remark;
          }

          public void setRemark(String remark) {
               this.remark = remark;
          }

          public String getFileName() {
               return fileName;
          }

          public void setFileName(String fileName) {
               this.fileName = fileName;
          }

          public String getTemplateName() {
               return templateName;
          }

          public void setTemplateName(String templateName) {
               this.templateName = templateName;
          }

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
               case DAO:
                    service = new GeneratorDaoServiceImpl();
                    break;
               case DAO_IMPL:
                    service = new GeneratorDaoImplServiceImpl();
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
          if(service == null) {
               //根据枚举在获取一次
               service = new GeneratorCommonImpl(type);
          }
          return service;
     }
}

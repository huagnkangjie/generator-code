package com.kabasiji.generator.core.impl;

import com.kabasiji.generator.core.GeneratorService;
import com.kabasiji.generator.core.Param;
import com.kabasiji.generator.core.factroy.GeneratoerFactroy;

/**
 * @author huang_kangjie
 * @create 2018-09-04 10:57
 **/
public class GeneratorCommonImpl extends GeneratorService {

     private GeneratoerFactroy.GeneratoerType generatoerType;

     public GeneratorCommonImpl(GeneratoerFactroy.GeneratoerType generatoerType){
          this.generatoerType = generatoerType;
     }

     @Override
     public void generator(Param param) {
          create(param);
     }

     @Override
     public String getFileName(Param param) {
          return String.format(generatoerType.getFileName(), param.getFileName());
     }

     @Override
     public String getTemplateName() {
          return generatoerType.getTemplateName();
     }

     @Override
     public void print() {
          System.out.println("======================="+generatoerType.getRemark()+" 生成成功！============================");
     }
}

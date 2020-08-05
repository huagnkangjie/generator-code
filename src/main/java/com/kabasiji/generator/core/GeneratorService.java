package com.kabasiji.generator.core;

/**
 * 自动生成代码
 * @author huang_kangjie
 * @create 2018-09-04 10:54
 **/
public abstract class GeneratorService extends AbstractGeneratorTemplate {

     /**
      * 根据模板生成代码
      * @param param
      */
     public abstract void generator(Param param);
}

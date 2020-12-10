package ${controllerClassPath};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author ${auther}
 * @create ${time}
 **/
@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = {""})
public class ${fileName}Controller {

     private final I${fileName}Service ${lowcaseFileName}Service;

     @Autowired
     public ${fileName}Controller(I${fileName}Service ${lowcaseFileName}Service) {
          this.${lowcaseFileName}Service = ${lowcaseFileName}Service;
     }

}

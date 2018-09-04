package ${controllerClassPath};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ${auther}
 * @create ${time}
 **/
@RestController
@RequestMapping("")
@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = {""})
@Validated
public class ${fileName}Controller {

     private final Errors errors;
     private final ${fileName}Service service;

     @Autowired
     public ${fileName}Controller(Errors errors, ${fileName}Service service) {
          this.errors = errors;
          this.service = service;
     }

}

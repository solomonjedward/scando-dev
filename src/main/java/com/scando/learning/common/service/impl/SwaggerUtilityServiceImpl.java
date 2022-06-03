package com.scando.learning.common.service.impl;

import com.scando.learning.common.models.SecureApiTag;
import com.scando.learning.common.service.SwaggerUtilityService;
import org.springframework.stereotype.Service;
import springfox.documentation.service.Tag;

@Service
public class SwaggerUtilityServiceImpl implements SwaggerUtilityService {

    @Override
    public Tag[] getAllSecuredApiTags() {
        return SecureApiTag.getAll();
    }


}

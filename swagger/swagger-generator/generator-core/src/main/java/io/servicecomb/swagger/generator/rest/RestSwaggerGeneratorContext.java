/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicecomb.swagger.generator.rest;

import org.springframework.util.StringUtils;

import io.servicecomb.swagger.generator.core.AbstractSwaggerGeneratorContext;
import io.servicecomb.swagger.generator.core.OperationGenerator;

public abstract class RestSwaggerGeneratorContext extends AbstractSwaggerGeneratorContext {
  protected void correctPath(OperationGenerator operationGenerator) {
    String path = operationGenerator.getPath();
    if (StringUtils.isEmpty(path)) {
      path = "/";
    }
    operationGenerator.setPath(path);
  }

  @Override
  public void postProcessOperation(OperationGenerator operationGenerator) {
    checkPath(operationGenerator);
    correctPath(operationGenerator);
  }

  protected void checkPath(OperationGenerator operationGenerator) {
    if (StringUtils.isEmpty(operationGenerator.getPath())
        && StringUtils.isEmpty(operationGenerator.getSwagger().getBasePath())) {
      throw new Error("Path must not both be empty in class and method");
    }
  }
}

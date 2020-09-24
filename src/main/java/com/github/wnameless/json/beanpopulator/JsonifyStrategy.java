/*
 *
 * Copyright 2020 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.github.wnameless.json.beanpopulator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * {@link JsonifyStrategy} can annotate on fields of any class which implements
 * {@link JsonPopulatable}. The annotated field will be turn into a JSON string
 * by the {@link JsonifyStrategyProvider} whenever
 * {@link JsonPopulatable#beanToJson} is called.<br>
 * <br>
 * If the keyName is provided, it will be used as the JSON key in
 * {@link JsonPopulatable#beanToJson} otherwise the bean Field name will be used
 * instead.
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonifyStrategy {

  Class<? extends JsonifyStrategyProvider> value() default DefaultJsonifyStrategy.class;

  String keyName() default "";

}

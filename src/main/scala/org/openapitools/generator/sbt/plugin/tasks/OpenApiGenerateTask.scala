/*
 * Copyright (c) 2020 OpenAPI-Generator Contributors (https://openapi-generator.tech)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openapitools.generator.sbt.plugin.tasks

import org.openapitools.codegen.DefaultGenerator
import org.openapitools.generator.sbt.plugin.OpenApiGeneratorKeys
import sbt.{Def, _}

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

trait OpenApiGenerateTask extends OpenApiGeneratorKeys {

  protected[this] def openApiGenerateTask: Def.Initialize[Task[Seq[File]]] = Def.task {

    val logger = sbt.Keys.streams.value.log

    openApiGenerateInputs.value match {
      case Some(clientOptInput) =>
        Try {
          val gen = new DefaultGenerator()

          gen.opts(clientOptInput)

          openApiGenerateMetadata.value.foreach { value =>
            gen.setGenerateMetadata(value)
          }

          val res = gen.generate().asScala

          logger.info(s"Successfully generated code to ${clientOptInput.getConfig.getOutputDir}")
          res
        } match {
          case Success(value) => value
          case Failure(ex) =>
            throw new Exception("Code generation failed.", ex)
        }

      case None =>
        Seq.empty
    }
  }
}

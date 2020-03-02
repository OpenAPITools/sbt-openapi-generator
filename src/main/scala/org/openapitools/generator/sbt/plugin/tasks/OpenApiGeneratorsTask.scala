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

import org.openapitools.codegen.meta.Stability
import org.openapitools.codegen.{CodegenConfigLoader, CodegenType}
import sbt.{Def, Task}

import scala.collection.JavaConverters._

trait OpenApiGeneratorsTask {

  protected[this] def openApiGeneratorsTask: Def.Initialize[Task[Unit]] = Def.task {
    val generators = CodegenConfigLoader.getAll.asScala
    val types = CodegenType.values()

    val stabilities = Stability.values().filterNot {
      _ == Stability.DEPRECATED
    }

    val out = new StringBuilder

    out ++= "The following generators are available:" + System.lineSeparator()
    for (t <- types) {
      val filteredGenerators = generators.filter(_.getTag == t).sortBy(_.getName)
      if (filteredGenerators.nonEmpty) {
        out ++= s" $t generators:" + System.lineSeparator()
        filteredGenerators.foreach {
          generator =>
            val meta = generator.getGeneratorMetadata
            val stability = meta.getStability
            val include = stabilities.contains(stability)
            if (include) {
              out ++= s"    - ${generator.getName}"
              if (stability != Stability.STABLE) {
                out ++= s" (${stability.value()})"
              }
              out ++= System.lineSeparator()
            }
        }
      }
    }
    out ++= System.lineSeparator()
    out ++= System.lineSeparator()

    println(out)
  }
}

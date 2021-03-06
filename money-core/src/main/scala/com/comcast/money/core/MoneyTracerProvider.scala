/*
 * Copyright 2012 Comcast Cable Communications Management, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comcast.money.core

import com.comcast.money.api.{ InstrumentationLibrary, SpanFactory }
import io.opentelemetry.api.trace
import io.opentelemetry.api.trace.TracerProvider

import scala.collection.concurrent.TrieMap

final case class MoneyTracerProvider(tracer: Tracer) extends TracerProvider {

  private val tracers = new TrieMap[InstrumentationLibrary, Tracer]()

  override def get(instrumentationName: String): trace.Tracer = get(instrumentationName, null)
  override def get(instrumentationName: String, instrumentationVersion: String): trace.Tracer = {
    val library = new InstrumentationLibrary(instrumentationName, instrumentationVersion)
    tracers.getOrElseUpdate(library, {
      tracer.spanFactory match {
        case csf: CoreSpanFactory => new Tracer {
          override val spanFactory: SpanFactory = csf.copy(library = library)
        }
        case _ => tracer
      }
    })
  }
}

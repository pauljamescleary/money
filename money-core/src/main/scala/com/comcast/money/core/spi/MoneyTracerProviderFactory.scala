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

package com.comcast.money.core.spi

import com.comcast.money.core.{ Money, MoneyTracerProvider, Tracer }
import io.opentelemetry.api.trace.TracerProvider
import io.opentelemetry.api.trace.spi.TracerProviderFactory

class MoneyTracerProviderFactory(tracer: Tracer) extends TracerProviderFactory {
  def this() = this(Money.Environment.tracer)

  override def create(): TracerProvider = {
    MoneyTracerProvider(tracer)
  }
}

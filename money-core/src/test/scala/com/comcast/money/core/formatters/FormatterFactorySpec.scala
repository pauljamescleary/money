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

package com.comcast.money.core.formatters

import com.typesafe.config.ConfigFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FormatterFactorySpec extends AnyWordSpec with Matchers {
  "FormatterFactory" should {
    "create an instance of a Formatter" in {
      val config = ConfigFactory.parseString(s"class=${classOf[MoneyTraceFormatter].getCanonicalName}")

      val formatter = FormatterFactory.create(config)
      formatter shouldBe a[MoneyTraceFormatter]
    }
  }
}
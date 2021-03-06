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

package com.comcast.money.core.handlers

import com.typesafe.config.ConfigFactory
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class HandlerFactorySpec extends AnyWordSpec with Matchers {

  "HandlerFactory" should {
    "create a span handler based on class" in {
      val config = ConfigFactory.parseString(s"class=${classOf[NonConfiguredHandler].getCanonicalName}")

      val createdHandler = HandlerFactory.create(config)
      createdHandler shouldBe a[NonConfiguredHandler]
    }

    "create a configurable span handle and call configure on it" in {
      val config = ConfigFactory.parseString(s"class=${classOf[ConfiguredHandler].getCanonicalName}")

      val createdHandler = HandlerFactory.create(config)
      createdHandler shouldBe a[ConfiguredHandler]

      createdHandler.asInstanceOf[ConfiguredHandler].calledConfigure shouldBe true
    }
  }
}

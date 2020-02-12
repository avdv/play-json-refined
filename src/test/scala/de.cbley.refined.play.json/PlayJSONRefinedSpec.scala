/*
 * Copyright 2019 Claudio Bley
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

package de.cbley.refined.play.json

import eu.timepit.refined.auto._
import eu.timepit.refined.numeric.{Negative, Positive}
import eu.timepit.refined.api.Refined
import eu.timepit.refined.scalacheck.numeric._
import _root_.play.api.libs.json.{JsNumber, JsSuccess, Json}
import play.api.libs.json.JsError
import org.scalacheck.Prop._
import org.scalacheck.Properties
import shapeless.test.illTyped

class PlayJSONRefinedSpec extends Properties("PlayJSONReadsWrites") {
  property("reads success") = forAll { n: Int Refined Positive =>
    Json.fromJson[Int Refined Positive](JsNumber(BigDecimal(n))) ?= JsSuccess(n)
  }

  property("reads failure") = forAll { n: Int Refined Negative =>
    Json.fromJson[Int Refined Positive](JsNumber(BigDecimal(n))) ?=
      JsError(s"Predicate failed: ($n > 0).")
  }

  property("writes success") = forAll { n: Int Refined Positive =>
    Json.toJson[Int Refined Positive](n) ?= JsNumber(BigDecimal(n))
  }

  property("writes failure") = secure {
    illTyped(
      """Json.toJson[Int Refined Positive](-10)""",
      """Predicate failed.*"""
    )
    true
  }
}

/*
 * Copyright 2016 Sebastian Wiesner
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

package com.lunaryorn.refined.play.json

import eu.timepit.refined.auto._
import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.api.Refined
import _root_.play.api.libs.json.{JsNumber, JsSuccess, Json}
import play.api.libs.json.JsError
import org.scalacheck.Prop._
import org.scalacheck.Properties
import shapeless.test.illTyped

class PlayJsonSpec extends Properties("PlayJSONReadsWrites") {
  type PosInt = Int Refined Positive

  property("reads success") = secure {
    Json.fromJson[PosInt](JsNumber(10)) ?= JsSuccess(10: PosInt)
  }

  property("reads failure") = secure {
    Json.fromJson[PosInt](JsNumber(-42)) ?=
    JsError("Predicate failed: (-42 > 0).")
  }

  property("writes success") = secure {
    Json.toJson(10: PosInt) ?= JsNumber(10)
  }

  property("writes failure") = secure {
    illTyped("""Json.toJson[PosInt](-10)""", """Predicate failed.*""")
    true
  }
}

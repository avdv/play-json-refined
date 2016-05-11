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

package com.lunaryorn.refined.play

import eu.timepit.refined.api._
import _root_.play.api.libs.json._

import scala.language.higherKinds

package object play {
  implicit def writeRefined[T, P, F[_, _]](
    implicit
    writesT: Writes[T],
    reftype: RefType[F]
  ): Writes[F[T, P]] = Writes(value => writesT.writes(reftype.unwrap(value)))

  implicit def readRefined[T, P, F[_, _]](
    implicit
    readsT: Reads[T],
    reftype: RefType[F],
    validate: Validate[T, P]
  ): Reads[F[T, P]] = Reads(jsValue => readsT.reads(jsValue).flatMap { valueT =>
    reftype.refine[P](valueT) match {
      case Right(valueP) => JsSuccess(valueP)
      case Left(error) => JsError(error)
    }
  })
}

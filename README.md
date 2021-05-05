# play-json-refined

[![Apache 2.0 license](https://img.shields.io/github/license/avdv/play-json-refined)](http://www.apache.org/licenses/LICENSE-2.0)
![Github workflow status](https://github.com/avdv/play-json-refined/actions/workflows/test.yml/badge.svg?branch=master)

[Play JSON][] Reads/Writes instances for [refined][] types. 
In other words, this library let’s you convert refined types to and from JSON.

[Play JSON]: https://www.playframework.com/documentation/2.8.x/ScalaJson
[refined]: https://github.com/fthomas/refined

## Installation

Add the following to your `build.sbt`:

```scala
libraryDependencies += "de.cbley" %% "play-json-refined" % "<version>"
```

Replace `<version>` with the desired version, see https://mvnrepository.com/artifact/de.cbley/play-json-refined.

## Usage

``` scala
import eu.timepit.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.api._
import play.api.libs.json._
import de.cbley.refined.play.json._
Welcome to Scala 2.11.8 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_65).
Type in expressions for evaluation. Or try :help.

scala> type PosInt = Int Refined numeric.Positive
defined type alias PosInt

scala> val foo : PosInt = 42
foo: PosInt = 42

scala> Json.toJson(foo)
res0: play.api.libs.json.JsValue = 42

scala> Json.fromJson[PosInt](JsNumber(42))
res3: play.api.libs.json.JsResult[PosInt] = JsSuccess(42,)

scala> Json.fromJson[PosInt](JsNumber(-42))
res4: play.api.libs.json.JsResult[PosInt] =
JsError(List((,List(ValidationError(List(Predicate failed: (-42 >
0).),WrappedArray())))))
```

## License

Copyright 2016–2019 Sebastian Wiesner
Copyright 2019 Claudio Bley

Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License.  You may obtain a copy of the
License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied.  See the License for the
specific language governing permissions and limitations under the License.

# play-json-refined

[![Bintray](https://img.shields.io/bintray/v/lunaryorn/maven/play-json-refined.svg?style=plastic)](https://bintray.com/lunaryorn/maven/play-json-refined/#)
[![Apache 2.0 license](https://img.shields.io/github/license/lunaryorn/play-json-refined.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Travis master build status](https://img.shields.io/travis/lunaryorn/play-json-refined/master.svg)](https://travis-ci.org/lunaryorn/play-json-refined)

[Play JSON][] Reads/Writes instances for [refined][] types. 
In other words, this library let’s you convert refined types to and from JSON.

[Play JSON]: https://www.playframework.com/documentation/2.5.x/ScalaJson
[refined]: https://github.com/fthomas/refined

## Installation

Add the following to your `build.sbt`:

```scala
resolvers += Resolver.bintrayRepo("lunaryorn", "maven")
libraryDependencies += "de.swsnr" %% "play-json-refined" % "<version>"
```

Replace `<version>` with the desired version (see Bintray badge above).

## Usage

``` scala
import eu.timepit.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.api._
import play.api.libs.json._
import com.lunaryorn.refined.play.json._
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

Copyright 2016–2018 Sebastian Wiesner

Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License.  You may obtain a copy of the
License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied.  See the License for the
specific language governing permissions and limitations under the License.

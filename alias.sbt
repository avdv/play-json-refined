addCommandAlias("compileAll", "; compile; test:compile")
addCommandAlias("headerCheckAll", "; headerCheck; test:headerCheck")
addCommandAlias("scalafmtCheckAll",
                "; scalafmtCheck; test:scalafmtCheck; scalafmtSbtCheck")
addCommandAlias("scalafmtAll", "; scalafmt; test:scalafmt; scalafmtSbt")

// Do-it-all command for Travis CI
val validateCommands = List(
  "clean",
  "headerCheckAll",
  "compileAll",
  "scalafmtCheckAll",
  "test"
)
addCommandAlias("validate", validateCommands.mkString(";", ";", ""))

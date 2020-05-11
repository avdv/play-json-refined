addCommandAlias("compileAll", "; compile; test:compile")
addCommandAlias("headerCheckAll", "; headerCheck; test:headerCheck")

// Do-it-all command for Travis CI
val validateCommands = List(
  "clean",
  "headerCheckAll",
  "compileAll",
  "scalafmtSbtCheck",
  "scalafmtCheckAll",
  "test"
)
addCommandAlias("validate", validateCommands.mkString(";", ";", ""))

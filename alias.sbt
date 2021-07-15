addCommandAlias("compileAll", "; compile; Test/compile")
addCommandAlias("headerCheckAll", "; headerCheck; Test/headerCheck")

// Do-it-all command for CI
val validateCommands = List(
  "clean",
  "headerCheckAll",
  "compileAll",
  "scalafmtSbtCheck",
  "scalafmtCheckAll",
  "test"
)
addCommandAlias("validate", validateCommands.mkString(";", ";", ""))

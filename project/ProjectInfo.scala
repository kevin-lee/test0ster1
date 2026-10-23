import sbt.{Developer, ScmInfo, url}

object ProjectInfo {
  val ProjectVersion: String = "0.2.0"

  val projectDevelopers: List[Developer] = List(
    Developer(
        "kevin-lee", "Kevin Lee"
      , "kevin.code@kevinlee.io"
      , url("https://github.com/kevin-lee")
    ))

  val projectHomePage: Option[sbt.URL] = Some(url("https://github.com/kevin-lee/test0ster1"))

  val projectScmInfo: Option[ScmInfo] = Some(ScmInfo(
        url("https://github.com/kevin-lee/test0ster1")
      , "git@github.com:kevin-lee/test0ster1.git"
    ))
}

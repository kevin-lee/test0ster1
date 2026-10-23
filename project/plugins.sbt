logLevel := Level.Warn

addSbtPlugin("com.github.sbt"   % "sbt-ci-release"        % "1.12.1")
addSbtPlugin("com.github.sbt" % "sbt-jacoco"            % "3.7.0")
addSbtPlugin("com.github.sbt.junit" % "sbt-jupiter-interface" % "0.19.0")

val sbtDevOopsVersion = "3.9.0"
addSbtPlugin("io.kevinlee" % "sbt-devoops-java"      % sbtDevOopsVersion)
addSbtPlugin("io.kevinlee" % "sbt-devoops-sbt-extra" % sbtDevOopsVersion)
addSbtPlugin("io.kevinlee" % "sbt-devoops-github"    % sbtDevOopsVersion)

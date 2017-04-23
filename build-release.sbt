import ReleaseTransformations._

ghreleaseNotes := {
  tag => s"""See CHANGELOG [$tag](../master/CHANGELOG.md#${tag.stripPrefix("v")}) for details."""
}

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  pushChanges,  // githubRelease: Remote repository doesn't have tag. You need to push it first
  releaseStepInputTask(githubRelease),  // Release to GitHub instead of `publishArtifacts`
  setNextVersion,
  commitNextVersion,
  pushChanges)

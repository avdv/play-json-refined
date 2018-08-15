# Contributing

## Release process

Travis CI automatically publishes all builds to Bintray, so publishing is simple:

- Check that `CHANGELOG.md` lists all important changes.
- Add a new headline for the new version and commit.
- Tag the commit with the version, ie, `git tag -sm 'play-json-refined X.Y' vX.Y`.
- `git push --follow-tags`

# Contributing

## Release process

Travis CI automatically publishes all builds to Bintray, so publishing is simple.

To release version `X.Y.Z`:

- Check that `CHANGELOG.md` lists all important changes.
- `sbt 'release X.Y.Z'` (quotes are important!)
- Check that the changelog looks good and the tag `vX.Y.Z` exists.
- `git push --follow-tags`

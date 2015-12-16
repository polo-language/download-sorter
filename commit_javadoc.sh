### Builds temporary local repo and commits javadocs to GitHub remote

todaysDate="$(date +'%Y-%m-%d')"
currentDir="$(pwd)"
docDir="build/docs/javadoc/"
remoteHost="github.com"
repoName="download-sorter"
remoteBranch="gh-pages"
remoteLocalName="javadoc"
commitMessage="\"$todaysDate\""

if [ ! -d "$docDir" ]; then
  echo "Run \"gradle javadoc\" first to create documentation!"
  exit 1
fi
cd "$docDir"
currentRemoteLocalName="$(git remote)"

if [ ! -d ".git" ]; then
  echo "Creating new git repo..."
  git init
fi
if [ "$remoteLocalName" != "$currentRemoteLocalName" ]; then
  echo "$currentRemoteLocalName"
  echo ""
  echo "Setting remote to local name $remoteLocalName..."
  git remote add "$remoteLocalName" git@"$remoteHost":polo-language/"$repoName".git
fi

git fetch --depth=1 "$remoteLocalName" "$remoteBranch"
git add --all
git commit -m "$commitMessage"
git merge --no-edit -s ours remotes/"$remoteLocalName"/"$remoteBranch"
git push "$remoteLocalName" master:"$remoteBranch"

# rm -rf .git
cd "$currentDir"

echo ""
echo "Committed contents of directory $docDir"
echo "    to branch $remoteBranch"
echo "    of repo $repoName"
echo "    on remote $remoteHost"
echo "    with message: $commitMessage."

exit 0

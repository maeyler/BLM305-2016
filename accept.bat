rem # accept user
git remote add %1 https://github.com/%1/Automata
git pull %1 master
rem # git push origin
git remote rm %1
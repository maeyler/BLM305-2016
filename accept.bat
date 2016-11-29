rem # accept user
git remote add %1 https://github.com/%1/BLM305
git pull %1 master
rem # git push 
git remote rm %1

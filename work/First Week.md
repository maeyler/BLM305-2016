## First Week

* Study [Small-Simple-Safe](http://maeyler.github.io/SmallSimpleSafe/index.html) web site, download [SSS](https://github.com/maeyler/SmallSimpleSafe/blob/master/sss.jar?raw=true)
and [DiningPhilosophers.jar](https://github.com/maeyler/Frameworks/blob/master/DiningPhilosophers.jar?raw=true)
* Start SSS and select `Chooser.runTeacher("String")`
* Select the jar file, convert it to URL, and run DiningPhilosophers until deadlock occurs
```
f = Chooser.file(); //select DiningPhilosophers.jar
i = f.toURI(); //--> file:/C:/java/jars/DiningPh...
u = i.toURL(); //--> file:/C:/java/jars/DiningPh...
Chooser.loadClass(u, "DiningPhilosophers");
d = new DiningPhilosophers(5); 
d.start();
```
Read  http://eyler.blogspot.com.tr/2004/12/neden-java.html

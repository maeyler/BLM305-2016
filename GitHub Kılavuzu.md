## GitHub Kılavuzu

GitHub kullanmak için üç önemli sebep:
* Projeniz bir bütün olarak bulutta, her yerden erişilebilir
* Otomatik sürüm kontrolü ve koordineli takım çalışması 
* Programcılar için özgeçmişin önemli bir parçası

Git'in akış ve dallanma mantığını anlamak için iyi bir başlangıç [burada](https://guides.github.com/introduction/flow/)

### GitHub Desktop

Mac ve Windows için yazılmış bu program ile Git kullanmak çok kolaylaştı

Öncelikle [GitHub hesabı açmak](https://github.com/join) ve [Desktop programını kurmak](https://desktop.github.com) gerekiyor

Örnek olarak eski bir projemizi GitHub'a koyalım:
* Klasörü programın açık penceresine sürükleyin, güzel bir isim verin
* "Uncommitted Changes" üstünde tıklayın, yeni bir isim ile `Commit` düğmesine basın
* `Publish` düğmesine basarak kendi GitHub hesabınıza ekleyin

İşimiz üç adımda bitti, projemiz hem yerel diskte hem de GitHub repository'de duruyor
* Bunlardan biri değişince, `Sync` düğmesine basmak gerekiyor  **<-- Bu adım otomatik değil**

### Commit, Branch, Pull Request, Merge

Yerel bilgisayarda denemeleri yapıp çalışır duruma getirince `Commit` işlemini yapıyoruz. Dokümanlardaki "Kaydet" gibi bir işlem, tek farkı, bütün ayrıntılar kaydedildiği için eski bir duruma dönmek mümkün. `Commit` işlemlerinin çok sayıda olmasında korkmayın: Her Commit, programın bir tek özelliği üstünde çalışsa iyi olur.

Yeni bir özellik denemek ya da takım çalışması yapmak için [dallanma](https://guides.github.com/introduction/flow) gerekiyor. Her dalı ayrı bir proje olarak düşünebiliriz. Desktop programında dal değiştirince, klasördeki dosyalar da uygun bir şekilde değişiyor, sadece o dala ait dosyalar görünüyor. Programın ana dalına `master` adı verilmiş. 

Bir dal üstünde yeteri kadar çalışıp doğru işlediği görülünce, o dalın sahibi `Pull Request` yapıyor, projenin sahibi ya `Merge` ile yeni özellikleri programa ekliyor, ya da dalı silerek reddediyor. 

### Markdown (MD) formatı

Karmaşık HTML sayfalarını basitleştiren [MD formatını](https://guides.github.com/features/mastering-markdown/#examples) on dakikada öğrenmek ister misiniz? GitHub'da bu formatı kullanmak şart değil ama bunun gibi sayfaları düz metin gibi kolayca yazabilirsiniz.

![örnekler sayfası](images/examples.PNG)

Markdown denemeleriniz için [bir çevirmen](http://dillinger.io/) yararlı olacak

### Git Command Line (CLI)

Desktop programı öncesinde, bu adımları *Command line* içinde yerel klasör altında `git` komutu ile yapmak gerekiyordu

Yukarıda anlatılan her işleme karşı gelen `git` komutu ile kıyaslayınca sürecin ne kadar basitleştiğini görebilirsiniz:
* Klasörü programın açık penceresine sürükleyin   `git init`
* "Uncommitted Changes" üstünde tıklayın   `git add *` 
* ... yeni bir isim ile `Commit` düğmesine basın  `git commit -m "yeni bir isim"`
* `git remote add origin https://github.com/maeyler/xxx.git` (remote repo'yu tanıtmak için)
* `Publish` düğmesine basarak kendi GitHub hesabınıza ekleyin  `git push origin master`
* `Sync` düğmesine basmak gerekiyor   `git push origin master; git pull`

Git için [kısa bir özet](http://try.github.io) ve [kapsamlı bir kitap](https://git-scm.com/book/en/v2) var 
ama Desktop programı normal bir kullanıcının çoğu ihtiyacını karşılıyor. "Merge conflict" ya da "merge revert" durumlarında biraz Git bilmek gerekecektir.

### Referanslar

* https://forvo.com/word/github/
* https://en.wikipedia.org/wiki/Git_(software)
* https://github.com
* https://desktop.github.com
* https://guides.github.com
* https://guides.github.com/introduction/flow
* https://guides.github.com/introduction/getting-your-project-on-github
* https://guides.github.com/features/mastering-markdown/
* https://dillinger.io
* https://try.github.io

### Teşekkürler

* Gürsu Gülcü 
* [Hakan DILEK](https://github.com/hakandilek)
* Ayla Gülcü
* [Güngör Yılmaz](https://github.com/gungor)
 
Eski öğrencilerim bu konuda bana öğretmen oldular :smile:

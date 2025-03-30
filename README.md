# Projekti Ülevaade

## Projekti valmimine
Antud projekti valmimiseks kulus ligikaudu **2 päeva**. Üldiselt oli töö lihtne, kuid kuna polnud varem **Spring Boot'i** kasutanud, pidin uurima ja õppima dokumentatsiooni wiki kaudu.
Projektis võib olla segaselt eesti ja inglisekeelseid sõnu. Algselt plaanis teha eesti keeles, kuid aegamööda sain aru et sõnavara ei ole nii lihtne kui inglise keeles

## Peamised raskused
- **CORS reeglid** tekitasid keerukusi ja nõudsid põhjalikumat uurimist.
- **Failide struktuuri planeerimine** oli alguses problemaatiline.
- Vahepeal jäi **konversioonidega seotud probleem** kahe silma vahele, põhjustades tundidepikkust segadust.

## Lahendamata ülesanded
Kuigi oli plaanis luua **Dockeri konteiner**, jäi see tegemata seoses perekondlike muredega, mis ei võimaldanud varasemat alustamist.

## Projekti ülevaade
Lahenduse osas olen uhke:
- **Frontendi disain** on küll lihtne, kuid täidab oma funktsiooni.
- Kood oleks tulnud jooksvalt kommenteerida (dokumenteerida)

## Kuidas käivitada?
Releaside alt 
https://github.com/MadisJu/CG_INT_Project/releases/tag/Bulid
- alla laadida fullbulid.zip
- lahti pakkida omale mugavasse kohta
- käivitada backend kaustas oleva .bat faili (või jar kui ei soovi konsooli näha ja soovite et server töötaks tagataustal ilma force sulgemiseta)
- anda sekund-kümme
- fronend kaustas avada index.html faili.

Või sourcekoodis
- terminalis avada töökaust ja terminalise jooksutada  "./gradlew.bat bootRun"
- frontend kaustas avada index.html.

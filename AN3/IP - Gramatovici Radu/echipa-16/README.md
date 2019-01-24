# Books to read

## Conventions
Always [clean](https://goo.gl/8lgeV5) the android project before pushing into the branch/master.

## About
Books to Read este o aplicație mobile bazată pe conceptul de “social cataloging”  gandita pentru a ajuta utilizatorii,
persoane pasionate de citit, sa isi gaseasca mai usor cele mai potrivite carti pentru ei pe care sa le organizeze sub 
forma de “rafturi” personalizate – care pot fi impartasite altor utilizatori.
Un alt scop al aplicatiei este de a promova cititul in randul tinerilor.

## Faq
* I get the following error:

```
Error:The modules ['app', 'echipa-16-app'] point to the same directory in the file system.
Each module must have a unique path.

Error:The modules ['echipa-16', 'git-echipa-16'] point to the same directory in the file system.
Each module must have a unique path.
```
This may happen because it is the first time you run the project.
You have to go to:
```
Gradle > Refresh (Press Yes if prompted)
```
* I get the following error

```
Error:Execution failed for task ':app:processDebugGoogleServices'.
> File google-services.json is missing. The Google Services Plugin cannot function without it. 
Searched Location: 
PATH\echipa-16\app\src\debug\google-services.json
PATH\echipa-16\app\google-services.json
```
This may happen if you did not set the connection with firebase.
You have to go to:
```
Tools > Firebase > Assistant > Connect to firebase
```
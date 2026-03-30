@echo off
echo Nettoyage et Compilation en cours...

:: Creation du dossier bin s'il n'existe pas
if not exist bin mkdir bin

:: Suppression des anciens fichiers .class pour eviter les conflits
del /s /q bin\*.class >nul 2>&1

:: 1. Compilation du package pipeandfilter
:: On compile d'abord les interfaces et les filtres
javac -d bin src/pipeandfilter/*.java

:: 2. Compilation de la classe principale MainApp
:: On utilise -cp bin pour que MainApp trouve le package pipeandfilter deja compile
javac -d bin -cp bin src/MainApp.java

:: Verification du code de retour (0 = succes)
if %errorlevel% neq 0 (
    echo Erreur de compilation detectee.
    pause
    exit /b
)

echo Compilation terminee avec succes.
echo Lancement de l'application...

:: 3. Execution
:: On definit le classpath sur bin pour charger toutes les classes
java -cp bin MainApp

pause
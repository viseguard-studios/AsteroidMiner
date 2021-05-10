dir /s /B *.java > sources.txt

javac -encoding utf8 -d bin @sources.txt
jar cfm AsteroidMiner.jar ./src/java/META-INF/MANIFEST.MF -C ./bin . -C ./src/resources .
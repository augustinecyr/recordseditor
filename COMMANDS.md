### Maven Commands
1. mvnw
2. mvnw compile
3. mvnw package
4. mvnw clean package
5. mvnw clean
6. mvnw clean package spring-boot:run
7. mvnw spring-boot:run
8. mvnw spring-boot:run -Dspring-boot.run.arguments="--port=3000"
9. mvnw spring-boot:run -Dspring-boot.run.arguments="--port=3000 --dataDir=c:\data"
10. mvnw clean test

### Git Commands
1. git init
2. git remote add origin https://github.com/<username>/<projectname>.git
3. git add *
4. git status
5. git log 
6. git commit -m "message"
7. git push -u origin master
8. git pull 
9. git branch -a
10. git branch -delete <branch name>
11. git checkout -b develop master
12. git push -u origin develop
13. git merge develop

### Heroku Commands
ALWAYS MERGE DEVELOP AND BE ON MASTER BRANCH BEFORE DOING THIS
CREATE system.properties file
java.runtime.version:18
1. heroku login / heroku login -i
2. heroku apps:create
3. git push heroku master 
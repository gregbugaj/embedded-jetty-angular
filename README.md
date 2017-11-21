# UI 

## Generated With
`ng new ui --dir=.`

# Update project to build to external location

Edit '.angular-cli.json' to and change to

`"outDir": "../main/resources/webapp"`
      
Build and push changes to external resource folder for our java application
`ng build --watch`


# Building and Running 

`mvn clean package`

`java -jar ./target/angularjetty-0.0.1-SNAPSHOT.jar`

## Entry points

Angular app `http://localhost/static.html`
Static page `http://localhost/index.html`


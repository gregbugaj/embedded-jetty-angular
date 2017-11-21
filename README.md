# Embedded Jetty with Angular JS pacakges into stand allone jar.

Example of Embedding HTML/Image resources and serving them from within a single jar file.

This project uses Static page and Angular app for demo purpoeses.
Files located in `/main/resources/webapp` will be accesible and will be served.


Application itself registers two handlers one for Servlets and another one for Resources.

```
        // bootstrap webapp from resource folder
        final String extForm = this.getClass().getClassLoader().getResource("webapp").toExternalForm();
        LOGGER.info("Serving from : {}", extForm);

        final ResourceHandler resHandler = new ResourceHandler();
        resHandler.setResourceBase(extForm);
        final ContextHandler ctx = new ContextHandler("/");
        ctx.setHandler(resHandler);
```

Key here is that we call `toExternalForm()` this will allow us get the form as an URL.

# Angular UI 

## App generation
`ng new ui --dir=.`


# Update Angular project to build to external location 

Edit `.angular-cli.json` and change

`"outDir": "dest"` =>
`"outDir": "../main/resources/webapp"` 
      
Build and push changes to external resource folder for our java application during dev cycle using `ng build --watch` this is similar to `ng serve` but it allow us to use the Jetty webserver as the changes are pushed to the `../main/resources/webapp` resource folder. While `ng serve` will onyl serve files from memory

# Java Application

## Building and Running 

`mvn clean package`

`java -jar ./target/angularjetty-0.0.1-SNAPSHOT.jar`

## Entry points

 * Angular app `http://localhost/index.html`
 * Static page  `http://localhost/static.html`


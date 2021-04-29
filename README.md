# URLStr

## About

URLStr is short for URLStore and is a little bookmark management
application using an embedded Neo4J database in the background for
rich data management, Quarkus using Java as backend framework, and
Angular for the frontend.

URLStr was created because of my need to manage, tag, comment and
query my huge list (bookmarks) of online resources (articles,
videos...).

Another need was to have my bookmarks stored in a local application
that runs in my home network. In fact it should run on a simple home
server or NAS as a simple docker container - easy to setup, manage and update.

The most import and final need was to experiment with (for me) new technology,
in this case above mentioned and more. It was also started
to study using [DDD](https://en.wikipedia.org/wiki/Domain-driven_design)
principles using Quarkus.

Currently used technologies.

* [Quarkus](https://quarkus.io)
* [Neo4J](https://neo4j.com)
* [Angular](https://angular.io)
* [Angular Material UI](https://material.angular.io/)

Older versions used Svelte/Swagger but I recently switched to Angular.

## Status

This is currently very much work in progress.

* Basic function do not work yet
* UI flow is far from finalized
* Database design is not finished
* Use cases are not finalized

## Future

This is fun- and study-ware. I currently do not have the time to work
on this regulary. My most important goals are to have fun while learning.

Having the best-possible application is currently not a goal and
likely would not be reached anyway because of lack of time.

However you are welcome to participate and learn with me or
make me learn from you -as long as you do not change the most important
design and technology decisions. Just contact me.

## Build for development

### Backend

This is a normal Quarkus application. Run it in development mode
using

```bash
   > mvn quarkus:dev
```

In this case it will hot-reinitialize on the next REST call.

It will offer a number of REST services at localhost:8080.

The backend directory also contains a number of *.sh scripts
that read or write data via the REST services.

Use `import.sh` to import a small number of data.

The one thing not covered by Quarkus modules is the integrated
NEO4J database.

You can connect to it via localhost:7687.

A simple

```neo4j
  MATCH (n) RETURN n
```

should show the imported data.  

### Frontend

The frontend is a standard Angular with Angular Material application.

In development mode, use

```bash
  > ng serve
```

to start a small web server at localhost:4200 that serves the frontend.

Currently URLs for the backend are hardcoded, just starting frontend and
backend in development mode should result in a working application.

## Build for release

There is currently no working code or profile settings for this. It is
planed to use the backend also as static web server for the
frontend and wire everything via environment variables
referenced in the various (not yet existing) production profiles.

This should allow to copy everything into one docker container with
the java backend running as the only process.

## License

Apache License 2.0

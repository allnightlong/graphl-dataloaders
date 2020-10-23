# GraphQL DataLoaders
Little project to demonstrate `n + 1` problem in graphql queries and how to resolve it.

## Default approach
Start the app, navigate to <http://localhost:8080/altair> and request following query:

```graphql
query {
  products {
    id
    name
    brand
    reviews {
      id
      title
      body
      replies {
        id
        user
        body
      }
    }
  }
}
```

In log file tons of request will be generated, so request completion time is about 10s. 

Exact timings could be checked at <http://localhost:8080/actuator/metrics/graphql.timer.query> and <http://localhost:8080/actuator/metrics/graphql.timer.resolver>.

## DataLoaders approach
Change profile value from `default` to `dataloaders` in `src/main/resources/application.yml`, reboot the app, and request the same query.
Now request completes in 200-300 ms, not 10s. Also, exact 3 db query in console log, not 3000+. 



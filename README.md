# very-good-purchase
Lab to experiment with Reactive and MongoDB.

# Quickstart
## Running integration tests
Setup mongodb dependency using docker-compose before running integration tests.

```bash
docker-compose up -d
gradle integrationTest
```

# TODO
[/] openapi
    [] Map model.Purchase to domain.Purchase 
    [] Fix type for amountDollars type
[] Page results, sort by date desc
[] Load data from CSV
[] Auth
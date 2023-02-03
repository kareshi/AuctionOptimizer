https://developer.ebay.com/api-docs/static/gs_ebay-rest-getting-started-landing.html

*** Docker ***

Build Docker image 
docker build --tag springbootaws-docker .

Run

[//]: # (Docker will expose the port 9090 to the world redirecting to internal port 8080)
docker run -dp 9090:8080 springbootaws-docker:latest
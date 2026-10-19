Task API

Build: docker build -t task-api:1.0 .

Run: docker run --rm -d --name task-api-container -p 8080:8080 -e APP_MESSAGE="Hello from Docker" task-api:1.0

Test: GET /health — curl http://localhost:8080/health | GET /tasks — curl http://localhost:8080/tasks | POST /tasks — curl -X POST http://localhost:8080/tasks -H "Content-Type: text/plain" -d "Learn Kubernetes"

Stop: docker stop task-api-container

Repo link: https://github.com/emresurgun/CENG413-HW1-Submission-

Emre Şurğun 230212034

# King AI - Complete Installation & Setup Guide

## 📚 Table of Contents
1. [Quick Start (5 minutes)](#quick-start)
2. [Detailed Setup](#detailed-setup)
3. [Project Structure](#project-structure)
4. [Running the Application](#running-the-application)
5. [Troubleshooting](#troubleshooting)

---

## Quick Start

### Prerequisites Check
```bash
# Check Java version (need 17+)
java -version

# Check Maven version (need 3.6+)
mvn -version
```

### Step 1: Get OpenRouter API Key
1. Go to https://openrouter.ai
2. Sign up (free account)
3. Get your API key from Settings → Keys
4. Copy it somewhere safe

### Step 2: Setup Project
```bash
# Navigate to project directory
cd king-ai

# Set your API key
export OPENROUTER_API_KEY="sk-or-your-key-here"

# Build and run
mvn clean install
mvn spring-boot:run
```

### Step 3: Access App
Open browser → http://localhost:8080

---

## Detailed Setup

### 1. Project Structure Setup

Create this folder structure:

```
king-ai/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/kingai/
│   │   │       ├── KingAiApplication.java
│   │   │       ├── controller/
│   │   │       │   └── ChatController.java
│   │   │       ├── service/
│   │   │       │   └── OpenRouterService.java
│   │   │       └── dto/
│   │   │           ├── ChatRequest.java
│   │   │           └── ChatResponse.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── static/
│   │           └── index.html
│   └── test/
│       └── java/
│           └── com/kingai/
├── pom.xml
├── Dockerfile
├── docker-compose.yml
├── .gitignore
└── README.md
```

### 2. File Placement

Place the provided files in these locations:

**Java Files:**
```
src/main/java/com/kingai/
├── KingAiApplication.java
├── controller/ChatController.java
├── service/OpenRouterService.java
└── dto/
    ├── ChatRequest.java
    └── ChatResponse.java
```

**Configuration & Frontend:**
```
src/main/resources/
├── application.yml
└── static/
    └── index.html
```

**Build & Docker:**
```
project-root/
├── pom.xml
├── Dockerfile
└── docker-compose.yml
```

### 3. Environment Setup

#### On Linux/macOS:
```bash
# Add to ~/.bashrc or ~/.zshrc
export OPENROUTER_API_KEY="sk-or-your-actual-key"

# Reload
source ~/.bashrc  # or source ~/.zshrc
```

#### On Windows (PowerShell):
```powershell
$env:OPENROUTER_API_KEY = "sk-or-your-actual-key"

# To make permanent, use:
[System.Environment]::SetEnvironmentVariable("OPENROUTER_API_KEY", "sk-or-your-key", "User")
```

#### On Windows (Command Prompt):
```cmd
set OPENROUTER_API_KEY=sk-or-your-actual-key
```

---

## Running the Application

### Method 1: Maven (Simplest)

```bash
cd king-ai

# Build
mvn clean install

# Run
mvn spring-boot:run
```

**Access:** http://localhost:8080

### Method 2: Run JAR Directly

```bash
cd king-ai

# Build JAR
mvn clean package

# Run JAR
java -jar target/king-ai-1.0.0.jar
```

### Method 3: Docker (Recommended)

```bash
# Build image
docker build -t king-ai .

# Run container
docker run -e OPENROUTER_API_KEY="sk-or-your-key" -p 8080:8080 king-ai

# Or with docker-compose
docker-compose up -d
```

### Method 4: IDE (IntelliJ IDEA / VS Code)

**IntelliJ IDEA:**
1. Open project
2. Right-click `KingAiApplication.java`
3. Select "Run 'KingAiApplication'"

**VS Code:**
1. Install "Extension Pack for Java"
2. Open command palette (Ctrl+Shift+P)
3. Type "Java: Run Maven build"
4. Choose "spring-boot:run"

---

## Testing the Application

### 1. Check Health Endpoint
```bash
curl http://localhost:8080/api/health
```

Expected response:
```json
{
  "status": "Kingdom is online and operational"
}
```

### 2. Test Chat API
```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{
    "model": "anthropic/claude-3.5-haiku-20241022",
    "messages": [
      {
        "role": "user",
        "content": "Hello!"
      }
    ]
  }'
```

### 3. Use Web Interface
1. Open http://localhost:8080
2. Type a message
3. Select a model from dropdown
4. Click send (➤ button)

---

## Project Structure Explained

```
king-ai/
│
├── src/main/java/com/kingai/
│   ├── KingAiApplication.java
│   │   └─ Main Spring Boot application class
│   │      Configures CORS and starts the server
│   │
│   ├── controller/
│   │   └── ChatController.java
│   │       ├─ POST /api/chat → Handles chat requests
│   │       └─ GET /api/health → Health check endpoint
│   │
│   ├── service/
│   │   └── OpenRouterService.java
│   │       └─ Makes HTTP calls to OpenRouter API
│   │          Handles request/response serialization
│   │
│   └── dto/
│       ├── ChatRequest.java
│       │   └─ Serializes incoming chat messages
│       │
│       └── ChatResponse.java
│           └─ Formats OpenRouter API response
│
├── src/main/resources/
│   ├── application.yml
│   │   └─ Spring Boot configuration
│   │      Database, API key, port settings
│   │
│   └── static/
│       └── index.html
│           └─ Frontend UI (served at root)
│              Single-page application
│              Model selector with 9 AI models
│
├── pom.xml
│   └─ Maven dependencies and build config
│      Spring Boot, Jackson, Lombok, etc.
│
└── Dockerfile & docker-compose.yml
    └─ Container configuration for deployment
```

---

## Configuration Reference

### application.yml Options

```yaml
server:
  port: 8080                    # Change port here
  servlet:
    context-path: /            # Root path

spring:
  application:
    name: King AI
  web:
    resources:
      static-locations: classpath:/static/

openrouter:
  api:
    key: ${OPENROUTER_API_KEY}  # Gets from environment

logging:
  level:
    root: INFO
    com.kingai: DEBUG           # Change to DEBUG for logs
```

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `OPENROUTER_API_KEY` | Required | Your API key |
| `SERVER_PORT` | 8080 | Server port |
| `SPRING_PROFILE` | dev | dev/prod |

---

## Common Commands

### Building

```bash
# Clean build
mvn clean

# Compile only
mvn compile

# Run tests
mvn test

# Build JAR
mvn package

# Install locally
mvn install
```

### Running

```bash
# Development mode with hot reload
mvn spring-boot:run

# With specific port
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"

# With debug logging
mvn spring-boot:run -Dspring-boot.run.arguments="--logging.level.com.kingai=DEBUG"
```

### Docker

```bash
# Build image
docker build -t king-ai .

# Run image
docker run -e OPENROUTER_API_KEY="key" -p 8080:8080 king-ai

# View logs
docker logs <container-id>

# Stop container
docker stop <container-id>

# With docker-compose
docker-compose up -d      # Start
docker-compose logs -f    # View logs
docker-compose down       # Stop
```

---

## Troubleshooting

### Issue: "Command not found: java"
**Solution:**
```bash
# Check Java is installed
java -version

# If not installed:
# Ubuntu/Debian:
sudo apt-get install openjdk-17-jdk

# macOS:
brew install openjdk@17

# Windows:
# Download from https://adoptium.net
```

### Issue: "API key not recognized"
**Solution:**
```bash
# Verify key is set
echo $OPENROUTER_API_KEY

# If empty, set it:
export OPENROUTER_API_KEY="sk-or-your-key"

# Restart application
mvn spring-boot:run
```

### Issue: "Port 8080 already in use"
**Solution:**
```bash
# Kill process on port 8080
# Linux/macOS:
lsof -i :8080 | grep LISTEN | awk '{print $2}' | xargs kill -9

# Windows:
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Or use different port:
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"
```

### Issue: "Cannot find symbol" errors
**Solution:**
```bash
# Update Maven
mvn -v

# Clean and rebuild
mvn clean install -U

# If IDE, refresh dependencies:
# IntelliJ: File → Invalidate Caches → Restart
# VS Code: Java: Import Java Projects → Select folder
```

### Issue: "Failed to construct HttpClient"
**Solution:**
- Update Java to 17+
- Check internet connection
- Verify OpenRouter API is not down

### Issue: "JSON parse error"
**Solution:**
- Check API response format in OpenRouterService.java
- Add logging to see actual response:

```java
System.out.println("Response: " + response.body());
```

---

## Performance Optimization

### For Better Performance:

1. **Use lightweight models:**
   ```
   anthropic/claude-3.5-haiku-20241022  (Fastest)
   google/gemini-2.5-flash              (Fast)
   ```

2. **Enable compression:**
   ```yaml
   server:
     compression:
       enabled: true
       min-response-size: 1024
   ```

3. **Adjust timeouts:**
   ```yaml
   server:
     servlet:
       session:
         timeout: 30m
   ```

4. **Monitor with logs:**
   ```yaml
   logging:
     level:
      com.kingai: DEBUG
   ```

---

## Security Checklist

- [ ] API key stored in environment variable (not in code)
- [ ] HTTPS enabled in production
- [ ] CORS restricted to allowed domains
- [ ] Input validation enabled
- [ ] Rate limiting configured
- [ ] Logs don't contain sensitive data
- [ ] Dependencies kept up to date

---

## Next Steps

1. ✅ Install prerequisites
2. ✅ Get OpenRouter API key
3. ✅ Clone/setup project
4. ✅ Run application
5. 📝 Customize models in frontend
6. 🚀 Deploy to cloud (Heroku, AWS, etc.)
7. 📊 Monitor usage and costs

---

## Support & Resources

- **OpenRouter Docs:** https://openrouter.ai/docs
- **Spring Boot Docs:** https://spring.io/projects/spring-boot
- **Maven Docs:** https://maven.apache.org/
- **Docker Docs:** https://docs.docker.com/

---

**Questions? Issues? Reach out to Tausif Islam**

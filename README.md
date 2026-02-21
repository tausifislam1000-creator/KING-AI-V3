# King AI - Royal AI Assistant
**Built by Tausif Islam** | Powered by OpenRouter API

## 🏰 Project Overview

King AI is a full-stack web application that provides an elegant chat interface with support for multiple AI models via OpenRouter. The frontend features a luxury dark theme with gold accents, and the backend is built with Spring Boot for reliability and scalability.

### Features
- 👑 Beautiful, responsive UI with animated elements
- 🎭 Support for 9 different AI models (Claude, GPT, Deepseek, Gemini, Qwen)
- 💬 Real-time chat with conversation history
- 📊 Session statistics and message counter
- 🔄 One-click chat history clear
- 🎯 Model selection with dropdown selector
- ⚡ Fast, lightweight architecture
- 🛡️ CORS-enabled REST API

---

## 📋 Prerequisites

### Backend Requirements
- **Java 17** or higher
- **Maven 3.6+** or **Gradle 7.0+**
- **OpenRouter API Key** (Get free at: https://openrouter.ai)

### Frontend Requirements
- Modern web browser (Chrome, Firefox, Safari, Edge)
- No additional dependencies (vanilla JavaScript)

---

## 🚀 Quick Start

### 1. Get Your OpenRouter API Key

1. Visit [openrouter.ai](https://openrouter.ai)
2. Sign up for a free account
3. Copy your API key from the dashboard
4. Note: Free tier has credit limits; premium models may require paid account

### 2. Setup Backend

#### Option A: Using Maven

```bash
# Navigate to project root
cd king-ai

# Set environment variable with your API key
export OPENROUTER_API_KEY="sk-or-your-actual-key-here"

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

#### Option B: Using Docker (Recommended)

```bash
# Build Docker image
docker build -t king-ai .

# Run container with API key
docker run -e OPENROUTER_API_KEY="sk-or-your-actual-key-here" -p 8080:8080 king-ai
```

### 3. Access the Application

Open your browser and navigate to:
```
http://localhost:8080
```

---

## 📁 Project Structure

```
king-ai/
├── src/
│   ├── main/
│   │   ├── java/com/kingai/
│   │   │   ├── KingAiApplication.java       # Main Spring Boot app
│   │   │   ├── controller/
│   │   │   │   └── ChatController.java      # REST endpoints
│   │   │   ├── service/
│   │   │   │   └── OpenRouterService.java   # API integration
│   │   │   └── dto/
│   │   │       ├── ChatRequest.java         # Request model
│   │   │       └── ChatResponse.java        # Response model
│   │   └── resources/
│   │       ├── application.yml              # Configuration
│   │       └── static/
│   │           └── index.html               # Frontend
│   └── test/
│       └── java/com/kingai/...              # Unit tests
├── pom.xml                                   # Maven configuration
├── Dockerfile                                # Docker setup
└── README.md                                 # Documentation
```

---

## 🔧 Configuration

### Environment Variables

Set these before running:

```bash
# Required
export OPENROUTER_API_KEY="sk-or-your-key"

# Optional
export SERVER_PORT="8080"
export SPRING_PROFILE="dev"  # or "prod"
```

### application.yml

The app reads from `src/main/resources/application.yml`:

```yaml
server:
  port: 8080

openrouter:
  api:
    key: ${OPENROUTER_API_KEY:your-api-key-here}
```

---

## 🎨 Supported AI Models

The dropdown includes 9 models:

| Model | Display Name | Provider | Type |
|-------|------------|----------|------|
| anthropic/claude-3.5-haiku-20241022 | C-3.5 haiku | Anthropic | Fast |
| anthropic/claude-3.5-sonnet-20240620 | C-3.5 sonnet | Anthropic | Balanced |
| anthropic/claude-3-sonnet | C-3 sonnet | Anthropic | Legacy |
| deepseek/deepseek-r1-0528:free | Deepseek-R1 | Deepseek | Reasoning |
| google/gemini-2.5-flash-preview-09-2025 | Gemini-2.5flash | Google | Fast |
| qwen/qwen3-vl-30b-a3b-thinking | Qwen-3 | Alibaba | Multimodal |
| openai/gpt-4.5-preview | GPT-4.5 | OpenAI | Advanced |
| openai/chatgpt-4o-latest | CHATGPT-4o | OpenAI | Latest |
| openai/codex-mini | Codex-Mini | OpenAI | Code |

**Add more models:** Edit the `<select>` in `index.html` and the model will work immediately!

---

## 📡 API Endpoints

### POST `/api/chat`
Send a message and get a response.

**Request:**
```json
{
  "model": "anthropic/claude-3.5-sonnet-20240620",
  "messages": [
    {"role": "user", "content": "Hello!"}
  ]
}
```

**Response:**
```json
{
  "choices": [
    {
      "message": {
        "role": "assistant",
        "content": "Greetings, Your Majesty!"
      }
    }
  ]
}
```

### GET `/api/health`
Check if the kingdom is operational.

**Response:**
```json
{
  "status": "Kingdom is online and operational"
}
```

---

## 🛠️ Development

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=ChatControllerTest
```

### Debug Mode

```bash
# Run with debug logging
export SPRING_PROFILE=dev
mvn spring-boot:run -Dspring-boot.run.arguments="--debug"
```

### Hot Reload

The application includes DevTools for hot reload during development:

```bash
mvn spring-boot:run
```

Make changes to Java files, and they'll auto-reload!

---

## 🐛 Troubleshooting

### "API Key not found" Error
```bash
# Make sure to set the environment variable
export OPENROUTER_API_KEY="your-key-here"

# Verify it's set
echo $OPENROUTER_API_KEY
```

### "Connection refused" Error
```bash
# Check if port 8080 is already in use
lsof -i :8080

# Use a different port
export SERVER_PORT=9090
mvn spring-boot:run
```

### "Invalid response from OpenRouter"
- Check if your API key is valid
- Verify you have credits on your OpenRouter account
- Try a free model first (deepseek-r1 is free)

---

## 📦 Deployment

### Deploy to Heroku

```bash
# 1. Install Heroku CLI
# 2. Login
heroku login

# 3. Create app
heroku create king-ai

# 4. Set environment variable
heroku config:set OPENROUTER_API_KEY="your-key"

# 5. Deploy
git push heroku main

# View logs
heroku logs --tail
```

### Deploy to AWS

Using AWS Elastic Beanstalk:

```bash
# 1. Install EB CLI
# 2. Initialize
eb init -p java-17 king-ai

# 3. Set environment variables
eb setenv OPENROUTER_API_KEY="your-key"

# 4. Create and deploy
eb create king-ai-env
eb deploy
```

### Deploy with Docker Compose

```yaml
version: '3.8'

services:
  king-ai:
    build: .
    ports:
      - "8080:8080"
    environment:
      - OPENROUTER_API_KEY=${OPENROUTER_API_KEY}
    restart: unless-stopped
```

Run with:
```bash
docker-compose up -d
```

---

## 🔒 Security Notes

1. **Never commit API keys** - Use environment variables
2. **Use HTTPS in production** - Enable SSL/TLS
3. **Validate input** - Frontend validates message length
4. **Rate limiting** - Consider adding rate limits for production
5. **CORS** - Currently allows all origins; restrict in production:

```java
registry.addMapping("/api/**")
    .allowedOrigins("https://yourdomain.com")
```

---

## 📈 Performance Tips

1. **Use faster models** for real-time interactions (Claude Haiku)
2. **Implement caching** for repeated queries
3. **Enable compression** in Spring Boot:
   ```yaml
   server:
     compression:
       enabled: true
   ```
4. **Monitor API costs** - Set limits in OpenRouter dashboard

---

## 🤝 Contributing

Want to improve King AI?

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

---

## 📄 License

This project is open source and available under the MIT License.

---

## 👨‍💻 Creator

**Tausif Islam**
- Twitter: [@TausifIslam](https://twitter.com/TausifIslam)
- GitHub: [@TausifIslam](https://github.com/TausifIslam)
- Website: [tausifislam.com](https://tausifislam.com)

---

## 🙏 Acknowledgments

- **OpenRouter** for excellent API
- **Spring Boot** community
- **Claude AI** for assistance

---

## 📞 Support

For issues and questions:
- Open an GitHub issue
- Check existing documentation
- Contact: support@kingai.dev

---

**Made with ♛ by Tausif Islam**

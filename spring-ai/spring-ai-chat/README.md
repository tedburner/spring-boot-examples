## 使用大模型
使用 Ollama 进行本地部署大模型
```bash
# CPU 或者 Nvidia GPU 
docker pull ollama/ollama

# AMD GPU
docker pull ollama/ollama:rocm

# 启动 ollama
docker run --name ollama -d -v D:\Document\docker\ollama:/root/.ollama -p 11434:11434 ollama/ollama

# 查看已安装模型
docker exec -it ollama ollama list

# deepseek-r1:8b
## 拉取并运行模型
ollama pull deepseek-r1:8b
ollama run deepseek-r1:8b

ollama pull qwen3:8b
ollama run qwen3:8b
```
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

# 拉取模型
docker exec -it ollama ollama pull deepseek-r1:8b

#运行模型
docker exec -it ollama ollama run deepseek-r1:8b
```
FROM python:3.12-slim

LABEL authors="Aryan kumar"

WORKDIR /frontend

COPY requirements.txt .

RUN pip install -r requirements.txt

COPY app.py .

CMD ["streamlit", "run", "app.py", "--server.address=0.0.0.0", "--server.port=8501"]


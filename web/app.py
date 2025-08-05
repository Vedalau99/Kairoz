from flask import Flask
from prometheus_client import Counter, generate_latest
import os

app = Flask(__name__)

REQUEST_COUNT = Counter("web_requests_total", "Total number of requests")

@app.route("/")
def home():
    REQUEST_COUNT.inc()
    return "Welcome to Kairoz Web Service!"

@app.route("/health")
def health():
    return "OK", 200

@app.route("/metrics")
def metrics():
    return generate_latest(), 200, {'Content-Type': 'text/plain; charset=utf-8'}

if __name__ == "__main__":
    port = int(os.environ.get("PORT", 5000))
    app.run(host="0.0.0.0", port=port)

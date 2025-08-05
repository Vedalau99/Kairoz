import time
import random

def do_work():
    print("Kairoz Worker started.")
    while True:
        task_id = random.randint(1000, 9999)
        print(f"[Worker] Processing task #{task_id}")
        time.sleep(5)

if __name__ == "__main__":
    do_work()

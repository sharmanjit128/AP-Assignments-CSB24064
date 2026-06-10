import random
from typing import List, Dict, Set
from functools import reduce
from collections import defaultdict


LogEntry = Dict[str, str | float]


def generate_dummy_logs(num_records: int) -> List[LogEntry]:
    actions = ["browser", "ide", "compiler", "terminal", "documentation", "forum"]
    num_students = max(5, num_records // 10)
    users = [f"roll_{i:03d}" for i in range(1, num_students + 1)]


    return [
        {
            "user": random.choice(users),
            "action": random.choice(actions),
            "duration": round(random.uniform(1.0, 180.0), 2)
        }
        for _ in range(num_records)
    ]


def total_time_per_user(logs: List[LogEntry]) -> Dict[str, float]:
    def reducer(acc: Dict[str, float], log: LogEntry) -> Dict[str, float]:
        acc[log["user"]] = acc.get(log["user"], 0.0) + log["duration"]
        return acc
    return reduce(reducer, logs, {})


def most_active_users(logs: List[LogEntry], k: int) -> List[str]:
    totals = total_time_per_user(logs)
    sorted_users = sorted(totals.keys(), key=lambda user: totals[user], reverse=True)
    return sorted_users[:k]


def unique_actions(logs: List[LogEntry]) -> Set[str]:
    return {log["action"] for log in logs}


if __name__ == "__main__":
    print("=== Activity Log Analyzer ===")


    try:
        num_logs = int(input("Enter number of log records to generate: "))
        if num_logs <= 0:
            print("Please enter a positive number.")
            exit()
    except ValueError:
        print("Invalid input. Please enter an integer.")
        exit()


    logs = generate_dummy_logs(num_logs)


    print("\nSample Logs:")
    for log in logs[:3]:
        print(log)


    actions = unique_actions(logs)
    print("\nUnique Actions:")
    print(actions)


    k = min(3, len(logs))
    top_users = most_active_users(logs, k)
    totals = total_time_per_user(logs)


    print(f"\nTop {k} Most Active Users:")
    for i, user in enumerate(top_users, 1):
        print(f"{i}. {user} -> {totals[user]:.2f} minutes")


    print("\nAnalysis Complete.")


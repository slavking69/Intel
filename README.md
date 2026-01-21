**Intel** is a high-performance Java-based OSINT tool designed to scout for usernames across multiple social media platforms simultaneously. Built with a focus on speed, stealth, and professional-grade concurrency.

## Features
* **Multi-Threaded Engine:** Uses a `FixedThreadPool` (10 workers) to scan platforms in parallel rather than one-by-one.
* **Smart Status Handling:** Color-coded terminal output (ANSI) for instant identification of results (Found, Blocked, Redirected, or Not Found).
* **Stealth Logic:** Implements `HEAD` requests to minimize network footprint and custom `User-Agent` headers to bypass basic bot detection.
* **Modern Java Stack:** Built using the `java.net.http.HttpClient` (Java 11+) for robust networking.



## Prerequisites
* **Java Development Kit (JDK) 11 or higher**
* **Linux/Unix environment** (Optimized for Linux Mint/Ubuntu)

## Installation & Setup
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/YOUR_USERNAME/Intel.git](https://github.com/YOUR_USERNAME/Intel.git)
   cd Intel

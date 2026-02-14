# Background Job Processing Infrastructure – Problem Statement

APIs should be fast and reliable. Slow, failure-prone or non-critical to user response tasks must not be done inside API requests as it increases latency, causes partial failures, tightly couples user flow with unreliable logic.

Examples of such tasks include:
- Sending emails or notifications
- Generating reports
- Data cleanup and batch processing
- Video or file processing
- Third-party API calls

Executing these tasks synchronously blocks client requests, reduces throughput,
and increases failure impact.

This project aims to build a **generic background job processing infrastructure**
that allows services to submit jobs asynchronously, execute them reliably,
and handle failures.

The system must be resilient to crashes, support retries, scale horizontally,
and provide operational visibility.

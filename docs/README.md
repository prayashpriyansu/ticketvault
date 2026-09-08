# TicketVault working context

TicketVault is a backend engineering learning project using Java, Spring Boot,
Maven, PostgreSQL, Docker, and Git/GitHub. Start with a modular monolith and
introduce complexity only when a concrete problem calls for it.

## How we work

- The learner writes application code. The assistant scopes assignments, explains
  relevant concepts, reviews completed work, and suggests corrections.
- Implement directly only when the learner explicitly requests it.
- Keep phases around 1–2 days and individual assignments around 30 minutes–2 hours.
- Plan only the current phase and immediate next assignment. Avoid a large roadmap.
- Inspect repository evidence before planning, reviewing, or diagnosing.
- Explain important design choices briefly. Let the learner participate.
- Add tests with behaviour, run relevant checks, and review the final diff.
- At each handoff, update the current assignment with progress, decisions, review
  findings, and known limitations. Do not mark unverified work complete.

## Current phase: backend foundation

The phase covers Spring Boot and Maven, PostgreSQL through Docker Compose,
application database configuration, explicit Flyway migrations, basic user
identity, a replaceable `CurrentUserProvider`, a tiny `/api/me` endpoint,
predictable HTTP errors, tests, and a usable README.

Temporary identity uses `X-User-Id: <UUID>`. Application code should obtain
identity through the provider rather than repeatedly reading request headers.
Missing headers, malformed UUIDs, and unknown users need deliberate behaviour
and tests. Decide those details when that assignment starts.

Real authentication is outside this phase: no passwords, JWT, sessions, OAuth,
roles, permissions, or Spring Security authentication.

Use Flyway for schema changes, not Hibernate schema generation. Where database
behaviour matters, test against PostgreSQL. Never commit secrets; document local
development credentials and their limitations when adding database setup.

## Git and review

Use an issue → feature/fix branch → small commits → push → pull request →
self-review → merge workflow. For a new repository, first establish an initial
documentation commit on `main` so a feature branch has a base.

Before closing an assignment, check behaviour, relevant tests, naming, accidental
or unrelated changes, dead code, unnecessary abstractions, secrets, and generated
files. Record limitations and suggest a Conventional Commit message. Never
rewrite history or perform destructive Git operations without an explicit request.

GitHub issue creation is welcome when a repository is connected. Record actual
issue and PR links; do not claim remote work happened without verification.

Repository: [prayashpriyansu/ticketvault](https://github.com/prayashpriyansu/ticketvault).
The assistant can create scoped assignment issues here at the learner's request.

Current assignment: [Bootstrap the application](phase-01-foundation.md).

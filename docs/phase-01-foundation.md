# Phase 01: backend foundation

Status: in progress; verification updated on 2026-09-08. Spring Boot scaffold and
Maven wrapper are committed in `6129b81`. Local `main` matches `origin/main`.
Git author name/email are configured. Java and javac report 26.0.2.

### First review findings

- Resolved: installing JDK 26 fixed the earlier Java target mismatch.
  `./mvnw -B -ntp test` passed: 1 test, 0 failures/errors/skips.
  `./mvnw -B -ntp spring-boot:run` started Tomcat on port 8080 successfully;
  the reviewer stopped the process afterward.
- Resolved: Git author settings and the first pushed commit are present.
- Resolved: the assistant expanded the learner's root README with selected
  versions and test/run commands, at the learner's request.
- Add local secret-file exclusions such as `.env` and `.env.*`, retaining
  `.env.example` if one is added later. No secrets were found in inspected files.
- The scaffold was committed directly on `main`. Preserve that history; use
  `docs/bootstrap-readme` and a PR linked to issue #1 for the remaining cleanup.
  No commits or branches were created by the reviewer.
- Optional cleanup: simplify the repeated `com.ticketvault.ticketvault` package
  to `com.ticketvault`, updating both main and test source paths and declarations.

Tracking: [GitHub issue #1 — Bootstrap Spring Boot application](https://github.com/prayashpriyansu/ticketvault/issues/1).

## Assignment 01: a runnable application

Timebox: 60–90 minutes. Stop after this assignment and return for review.

Outcome: a minimal Spring Boot application that starts, has a passing context
test, and can be built with the committed Maven wrapper.

### Implementation

1. Initialize Git with `git init -b main`. Commit the planning documents as
   `docs: record learning workflow and foundation assignment`.
2. Use the existing GitHub repository and issue #1 linked above. Add
   `git@github.com:prayashpriyansu/ticketvault.git` as `origin` if absent and push
   `main`.
3. Create `chore/bootstrap-application` from `main`.
4. Generate a Maven/Java project using Spring Initializr. The selected versions
   are Spring Boot 4.1.1 and Java 26. Record the versions
   in the README. Use `com.ticketvault` as the group and `ticketvault` as the
   artifact. Add Spring Web only for now; keep the generated test support.
5. Put the generated project at the repository root, preserving `docs/`. Keep
   the Maven wrapper and the generated application context test. Ensure
   `.gitignore` excludes `target/`, IDE-local files, and local secret files.
6. Add a short root README with prerequisites, `./mvnw test`, and
   `./mvnw spring-boot:run` (Windows users can use `mvnw.cmd`).
7. Run the test and start the application. Save any failure output for review.
   A 404 at `/` is expected: there is no endpoint yet.

Do not add database dependencies, Docker, migrations, identity, or endpoints in
this assignment. Those belong to later assignments within this same phase.

### What to understand

The Maven wrapper lets contributors use the project's selected Maven version.
The context test checks whether Spring can create the application and wire its
beans; it does not yet prove any HTTP or database behaviour.

### Acceptance checklist

- [x] Maven wrapper, application entry point, and context test are committed.
- [x] `./mvnw test` passes.
- [x] `./mvnw spring-boot:run` starts successfully; stop it after checking.
- [x] README names the selected Java/Spring Boot versions and runnable commands.
- [ ] No secrets, generated build output, or unrelated files are staged.
- [ ] Review `git diff --cached` before each commit and the full PR diff afterward.
- [ ] Push the branch and open a PR linked to the issue; leave it open for review.

Suggested implementation commit: `chore: bootstrap Spring Boot application`.

### Return for review

Share the issue/PR links, test result, and anything that confused you. The assistant
will inspect the code and diff, explain findings, and hand back small corrections
for you to implement. Merge after the review findings are resolved and checks pass.

### Decisions and limitations

- The assistant created issue #1; the learner generated the application scaffold.
- Spring Boot 4.1.1 and Java 26.0.2 passed the checks above. The test emits a
  non-failing Mockito self-attachment warning; agent configuration remains a
  future cleanup item.
- Database and identity behaviour are intentionally not implemented yet.

## Assignment 02: run PostgreSQL locally

Timebox: 45–90 minutes. The learner asked to move on from documentation; the
assistant now handles the short README updates. Bootstrap Git cleanup can follow
without blocking this exercise. Do not rewrite the existing pushed commit.

Create branch `feat/local-postgres`. Write `compose.yaml` yourself with one
PostgreSQL service, an explicitly selected image version, a local database/user,
localhost-only port exposure, and a named volume for persistence. Use an ignored
`.env` for local credentials and commit `.env.example` with clearly disposable
development values. Add the relevant ignore rules alongside this work.

Acceptance checks:

- [ ] `docker compose config --quiet` validates the configuration.
- [ ] `docker compose up -d` starts PostgreSQL.
- [ ] Connect using `psql` inside the container and run `SELECT current_database(), current_user;`.
- [ ] Create a small scratch table and insert a row. Run `docker compose down`,
  start again, and confirm the row remains. Do not use `down -v`: that removes
  the volume and its data. Drop the scratch table when finished.
- [ ] Review the diff: `.env` is ignored and no real credentials are committed.

Learning focus: the container can be replaced while its named volume preserves
the database. Container startup alone does not prove the database accepts SQL;
the connection check does. Local example credentials are not production secrets.

Stop after PostgreSQL works. Spring datasource configuration and Flyway are the
next assignment, after this review. Return with your Compose file and check
results. Suggested commit: `feat: add local PostgreSQL with Docker Compose`.

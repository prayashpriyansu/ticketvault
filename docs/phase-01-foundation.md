# Phase 01: backend foundation

Status: in progress; first review on 2026-09-08. Spring Boot scaffold and Maven
wrapper are present. Git is initialized on `main`, with the correct HTTPS origin,
but there are no commits yet.

### First review findings

- `./mvnw test` failed during compilation: `release version 26 not supported`.
  The POM targets Java 26; the review terminal runs Java 25. Align the project
  target and development JDK, then rerun tests and verify application startup.
- Git author name/email are not configured in the review environment. Configure
  them before the first commit (Windows Git may have separate settings).
- Add the root README with selected versions and test/run commands.
- Add local secret-file exclusions such as `.env` and `.env.*`, retaining
  `.env.example` if one is added later. No secrets were found in inspected files.
- Make the initial documentation commit, then create the bootstrap branch before
  committing application files. No commits or branches were created by the reviewer.
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
4. Generate a Maven/Java project using Spring Initializr. Choose a stable Spring
   Boot release and a compatible LTS JDK available locally. Record the versions
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

- [ ] Maven wrapper, application entry point, and context test are committed.
- [ ] `./mvnw test` passes.
- [ ] `./mvnw spring-boot:run` starts successfully; stop it after checking.
- [ ] README names the selected Java/Spring Boot versions and runnable commands.
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
- The scaffold selects Spring Boot 4.1.1 and Java 26; the Java mismatch above
  remains unresolved. Tests did not reach execution and startup was not verified.
- Database and identity behaviour are intentionally not implemented yet.

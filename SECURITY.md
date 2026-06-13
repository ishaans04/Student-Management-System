# Security Policy

## Supported Versions

This project is maintained as a student/learning project. Security updates are applied to the latest version available in the main branch.

| Version | Supported |
| --- | --- |
| Latest main branch | Yes |
| Older versions | No |

## Reporting a Vulnerability

If you discover a security vulnerability, please report it privately instead of opening a public issue.

When reporting a vulnerability, include:

- A clear description of the issue
- Steps to reproduce the problem
- The affected file or component
- Any potential impact
- Suggested fixes, if available

Do not include real passwords, database credentials, personal paths, or other sensitive information in reports.

## Security Notes

- Update the MySQL username and password in `src/db/DBConnection.java` before running the project locally.
- Do not commit real credentials to the repository.
- Use strong local database credentials.
- Restrict database access to trusted users and local development environments.
- Keep MySQL Connector/J and other dependencies updated.

## Disclosure

Please allow reasonable time for review and fixes before publicly disclosing any vulnerability.

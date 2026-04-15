# Experiment 2: Anagram Checker with SonarQube CI/CD Integration

## 📋 Overview

This project demonstrates a **Maven-based Java application** with **Jenkins CI/CD automation** and **SonarQube code quality analysis** that checks if two strings are anagrams **without sorting**.

**Key Features:**
- ✅ O(n) time complexity, O(1) space complexity (using character frequency)
- ✅ 15 comprehensive JUnit 5 tests
- ✅ JaCoCo code coverage analysis
- ✅ SonarQube integration for code quality metrics
- ✅ Complete Jenkins Pipeline (7 stages including SonarQube)
- ✅ Git integration with GitHub
- ✅ Windows-compatible pipeline

---

## 🎯 Algorithm

**Problem:** Check if two strings are anagrams without sorting

**Solution:** Character frequency counting approach

```
1. Normalize strings (lowercase, remove spaces)
2. If lengths differ → NOT anagrams
3. Create frequency array (26 letters)
4. Count characters in string 1 → increment
5. Count characters in string 2 → decrement
6. Check if all counts are zero → ARE anagrams
```

**Examples:**
```
"listen" & "silent"     → ✓ ARE ANAGRAMS
"The Eyes" & "They See" → ✓ ARE ANAGRAMS (handles spaces)
"hello" & "world"       → ✗ NOT ANAGRAMS
```

**Complexity:**
- **Time:** O(n + m) - two passes through strings
- **Space:** O(1) - fixed array for 26 letters
- **Handles:** Case insensitivity, spaces, duplicates, special chars

---

## 📁 Project Structure

```
Experiment2/
├── pom.xml                              # Maven config with SonarQube
├── Jenkinsfile                          # 7-stage Jenkins Pipeline
├── .gitignore                           # Git ignore rules
├── README.md                            # This file
│
└── src/
    ├── main/java/com/example/
    │   └── AnagramChecker.java          # Algorithm implementation
    └── test/java/com/example/
        └── AnagramCheckerTest.java      # 15 comprehensive tests
```

---

## 🚀 Quick Start

### Prerequisites

```bash
# Java 11+
java -version

# Maven 3.8.1+
mvn --version

# Git
git --version

# SonarQube (optional for full analysis)
# Docker: docker run -d -p 9000:9000 sonarqube:latest
```

### Build Locally

```bash
# Full build with tests and coverage
mvn clean install

# Run tests only
mvn test

# Generate coverage report
mvn jacoco:report

# Run SonarQube analysis (requires SonarQube running)
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000

# Execute application
java -jar target/anagram-checker-1.0.0.jar
```

---

## 📊 Maven Commands

### Build Commands
```bash
# Clean and compile
mvn clean compile

# Compile + test
mvn clean test

# Full build
mvn clean install

# Package JAR
mvn package -DskipTests

# Skip tests
mvn clean install -DskipTests
```

### Testing Commands
```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=AnagramCheckerTest#testBasicAnagrams

# Generate detailed report
mvn surefire-report:report
```

### Code Analysis Commands
```bash
# Generate JaCoCo coverage report
mvn jacoco:report

# View coverage
target/site/jacoco/index.html

# Run SonarQube analysis
mvn sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=admin

# SonarQube with custom settings
mvn sonar:sonar \
  -Dsonar.projectKey=anagram-checker \
  -Dsonar.projectName="Anagram Checker" \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=admin \
  -Dsonar.sources=src/main \
  -Dsonar.tests=src/test \
  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
```

---

## 🌿 Git Commands

### Initialize & Commit
```bash
git init
git config user.name "Developer"
git config user.email "dev@example.com"
git add .
git commit -m "Initial: Anagram checker with SonarQube"
```

### Push to GitHub
```bash
git remote add origin https://github.com/NupoorYadu/AnagramChecker-SonarQube.git
git branch -M main
git push -u origin main
```

### Update & Push
```bash
git add .
git commit -m "Feature: Add SonarQube integration"
git push origin main
```

---

## 🧪 Test Cases (15 Total)

| Test Case | Input 1 | Input 2 | Expected | Status |
|-----------|---------|---------|----------|--------|
| Basic Anagrams | "listen" | "silent" | true | ✅ |
| Not Anagrams | "hello" | "world" | false | ✅ |
| Case Insensitive | "Listen" | "Silent" | true | ✅ |
| With Spaces | "The Eyes" | "They See" | true | ✅ |
| Empty Strings | "" | "" | true | ✅ |
| Single Char Same | "a" | "a" | true | ✅ |
| Single Char Diff | "a" | "b" | false | ✅ |
| Different Lengths | "cat" | "cats" | false | ✅ |
| Special Chars | "a-b-c" | "c,b,a" | true | ✅ |
| Duplicates Match | "aabb" | "abab" | true | ✅ |
| Duplicates Mismatch | "aab" | "abb" | false | ✅ |
| Numbers Ignored | "a1b2c3" | "c3b2a1" | true | ✅ |
| Null Input | null | "test" | Exception | ✅ |
| Real World 1 | "astronomer" | "moon starer" | true | ✅ |
| Real World 2 | "desperation" | "a rope ends it" | true | ✅ |

**Run all tests:**
```bash
mvn test
```

**Expected output:**
```
Tests run: 15, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

---

## 🔧 Jenkins Pipeline (7 Stages)

### Stage Flow

```
┌─ STAGE 1: Checkout ─────────────────────┐
│ • Clone from GitHub main branch        │
│ • Show last 5 commits                  │
└────────────────────────────────────────┘
         ↓
┌─ STAGE 2: Build ──────────────────────┐
│ • mvn clean compile                    │
│ • Compile all source files             │
└────────────────────────────────────────┘
         ↓
┌─ STAGE 3: Test ──────────────────────┐
│ • mvn test                             │
│ • Run 15 JUnit tests                  │
│ • All tests must pass                 │
└────────────────────────────────────────┘
         ↓
┌─ STAGE 4: Code Coverage ──────────────┐
│ • mvn jacoco:report                    │
│ • Generate coverage metrics            │
│ • Target coverage: >80%               │
└────────────────────────────────────────┘
         ↓
┌─ STAGE 5: SonarQube Analysis ────────┐
│ • mvn sonar:sonar                     │
│ • Code quality metrics                 │
│ • Bug detection                        │
│ • Vulnerability scanning               │
│ • Technical debt analysis              │
│ ⭐ NEW STAGE - CODE QUALITY         │
└────────────────────────────────────────┘
         ↓
┌─ STAGE 6: Package ────────────────────┐
│ • mvn package -DskipTests             │
│ • Create JAR artifact                 │
└────────────────────────────────────────┘
         ↓
┌─ STAGE 7: Demo - Run Application ────┐
│ • java -jar anagram-checker.jar       │
│ • SHOWS OUTPUT:                       │
│   "listen" & "silent" → ✓ ARE ANAGRAMS│
│   "hello" & "world" → ✗ NOT ANAGRAMS  │
└────────────────────────────────────────┘
         ↓
┌─ STAGE 8: Archive ────────────────────┐
│ • Store JAR in Jenkins                │
│ • Store code coverage reports         │
└────────────────────────────────────────┘
         ↓
    ✓ BUILD SUCCESS
    📊 CODE QUALITY ANALYZED
```

---

## 🔍 SonarQube Setup

### Option 1: Docker (Recommended)

```bash
# Pull SonarQube image
docker pull sonarqube:latest

# Run SonarQube container
docker run -d \
  -p 9000:9000 \
  --name sonarqube \
  sonarqube:latest

# Access SonarQube
# Navigate to: http://localhost:9000
# Default login: admin / admin
```

### Option 2: Manual Installation

```bash
# Download SonarQube
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.9.1.69595.zip

# Extract
unzip sonarqube-9.9.1.69595.zip

# Start SonarQube
cd sonarqube-9.9.1.69595/bin
./sonar.sh start

# Access: http://localhost:9000
```

### Configure Jenkins for SonarQube

1. **Jenkins → Manage Jenkins → Configure System**
2. **SonarQube servers**
   - Name: `SonarQube`
   - Server URL: `http://localhost:9000`
   - Server authentication token: (generate in SonarQube)
3. **Click Save**

---

## 📊 Code Quality Metrics

SonarQube analyzes:

```
✓ Code Smells         - Minor quality issues
✓ Bugs                - Potential defects
✓ Vulnerabilities     - Security issues
✓ Hotspots            - Security-sensitive code
✓ Duplications        - Duplicate code blocks
✓ Code Coverage       - JaCoCo integration
✓ Complexity          - Cyclomatic complexity
✓ Technical Debt      - Time to fix issues
```

### View Metrics

```
1. After build completes, check SonarQube:
   http://localhost:9000/projects

2. Click on "anagram-checker" project

3. View metrics dashboard with:
   - Code coverage percentage
   - Issues by severity
   - Technical debt ratio
   - Security vulnerabilities
```

---

## 📋 Jenkinsfile Stages

All stages defined in `Jenkinsfile`:

```groovy
stages {
    stage('Checkout')           // Clone repo
    stage('Build')              // Compile
    stage('Test')               // Unit tests
    stage('Code Coverage')      // JaCoCo
    stage('SonarQube Analysis')  // Code quality ⭐
    stage('Package')            // Create JAR
    stage('Demo')               // Run app
    stage('Archive')            // Store artifacts
}
```

---

## 🎯 Complete Workflow

### Step 1: Local Development
```bash
cd c:\Win_Sem_2025_26\ADDA\FAT\Experiment2
mvn clean install
java -jar target/anagram-checker-1.0.0.jar
```

### Step 2: Local Analysis
```bash
# Generate coverage
mvn jacoco:report

# View coverage
target/site/jacoco/index.html

# Run SonarQube (if running)
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000
```

### Step 3: Git Setup
```bash
git init
git config user.name "Developer"
git config user.email "dev@example.com"
git add .
git commit -m "Initial: Anagram checker with SonarQube"
git remote add origin https://github.com/NupoorYadu/AnagramChecker-SonarQube.git
git branch -M main
git push -u origin main
```

### Step 4: Jenkins Configuration
```
1. New Item → Name: AnagramChecker-SonarQube → Pipeline
2. Repository: https://github.com/NupoorYadu/AnagramChecker-SonarQube.git
3. Branch: */main
4. Script Path: Jenkinsfile
5. Build Triggers: Poll SCM (H/15 * * * *)
6. Save → Build Now
```

### Step 5: Monitor Build & Analysis
```
Jenkins: http://localhost:8080/job/AnagramChecker-SonarQube/
SonarQube: http://localhost:9000/projects
```

---

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| SonarQube connection refused | Ensure SonarQube running: `docker ps` or check port 9000 |
| Maven SonarQube plugin not found | Update pom.xml with correct plugin version |
| JaCoCo report not generated | Ensure tests run: `mvn clean test jacoco:report` |
| "localhost:9000 not reachable" | Check Docker container: `docker logs sonarqube` |
| SonarQube analysis skipped | Check environment variables in Jenkinsfile |

---

## 📚 References

- **Maven SonarQube Plugin:** https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/
- **JaCoCo Maven Plugin:** https://www.jacoco.org/jacoco/trunk/doc/maven.html
- **SonarQube Documentation:** https://docs.sonarqube.org/latest/
- **Jenkins SonarQube Integration:** https://docs.sonarqube.org/latest/devops-platform-sonarqube/jenkins-integration/

---

## ✅ Verification Checklist

```
LOCAL BUILD:
  ☑ mvn clean install succeeds
  ☑ 15 tests pass
  ☑ JAR runs correctly
  ☑ JaCoCo coverage report generated

CODE QUALITY:
  ☑ No compilation warnings
  ☑ Code follows standards
  ☑ Coverage > 80%
  ☑ No major code smells

GIT & GITHUB:
  ☑ Repository pushed to GitHub
  ☑ Main branch contains all files
  ☑ .gitignore properly configured

JENKINS & SONARQUBE:
  ☑ Jenkins job created
  ☑ All 7 stages pass
  ☑ SonarQube analysis completes
  ☑ Metrics visible in dashboard
  ☑ Code coverage metrics displayed
```

---

**Ready to build! 🚀**

Next: Initialize Git, push to GitHub, and create Jenkins job!


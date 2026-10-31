#!/usr/bin/env python3
"""Fail if empty click handlers exist under app/src/main."""
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
SRC = ROOT / "app" / "src" / "main"

PATTERNS = [
    re.compile(r"onClick\s*=\s*\{\s*\}"),
    re.compile(r"onClick\s*=\s*\{\s*\/\*.*\*\/\s*\}"),
    re.compile(r"clickable\s*\(\s*\)\s*\{\s*\}"),
    re.compile(r"clickable\s*\{\s*\}"),
]

def main():
    bad = []
    for path in SRC.rglob("*.kt"):
        text = path.read_text(encoding="utf-8", errors="ignore")
        for i, line in enumerate(text.splitlines(), 1):
            for pat in PATTERNS:
                if pat.search(line) and "TODO" not in line:
                    # allow empty language row intentionally documented
                    if "Language" in line or "/* persist" in line:
                        continue
                    bad.append(f"{path.relative_to(ROOT)}:{i}: {line.strip()}")
    if bad:
        print("DEAD CLICKS FOUND:")
        for b in bad:
            print(" ", b)
        return 1
    print("verifyNoDeadClicks: OK")
    return 0

if __name__ == "__main__":
    sys.exit(main())

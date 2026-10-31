#!/usr/bin/env python3
"""Fail on VPN / health forbidden tokens; warn on sensitive financial words."""
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
SCAN = [ROOT / "app" / "src" / "main", ROOT / "docs"]

FORBIDDEN = [
    re.compile(r"VpnService", re.I),
    re.compile(r"BIND_VPN_SERVICE", re.I),
    re.compile(r"android\.net\.VpnService", re.I),
    re.compile(r"HealthConnect", re.I),
    re.compile(r"android\.permission\.health", re.I),
]

WARN = [
    re.compile(r"\bwallet\b", re.I),
    re.compile(r"\bloan\b", re.I),
    re.compile(r"\bcredit\b", re.I),
    re.compile(r"\binvest\b", re.I),
    re.compile(r"\bpayment\b", re.I),
    re.compile(r"\bbank\b", re.I),
]

def main():
    errors = []
    warnings = []
    for base in SCAN:
        if not base.exists():
            continue
        for path in base.rglob("*"):
            if path.suffix.lower() not in {".kt", ".xml", ".md", ".html", ".json"}:
                continue
            text = path.read_text(encoding="utf-8", errors="ignore")
            for i, line in enumerate(text.splitlines(), 1):
                for pat in FORBIDDEN:
                    if pat.search(line):
                        errors.append(f"{path.relative_to(ROOT)}:{i}: {line.strip()}")
                for pat in WARN:
                    if pat.search(line) and "no payment" not in line.lower() and "not a" not in line.lower():
                        warnings.append(f"{path.relative_to(ROOT)}:{i}: {line.strip()}")
    if warnings:
        print("WARN (review manually):")
        for w in warnings[:30]:
            print(" ", w)
    if errors:
        print("FORBIDDEN TOKENS:")
        for e in errors:
            print(" ", e)
        return 1
    print("verifyPlayGuard: OK")
    return 0

if __name__ == "__main__":
    sys.exit(main())

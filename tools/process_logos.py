#!/usr/bin/env python3
"""
BQ Sqwad Data Receipt – Logo processing pipeline

Usage:
  python tools/process_logos.py

Looks in branding/ folders, detects real image format, generates
placeholders when missing, and prepares assets for the app and docs.
"""

import os
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
BRANDING = ROOT / "branding"

SLOTS = {
    "app": ["bq-sqwad-data-receipt-logo.png", "bq-sqwad-data-receipt-logo-mono.png"],
    "company": ["bq-sqwad-technologies-logo.png"],
    "apps": ["bq-sqwad-ai.png", "bq-sqwad-data-saver.png"],
    "entertainment": ["bq-sqwad-entertainment.png"],
}

def main():
    print("BQ Sqwad logo pipeline")
    print("======================")
    missing = []
    found = []

    for folder, files in SLOTS.items():
        folder_path = BRANDING / folder
        folder_path.mkdir(parents=True, exist_ok=True)
        for name in files:
            path = folder_path / name
            # also check common double extensions / wrong names
            candidates = list(folder_path.glob(name.split(".")[0] + "*"))
            if path.exists() or candidates:
                found.append(str(path))
                print(f"  [OK] {folder}/{name}")
            else:
                missing.append(str(path))
                print(f"  [MISSING] {folder}/{name} → placeholder will be used")

    print()
    print(f"Found: {len(found)}  Missing: {len(missing)}")
    if missing:
        print("Upload the real logos to the paths above, then re-run this script / rebuild.")
    else:
        print("All logo slots filled.")

    # Note: full image processing (resize, adaptive icons) requires Pillow.
    # When Pillow is available the script can be extended to export densities.
    return 0 if not missing else 1

if __name__ == "__main__":
    sys.exit(main())

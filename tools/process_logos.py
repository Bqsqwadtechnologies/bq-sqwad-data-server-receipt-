#!/usr/bin/env python3
"""Logo pipeline v2 — detect real format, list slots, write status."""
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

SIGNATURES = {
    b"\x89PNG": "png",
    b"\xff\xd8\xff": "jpeg",
    b"RIFF": "webp",
    b"GIF8": "gif",
}

def detect_format(path: Path) -> str:
    try:
        head = path.read_bytes()[:12]
    except Exception:
        return "unknown"
    for sig, name in SIGNATURES.items():
        if head.startswith(sig):
            return name
    return "unknown"

def main():
    print("BQ Sqwad logo pipeline v2")
    print("=========================")
    missing, found = [], []
    for folder, files in SLOTS.items():
        folder_path = BRANDING / folder
        folder_path.mkdir(parents=True, exist_ok=True)
        readme = folder_path / "UPLOAD_LOGO_HERE.md"
        if not readme.exists():
            readme.write_text(f"# Upload logos for `{folder}`\n\nExpected files:\n" + "\n".join(f"- `{f}`" for f in files) + "\n", encoding="utf-8")
        for name in files:
            path = folder_path / name
            candidates = list(folder_path.glob(name.split(".")[0] + "*"))
            if path.exists() or candidates:
                target = path if path.exists() else candidates[0]
                fmt = detect_format(target)
                found.append(f"{folder}/{target.name} ({fmt})")
                print(f"  [OK] {folder}/{target.name}  format={fmt}")
            else:
                missing.append(str(path.relative_to(ROOT)))
                print(f"  [MISSING] {folder}/{name}")
    print()
    print(f"Found: {len(found)}  Missing: {len(missing)}")
    status = ROOT / "docs" / "LOGO_STATUS.md"
    status.write_text(
        "# Logo status\n\n## Found\n" + "\n".join(f"- {x}" for x in found) +
        "\n\n## Missing\n" + ("\n".join(f"- {x}" for x in missing) if missing else "- none") +
        "\n",
        encoding="utf-8",
    )
    print(f"Wrote {status.relative_to(ROOT)}")
    return 0 if not missing else 1

if __name__ == "__main__":
    sys.exit(main())

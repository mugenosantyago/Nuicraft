#!/usr/bin/env python3
"""
Replace bionicle_qfn with nuicraft in structure NBT files (gzipped).
Uses only stdlib. Run from repo root.
"""
import gzip
import struct
import sys
from pathlib import Path

STRUCTURE_DIR = Path(__file__).resolve().parent.parent / "src/main/resources/data/nuicraft/structure"
OLD_NS = b"bionicle_qfn"
NEW_NS = b"nuicraft"


def read_string(data: bytes, pos: int):
    """Read NBT string: 2 byte BE length + UTF-8 bytes. Returns (decoded_str, new_pos)."""
    if pos + 2 > len(data):
        return None, pos
    ln = struct.unpack(">H", data[pos : pos + 2])[0]
    pos += 2
    if pos + ln > len(data):
        return None, pos
    s = data[pos : pos + ln].decode("utf-8", errors="replace")
    return s, pos + ln


def write_string(buf: list, s: str):
    buf.append(struct.pack(">H", len(s.encode("utf-8"))))
    buf.append(s.encode("utf-8"))


def process_payload(data: bytes, pos: int, tag_type: int, out: list) -> int:
    """Read and write one tag payload (no type/name). Returns new pos."""
    if tag_type == 0x08:  # TAG_String
        s, pos = read_string(data, pos)
        if s is not None and OLD_NS.decode() in s:
            s = s.replace(OLD_NS.decode(), NEW_NS.decode())
        write_string(out, s or "")
        return pos
    if tag_type == 0x09:  # TAG_List
        if pos + 5 > len(data):
            return pos
        elem_type = data[pos]
        count = struct.unpack(">I", data[pos + 1 : pos + 5])[0]
        out.append(data[pos : pos + 5])
        pos += 5
        for _ in range(count):
            pos = process_payload(data, pos, elem_type, out)
        return pos
    if tag_type == 0x0A:  # TAG_Compound
        while pos < len(data):
            if data[pos] == 0:
                out.append(b"\x00")
                return pos + 1
            t = data[pos]
            pos += 1
            nlen = struct.unpack(">H", data[pos : pos + 2])[0]
            pos += 2
            n = data[pos : pos + nlen]
            pos += nlen
            out.append(bytes([t]))
            out.append(struct.pack(">H", nlen))
            out.append(n)
            pos = process_payload(data, pos, t, out)  # pos now after name, at payload
        return pos
    # Fixed-size types
    sizes = {1: 1, 2: 2, 3: 4, 4: 8, 5: 4, 6: 8}
    if tag_type == 0:  # TAG_End
        return pos
    if tag_type == 7:  # byte array
        ln = struct.unpack(">I", data[pos : pos + 4])[0]
        out.append(data[pos : pos + 4 + ln])
        return pos + 4 + ln
    if tag_type == 0x0B:  # int array
        ln = struct.unpack(">I", data[pos : pos + 4])[0]
        out.append(data[pos : pos + 4 + 4 * ln])
        return pos + 4 + 4 * ln
    if tag_type == 0x0C:  # long array
        ln = struct.unpack(">I", data[pos : pos + 4])[0]
        out.append(data[pos : pos + 4 + 8 * ln])
        return pos + 4 + 8 * ln
    sz = sizes.get(tag_type, 0)
    if sz:
        out.append(data[pos : pos + sz])
        return pos + sz
    return pos


def process_nbt(data: bytes) -> bytes:
    """Process root compound. Returns new NBT bytes."""
    pos = 0
    if data[pos] != 0x0A:
        return data
    pos += 1
    nlen = struct.unpack(">H", data[pos : pos + 2])[0]
    pos += 2 + nlen  # skip root name
    out = [b"\x0A", b"\x00\x00"]
    pos = process_payload(data, pos, 0x0A, out)
    return b"".join(out)


def main():
    if not STRUCTURE_DIR.is_dir():
        print("Structure dir not found:", STRUCTURE_DIR, file=sys.stderr)
        sys.exit(1)
    for nbt_path in sorted(STRUCTURE_DIR.glob("*.nbt")):
        try:
            with gzip.open(nbt_path, "rb") as f:
                raw = f.read()
        except Exception as e:
            print(nbt_path.name, "read error:", e, file=sys.stderr)
            continue
        if OLD_NS not in raw:
            continue
        try:
            new_raw = process_nbt(raw)
        except Exception as e:
            print(nbt_path.name, "process error:", e, file=sys.stderr)
            continue
        try:
            with gzip.open(nbt_path, "wb") as f:
                f.write(new_raw)
        except Exception as e:
            print(nbt_path.name, "write error:", e, file=sys.stderr)
            continue
        print("Updated:", nbt_path.name)
    print("Done.")


if __name__ == "__main__":
    main()

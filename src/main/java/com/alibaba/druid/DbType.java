package com.alibaba.druid;

import com.alibaba.druid.util.FnvHash;

public enum DbType {
    other           (1),
    oracle          (1 << 6),
    mysql           (1 << 7),
    oceanbase       (1 << 15),
    ali_oracle          (1 << 33),
    oceanbase_oracle       (1 << 31),
    ;

    public final long mask;
    public final long hashCode64;

    DbType(long mask) {
        this.mask = mask;
        this.hashCode64 = FnvHash.hashCode64(name());
    }

    public static long of(DbType... types) {
        long value = 0;

        for (DbType type : types) {
            value |= type.mask;
        }

        return value;
    }

    public static DbType of(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        try {
            return valueOf(name);
        } catch (Exception e) {
            return null;
        }
    }

}
